<template>
  <div class="pageMain">
    <part title="查询条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="年份">
          <el-select v-model="jobFairReq.data.yearId" clearable>
            <el-option
              v-for="year in yearList"
              :key="year.id"
              :label="year.yearName"
              :value="year.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="季度">
          <el-select v-model="jobFairReq.data.quarter" clearable>
            <el-option label="秋季" :value="0"></el-option>
            <el-option label="春季" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标题">
          <el-input v-model="jobFairReq.data.title" placeholder="请输入标题" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getJobFairPage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="活动列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table
        ref="multipleTable"
        :data="jobFairPage"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="yearName" label="年份" width="100"></el-table-column>
        <el-table-column label="季度" width="100">
          <template slot-scope="scope">{{scope.row.quarter===0?'秋季':'春季'}}</template>
        </el-table-column>
        <el-table-column prop="title" label="标题" show-overflow-tooltip></el-table-column>
        <el-table-column prop="startTimeString" label="开始日期" width="160"></el-table-column>
        <el-table-column prop="endTimeString" label="结束日期" width="160"></el-table-column>
        <el-table-column label="附件" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button
              type="text"
              size="mini"
              v-for="item in scope.row.files"
              :key="item.fileName"
              @click.prevent="clickAccessory(item)"
            >{{item.fileName + '.' + item.fileSuffix}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="80">
          <template
            slot-scope="scope"
          >{{scope.row.status===0?'未发布':scope.row.status===1?'已发布':'已过期'}}</template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template>
            <el-button type="primary" icon="el-icon-edit" size="mini" circle></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini" circle></el-button>
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
      yearList: [
        {
          id: 8,
          yearName: "2020"
        }
      ],
      jobFairReq: {
        page: "",
        rows: "",
        data: {
          yearId: "",
          quarter: "",
          title: ""
        }
      },
      jobFairPage: [],
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
      this.$post("/jobFair/pageList", this.jobFairReq)
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
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
    clickAccessory(accessory) {
      console.log(accessory);
    },
    handleSizeChange(val) {
      this.jobFairReq.rows = val;
      this.getJobFairPage();
    },
    handleCurrentChange(val) {
      this.jobFairReq.page = val;
      this.getJobFairPage();
    }
  }
};
</script>