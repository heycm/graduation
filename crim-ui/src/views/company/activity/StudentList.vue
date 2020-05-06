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
          <el-input v-model="pageReq.data.jobFairTitle" placeholder="请输入招聘会标题" clearable></el-input>
        </el-form-item>
        <el-form-item label="宣讲会">
          <el-input v-model="pageReq.data.careerTalkTitle" placeholder="请输入宣讲会标题" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getStudentPage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="参加学生" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="studentPage" tooltip-effect="dark">
        <el-table-column prop="yearName" label="年度" width="60"></el-table-column>
        <el-table-column label="季度" width="50">
          <template slot-scope="scope">{{scope.row.quarter===0?'秋季':'春季'}}</template>
        </el-table-column>
        <el-table-column prop="jobFairTitle" label="招聘会" width="200" show-overflow-tooltip></el-table-column>
        <el-table-column prop="campusName" label="校区" width="100" show-overflow-tooltip></el-table-column>
        <el-table-column prop="careerTalkTitle" label="宣讲会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="studentName" label="学生姓名" width="120"></el-table-column>
        <el-table-column prop="professionName" label="专业" width="150" show-overflow-tooltip></el-table-column>
        <el-table-column prop="studentYearLevel" label="年级" width="80"></el-table-column>
        <el-table-column prop="date" label="性别" width="80">
          <template
            slot-scope="scope"
          >{{scope.row.studentSex===0?'男':scope.row.studentSex===1?'女':''}}</template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope" v-if="scope.row.id">
            <el-button type="text" size="mini" @click="toResume(scope.row.studentId)">查看简历</el-button>
            <el-button type="text" size="mini">发送消息</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" class="paddingLR-15 marginB-30">
      <el-col>
        <el-button type="primary" plain>导出表格</el-button>
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
    <el-dialog title="查看简历" :visible.sync="dialogVisible" width="60%" @closed="closedDialog">
      <router-view/>
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
      pageReq: {
        page: "",
        rows: "",
        data: {
          yearId: "",
          quarter: "",
          jobFairTitle: "",
          careerTalkTitle: ""
        }
      },
      studentPage: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
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
      this.getYearList();
      this.getStudentPage();
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
    getStudentPage() {
      this.$post("/careerTalkStudent/pageList", this.pageReq)
        .then(res => {
          if (res.data.ok) {
            this.studentPage = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
          }
        })
        .catch(e => {});
    },
    handleSizeChange(val) {
      this.pageReq.rows = val;
      this.getStudentPage();
    },
    handleCurrentChange(val) {
      this.pageReq.page = val;
      this.getStudentPage();
    },
    toResume(studentId) {
      this.dialogVisible = true;
      this.$router.push("/company/activity/student/list/resume/" + studentId);
    },
    closedDialog(){
      this.$router.push("/company/activity/student/list");
    }
  }
};
</script>