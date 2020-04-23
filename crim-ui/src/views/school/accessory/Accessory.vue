<template>
  <div class="pageMain">
    <el-row type="flex" :gutter="20">
      <el-col :span="12">
        <part title="查询条件" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="文件名">
              <el-input v-model="formInline.user" placeholder="文件名" clearable></el-input>
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="formInline.region" placeholder="状态" clearable>
                <el-option label="挂载" value="shanghai"></el-option>
                <el-option label="闲置" value="beijing"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="招聘会">
              <el-input v-model="formInline.activity" placeholder="招聘会" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">查询</el-button>
            </el-form-item>
          </el-form>
        </el-row>
      </el-col>
      <el-col :span="12">
        <part title="上传文件" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-upload
            class="upload-demo"
            drag
            action="https://jsonplaceholder.typicode.com/posts/"
            multiple
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或
              <em>点击上传</em>
            </div>
          </el-upload>
        </el-row>
      </el-col>
    </el-row>
    <part title="附件列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column label="文件名" width="260">
          <template slot-scope="scope">{{ scope.row.date }}</template>
        </el-table-column>
        <el-table-column prop="name" label="大小(KB)" width="120"></el-table-column>
        <el-table-column label="状态" width="120">
          <template slot-scope="scope">
            <el-button
              :type="scope.row.status === 0 ? 'primary' : 'success'"
              size="mini"
              plain
            >{{scope.row.status === 0 ? '空闲' : '挂载'}}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="address" label="挂载招聘会" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="140">
          <template slot-scope="scope">
            <el-button type="text" size="mini">下载</el-button>
            <el-button type="text" size="mini">{{scope.row.status === 0 ? '挂载' : '闲置'}}</el-button>
            <el-button type="text" size="mini">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-col>
        <el-button type="danger">批量删除</el-button>
      </el-col>
      <el-col>
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="1"
          :page-sizes="[5, 10, 20]"
          :page-size="5"
          layout="sizes, prev, pager, next"
          :total="1000"
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
      tableData: [
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          status: 1
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          status: 0
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          status: 1
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          status: 1
        },
        {
          date: "2016-05-08",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          status: 1
        },
        {
          date: "2016-05-06",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          status: 0
        },
        {
          date: "2016-05-07",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
          status: 0
        }
      ],
      multipleSelection: [],
      formInline: {
        user: "",
        region: ""
      }
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    onSubmit() {
      console.log("submit!");
    }
  }
};
</script>