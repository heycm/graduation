<template>
  <div class="pageMain">
    <part title="可申请列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-30">
      <el-table :data="applyList" tooltip-effect="dark">
        <el-table-column prop="yearName" label="年度" width="100"></el-table-column>
        <el-table-column label="季度" width="100">
          <template slot-scope="scope">{{scope.row.quarter===0?'秋季':'春季'}}</template>
        </el-table-column>
        <el-table-column prop="title" label="招聘会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="startTimeString" label="开始日期" width="180" show-overflow-tooltip></el-table-column>
        <el-table-column prop="endTimeString" label="结束日期" width="180" show-overflow-tooltip></el-table-column>
        <el-table-column label="附件">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="mini"
              v-for="file in scope.row.files"
              :key="file.id"
            >{{file.fileName}}.{{file.fileSuffix}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="100" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button
              type="info"
              plain
              size="mini"
              v-if="scope.row.progress==='未开始'"
            >{{scope.row.progress}}</el-button>
            <el-button
              type="success"
              plain
              size="mini"
              v-if="scope.row.progress==='进行中'"
            >{{scope.row.progress}}</el-button>
            <el-button
              type="info"
              size="mini"
              v-if="scope.row.progress==='已结束'"
            >{{scope.row.progress}}</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="openDialog(scope.row.id)">申请入会</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <part title="审核列表" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form v-model="auditReq.data" :inline="true" class="demo-form-inline">
        <el-form-item label="年度">
          <el-select v-model="auditReq.data.yearId" clearable>
            <el-option
              v-for="year in yearList"
              :key="year.id"
              :label="year.yearName"
              :value="year.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="季度">
          <el-select v-model="auditReq.data.quarter" clearable>
            <el-option label="秋季" :value="0"></el-option>
            <el-option label="春季" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="招聘会">
          <el-input v-model="auditReq.data.title" placeholder="请输入招聘会标题" clearable></el-input>
        </el-form-item>
        <el-form-item label="审核状态">
          <el-select v-model="auditReq.data.auditRes" clearable>
            <el-option label="待审核" :value="2"></el-option>
            <el-option label="通过" :value="0"></el-option>
            <el-option label="拒绝" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getAuditPage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="auditPage" tooltip-effect="dark">
        <el-table-column prop="yearName" label="年度" width="100"></el-table-column>
        <el-table-column label="季度" width="100">
          <template slot-scope="scope">{{scope.row.quarter===0?'秋季':'春季'}}</template>
        </el-table-column>
        <el-table-column prop="jobFairTitle" label="招聘会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="campusName" label="校区" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="applyTimeString" label="申请时间" width="160"></el-table-column>
        <el-table-column prop="date" label="审核状态" width="100">
          <template slot-scope="scope">
            <el-button type="success" size="mini" plain v-if="scope.row.auditRes===0">通过</el-button>
            <el-button type="danger" size="mini" plain v-if="scope.row.auditRes===1">拒绝</el-button>
            <el-button type="info" size="mini" plain v-if="scope.row.auditStatus===1">待审核</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="auditTimeString" label="审核时间" width="160"></el-table-column>
        <el-table-column prop="auditFeedback" label="审核意见" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="100">
          <template slot-scope="scope">
            <el-button type="text" size="mini" v-if="scope.row.auditStatus===1">撤销申请</el-button>
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

    <!-- 弹窗 -->
    <el-dialog title="选择校区" :visible.sync="dialogVisible" width="30%" @closed="dialogHandleClose">
      <el-form ref="form" label-width="80px">
        <el-form-item label="校区">
          <el-select v-model="campusId" placeholder="请选择校区">
            <el-option
              :label="item.campusName"
              :value="item.id"
              v-for="item in campusList"
              :key="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="joinJonFair">确 定</el-button>
      </span>
    </el-dialog>
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
      applyList: [],
      auditReq: {
        page: "",
        rows: "",
        data: {
          yearId: "",
          quarter: "",
          title: "",
          auditRes: ""
        }
      },
      auditPage: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      jobFairId: "",
      campusId: "",
      dialogVisible: false,
      campusList: [
        {
          id: 1,
          campusName: "数学与信息科技学院"
        }
      ]
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.getApplyList();
      this.getYearList();
      this.getAuditPage();
      this.geCampusList();
    },
    getApplyList() {
      this.$get("/jobFair/applyList")
        .then(res => {
          if (res.data.ok) {
            this.applyList = res.data.data;
          }
        })
        .catch(e => {});
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
    geCampusList() {
      this.$get("/type/campus/list")
        .then(res => {
          if (res.data.ok) {
            this.campusList = res.data.data;
          }
        })
        .catch(e => {});
    },
    getAuditPage() {
      this.$post("/auditRecord/applyJobFairAuditRecords", this.auditReq)
        .then(res => {
          if (res.data.ok) {
            this.auditPage = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
          }
        })
        .catch(e => {});
    },
    handleSizeChange(val) {
      this.auditPage.rows = val;
      this.getAuditPage();
    },
    handleCurrentChange(val) {
      this.auditPage.page = val;
      this.getAuditPage();
    },
    joinJonFair() {
      if (!this.campusId) {
        this.$message("请选择校区");
        return;
      }
      const jobFairId = this.jobFairId;
      const campusId = this.campusId;
      this.dialogVisible = false;
      this.$get(`/auditRecord/joinJobFair/${jobFairId}/${campusId}`)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: res.data.data,
              type: "success"
            });
            this.getAuditPage();
          }
        })
        .catch(e => {});
    },
    openDialog(id) {
      this.jobFairId = id;
      this.dialogVisible = true;
    },
    dialogHandleClose() {
      this.jobFairId = "";
      this.campusId = "";
    }
  }
};
</script>