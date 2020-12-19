<template>
  <div class="pageMain">
    <part title="查询条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" :model="pageReq.data" class="demo-form-inline">
        <!-- <el-form-item label="招聘会">
          <el-select v-model="formInline.region" placeholder="请选择招聘会">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>-->
        <el-form-item label="校区">
          <el-select v-model="pageReq.data.campusId" clearable>
            <el-option
              v-for="item in campusList"
              :key="item.id"
              :label="item.campusName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="楼号">
          <el-select v-model="pageReq.data.buildingId" clearable>
            <el-option
              v-for="item in buildingList"
              :key="item.id"
              :label="item.buildingName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="pageReq.data.siteStatus" clearable>
            <el-option label="空闲" :value="0"></el-option>
            <el-option label="占用" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getSitePage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="场地列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="sitePage" tooltip-effect="dark">
        <el-table-column prop="campusName" label="校区" width="150" show-overflow-tooltip></el-table-column>
        <el-table-column prop="buildingName" label="楼号" width="100" show-overflow-tooltip></el-table-column>
        <el-table-column prop="siteName" label="场地" width="100" show-overflow-tooltip></el-table-column>
        <el-table-column prop="siteDesc" label="场地说明" show-overflow-tooltip></el-table-column>
        <el-table-column prop="siteStatus" label="状态" width="100">
          <template slot-scope="scope">
            <el-button type="success" plain size="mini" v-if="scope.row.siteStatus===0">空闲</el-button>
            <el-button type="primary" plain size="mini" v-if="scope.row.siteStatus===1">占用</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="date" label="占用人" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="80" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button
              type="text"
              size="mini"
              v-if="scope.row.siteStatus===0"
              @click="allocateSite(scope.row)"
            >分配</el-button>
            <el-button
              type="text"
              size="mini"
              v-if="scope.row.siteStatus===1"
              @click="allocateSite(scope.row)"
            >收回</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-col>
        <el-button type="primary">导出此页</el-button>
        <el-button type="primary">导出所有</el-button>
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

    <!-- 弹窗 -->
    <el-dialog title="未分配场地的企业" :visible.sync="dialogVisible">
      <el-row>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="企业名称">
            <el-input v-model="formInline.user" placeholder="企业名称"></el-input>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row>
        <el-table :data="tableData" style="width:100%">
          <el-table-column property="date" label="企业名称"></el-table-column>
          <el-table-column label="操作" width="100">
            <template>
              <el-button type="text" size="mini">分配</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-pagination class="marginT-10 text-right" layout="prev, pager, next" :total="50"></el-pagination>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      pageReq: {
        page: 1,
        rows: 5,
        data: {
          camopusId: "",
          buildingId: "",
          siteStatus: ""
        }
      },
      campusList: [],
      buildingList: [],
      sitePage: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      tableData: [
        {
          date: "2016-05-02",
          status: "王小虎",
          title: "上海市普陀区金沙江路 1518 131232",
          site: {
            campus: "校区",
            building: "楼号",
            name: "名称"
          }
        },
        {
          date: "2016-05-04",
          status: "王小虎",
          title: "上海市普陀区金沙江路 1517 弄",
          site: {
            campus: "校区",
            building: "楼号",
            name: "名称"
          }
        },
        {
          date: "2016-05-01",
          status: "王小虎",
          title: "上海市普陀区金沙江路 1519 弄",
          site: {
            campus: "校区",
            building: "楼号",
            name: "名称"
          }
        },
        {
          date: "2016-05-03",
          status: "王小虎",
          title: "上海市普陀区金沙江路 1516 弄",
          site: {
            campus: "校区",
            building: "楼号",
            name: "名称"
          }
        }
      ],
      formInline: {
        user: "",
        region: ""
      },
      dialogVisible: false
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
      this.$post("/site/pageList", this.pageReq)
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
    onSubmit() {
      console.log("submit!");
    },
    allocateSite(row) {
      console.log("分配场地!");
      console.log(row);
      this.dialogVisible = true;
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  }
};
</script>