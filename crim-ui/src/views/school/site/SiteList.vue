<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="校区">
          <el-select v-model="siteReq.data.campusId">
            <el-option label="不限" value></el-option>
            <el-option
              v-for="item in campusList"
              :key="item.id"
              :label="item.campusName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="楼号">
          <el-select v-model="siteReq.data.buildingId">
            <el-option label="不限" value></el-option>
            <el-option
              v-for="item in buildingList"
              :key="item.id"
              :label="item.buildingName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="siteReq.data.siteName" placeholder="例如：A103" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getSitePage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="场地列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table
        ref="multipleTable"
        :data="sitePage"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="campusName" label="校区" width="120"></el-table-column>
        <el-table-column prop="buildingName" label="楼号" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="siteName" label="名称" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="siteDesc" label="详细说明" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="120">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              circle
              @click="delSite(scope.row.siteId)"
            ></el-button>
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
      campusList: [
        {
          id: 1,
          campusName: "数学与信息科技学院"
        }
      ],
      buildingList: [
        {
          id: 1,
          buildingName: "逸夫楼"
        }
      ],
      siteReq: {
        page: 1,
        rows: 5,
        data: {
          campusId: "",
          buildingId: "",
          siteName: ""
        }
      },
      sitePage: [
        {
          id: 1,
          siteName: "A103",
          siteDesc: "",
          campusName: "秦皇岛校区",
          buildingName: "逸夫楼"
        }
      ],
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
      this.geCampusList();
      this.getBuildingList();
      this.getSitePage();
    },
    geCampusList() {
      this.$get("/type/campus/list")
        .then(res => {
          if (res.data.ok) {
            this.campusList = res.data.data;
          }
        })
        .catch(e => {});
    },
    getBuildingList() {
      this.$get("/building/list")
        .then(res => {
          if (res.data.ok) {
            this.buildingList = res.data.data;
          }
        })
        .catch(e => {});
    },
    getSitePage() {
      this.$post("/site/pageList", this.siteReq)
        .then(res => {
          if (res.data.ok) {
            this.sitePage = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
          }
        })
        .catch(e => {});
    },
    delSite(id) {
      this.$confirm("此操作将永久删除该场地, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "error"
      })
        .then(() => {
          this.$delete("/site/" + id)
            .then(res => {
              if (res.data.ok) {
                this.$message({
                  message: "删除成功",
                  type: "success"
                });
                this.getSitePage();
              }
            })
            .catch(e => {});
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    handleSizeChange(val) {
      this.siteReq.rows = val;
      this.getSitePage();
    },
    handleCurrentChange(val) {
      this.siteReq.page = val;
      this.getSitePage();
    }
  }
};
</script>