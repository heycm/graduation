<template>
  <div class="pageMain">
    <el-row type="flex" :gutter="20">
      <el-col :span="16">
        <part title="查询条件" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-form :inline="true" :model="pageReq" class="demo-form-inline">
            <el-form-item label="文件名">
              <el-input v-model="pageReq.data.fileName" placeholder="文件名" clearable></el-input>
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="pageReq.data.status" placeholder="状态" clearable>
                <el-option label="已挂载" :value="0"></el-option>
                <el-option label="闲置中" :value="1"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="getFilePage">查询</el-button>
            </el-form-item>
          </el-form>
        </el-row>
      </el-col>
      <el-col :span="8">
        <part title="上传文件" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-upload
            class="upload-demo"
            ref="upload"
            action
            multiple
            :limit="10"
            :file-list="fileList"
            :on-exceed="handleExceed"
            :on-change="handleChange"
            :before-remove="beforeRemove"
            :on-remove="handleRemove"
            :auto-upload="false"
          >
            <el-button slot="trigger" type="primary">选取文件</el-button>
            <el-button style="margin-left: 10px;" type="success" @click="submitUpload">上传到服务器</el-button>
          </el-upload>
        </el-row>
      </el-col>
    </el-row>
    <part title="附件列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table
        ref="multipleTable"
        :data="filePage"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="fileName" label="文件名" width="260"></el-table-column>
        <el-table-column prop="fileSize" label="大小(KB)" width="120"></el-table-column>
        <el-table-column label="状态" width="120">
          <template slot-scope="scope">
            <el-button
              :type="scope.row.status === 0 ? 'success' : 'primary'"
              size="mini"
              plain
            >{{scope.row.status === 0 ? '挂载' : '空闲'}}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="jobFairTitle" label="挂载招聘会" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="140">
          <template slot-scope="scope">
            <el-button type="text" size="mini">下载</el-button>
            <el-button type="text" size="mini">{{scope.row.status === 0 ? '闲置' : '挂载'}}</el-button>
            <el-button type="text" size="mini">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-col>
        <el-button type="danger" @click="delBatchConfirm">批量删除</el-button>
      </el-col>
      <el-col>
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pagination.pageSize"
          layout="sizes, prev, pager, next"
          :total="pagination.total"
          class="text-right"
        ></el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      fileList: [],
      pageReq: {
        page: "",
        rows: "",
        ids: [],
        data: {
          fileName: "",
          status: ""
        }
      },
      filePage: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      multipleSelection: []
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.getFilePage();
    },
    // 上传文件
    submitUpload() {
      if (!this.fileList || this.fileList.length === 0) {
        this.$message.warning("请先选择文件");
        return;
      }
      let param = new FormData();
      for (let item of this.fileList) {
        param.append("files", item.raw);
      }
      this.$post("/file/upload", param)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "上传成功",
              type: "success"
            });
            this.fileList = [];
          }
        })
        .catch(e => {});
    },
    // 选择文件超出数量限制时
    handleExceed(files, fileList) {
      console.log(files);
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      );
    },
    // 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    handleChange(file, fileList) {
      const isLess10MB = file.size / 1024 / 1024 < 10;
      if (!isLess10MB) {
        this.$message.error(`文件限制10MB，请检查文件 ${file.name}`);
        fileList.splice(fileList.indexOf(file), 1);
      }
      this.fileList = fileList;
    },
    // 移除文件之前
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name} ？`);
    },
    // 移除文件
    handleRemove(file, fileList) {
      this.fileList = fileList;
    },
    // 获取文件列表
    getFilePage() {
      this.$post("/file/sch/page", this.pageReq)
        .then(res => {
          if (res.data.ok) {
            this.filePage = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
          }
        })
        .catch(e => {});
    },
    handleSizeChange(val) {
      this.pageReq.rows = val;
      this.getFilePage();
    },
    handleCurrentChange(val) {
      this.pageReq.page = val;
      this.getFilePage();
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      let ids = [];
      this.multipleSelection.forEach(item => {
        ids.push(item.id);
      });
      this.pageReq.ids = ids;
    },
    clearSelection() {
      this.multipleSelection = [];
      this.pageReq.ids = [];
    },
    delBatchConfirm() {
      if(this.pageReq.ids.length === 0){
        this.$message({
            type: "info",
            message: "请先选择删除项"
          });
        return;
      }
      this.$confirm(`此操作将永久删除所选项, 是否继续?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "error"
      })
        .then(() => {
          this.delBatch();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    delBatch() {
      this.$post("/file/batchDel", this.pageReq)
        .then(res => {
          this.$message({
            message: "删除成功",
            type: "success"
          });
          this.clearSelection();
          this.getFilePage();
        })
        .catch(e => {
          this.clearSelection();
        });
    }
  }
};
</script>