<template>
  <div class="pageMain">
    <part title="查询条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" :model="pageReq.data" class="demo-form-inline">
        <el-form-item label="年度">
          <el-select v-model="pageReq.data.yearId" clearable>
            <el-option
              v-for="year in yearList"
              :key="year.id"
              :label="year.yearName"
              :value="year.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="季度">
          <el-select v-model="pageReq.data.quarter" clearable>
            <el-option label="秋季" :value="0"></el-option>
            <el-option label="春季" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="招聘会">
          <el-input v-model="pageReq.data.title" placeholder="请输入企业名称" clearable></el-input>
        </el-form-item>
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
        <el-form-item label="企业名称">
          <el-input v-model="pageReq.data.companyName" placeholder="请输入企业名称" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getCompanyPage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="企业列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="companyList" tooltip-effect="dark">
        <el-table-column prop="yearName" label="年份" width="100"></el-table-column>
        <el-table-column label="季度" width="100">
          <template slot-scope="scope">{{scope.row.quarter===0?'秋季':'春季'}}</template>
        </el-table-column>
        <el-table-column prop="jobFairTitle" label="招聘会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="campusName" label="校区" width="100"></el-table-column>
        <el-table-column prop="companyName" label="企业名称" show-overflow-tooltip></el-table-column>
        <el-table-column label="场地分配" show-overflow-tooltip>
          <template slot-scope="scope">
            <div v-if="scope.row.siteStatus!==0">未分配</div>
            <div v-else>{{scope.row.buildingName}} {{scope.row.siteName}}</div>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-search" size="mini" circle></el-button>
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              circle
              @click="allocateSite(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-col>
        <el-button type="primary">导出此页</el-button>
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
    <el-dialog title="空闲场地" :visible.sync="dialogVisible">
      <el-row>
        <el-form :inline="true" :model="pageReq.data" class="demo-form-inline">
          <el-form-item label="楼号">
            <el-select v-model="pageReq.data.buildingId" placeholder="请选择楼号" clearable @change="getSitePage">
              <el-option
                :label="item.buildingName"
                :value="item.id"
                v-for="item in buildingList"
                :key="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row>
        <el-table :data="sitePage" style="width:100%">
          <el-table-column property="buildingName" label="楼号" width="200"></el-table-column>
          <el-table-column property="siteName" label="场地" width="150"></el-table-column>
          <el-table-column property="siteDesc" label="场地说明" show-overflow-tooltip></el-table-column>
          <el-table-column label="操作" width="70">
            <template slot-scope="scope">
              <el-button
                type="text"
                size="mini"
                v-if="scope.row.siteId"
                @click="handleAllocate(scope.row)"
              >分配</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-pagination
        @size-change="handleSizeChangeDialog"
        @current-change="handleCurrentChangeDialog"
        layout="prev, pager, next"
        :total="dialogTotal"
        class="marginT-10 text-right"
      ></el-pagination>
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
          yearId: "",
          quarter: "",
          title: "",
          campusId: "",
          companyName: "",
          siteStatus: 0,
          buildingId: ""
        }
      },
      yearList: [],
      campusList: [],
      companyList: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      buildingList: [],
      dialogVisible: false,
      sitePage: [],
      dialogTotal: 0,
      jobFairCompany: {}
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.getYearList();
      this.getCampusList();
      this.getCompanyPage();
    },
    getYearList() {
      this.$get("/type/year/list")
        .then(res => {
          if (res.data.ok) {
            this.yearList = res.data.data;
          }
        })
        .catch(e => {});
    },
    getCampusList() {
      this.$get("/type/campus/list")
        .then(res => {
          if (res.data.ok) {
            this.campusList = res.data.data;
          }
        })
        .catch(e => {});
    },
    getCompanyPage() {
      this.$post("/jobFairCompany/company/list", this.pageReq)
        .then(res => {
          if (res.data.ok) {
            this.companyList = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
          }
        })
        .catch(e => {});
    },
    handleSizeChange(val) {
      this.pageReq.rows = val;
      this.getCompanyPage();
    },
    handleCurrentChange(val) {
      this.pageReq.page = val;
      this.getCompanyPage();
    },
    allocateSite(row) {
      this.dialogVisible = true;
      this.getBuildingByCampus(row.campusId);
      this.pageReq.data.campusId = row.campusId;
      this.getSitePage();
      this.jobFairCompany = row;
    },
    getBuildingByCampus(campusId) {
      this.$get("/building/campus/" + campusId)
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
            this.dialogTotal = res.data.data.total;
          }
        })
        .catch(e => {});
    },
    handleSizeChangeDialog(val) {
      this.pageReq.rows = val;
      this.getSitePage();
    },
    handleCurrentChangeDialog(val) {
      this.pageReq.page = val;
      this.getSitePage();
    },
    handleAllocate(row) {
      console.log(this.jobFairCompany);
      console.log(row);
      this.$get(`/jobFairCompany/site/${this.jobFairCompany.id}/${row.siteId}`)
        .then(res => {
          if (res.data.ok) {
            this.getCompanyPage();
            this.dialogVisible = false;
          }
        })
        .catch(e => {});
    }
  }
};
</script>