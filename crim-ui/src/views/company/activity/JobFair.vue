<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form v-model="pageReq.data" :inline="true" class="demo-form-inline">
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
          <el-input v-model="pageReq.data.title" placeholder="请输入招聘会标题" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getJobFairPage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="招聘会" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="jobFairPage" tooltip-effect="dark">
        <el-table-column prop="yearName" label="年度" width="60"></el-table-column>
        <el-table-column label="季度" width="50">
          <template slot-scope="scope">{{scope.row.quarter===0?'秋季':scope.row.quarter===1?'春季':''}}</template>
        </el-table-column>
        <el-table-column prop="jobFairTitle" label="招聘会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="startTimeString" label="开始日期" width="120"></el-table-column>
        <el-table-column prop="endTimeString" label="结束日期" width="120"></el-table-column>
        <el-table-column prop="campusName" label="校区" width="120"></el-table-column>
        <el-table-column prop="siteName" label="场地" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="siteDesc" label="场地说明" show-overflow-tooltip></el-table-column>
        <el-table-column prop="progress" label="状态" width="80"></el-table-column>
        <el-table-column label="操作" width="120">
          <template slot-scope="scope">
            <el-button type="text" size="mini" v-if="scope.row.jobFairStatus!==-1">查看</el-button>
            <el-button type="text" size="mini" v-if="scope.row.jobFairStatus!==-1">取消参加</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row class="paddingLR-15 marginB-30">
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
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      yearList: [
        {
          id: 8,
          yearName: "2020"
        }
      ],
      pageReq: {
        page: "",
        rows: "",
        data: {
          yearId: "",
          quarter: "",
          title: ""
        }
      },
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      jobFairPage: []
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
      this.getJobFairPage();
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
    getJobFairPage() {
      this.$post("/jobFairCompany/pageList", this.pageReq)
        .then(res => {
          if (res.data.ok) {
            this.jobFairPage = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
          }
        })
        .catch(e => {});
    },
    handleSizeChange(val) {
      this.pageReq.rows = val;
      this.getJobFairPage();
    },
    handleCurrentChange(val) {
      this.pageReq.page = val;
      this.getJobFairPage();
    }
  }
};
</script>