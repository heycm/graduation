<template>
  <div>
    <div class="pageMain marginB-15">
      <part title="宣讲会" type="main" />
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
          <br />
          <el-form-item label="企业名称">
            <el-input v-model="pageReq.data.companyName" placeholder="例如：阿里巴巴" clearable></el-input>
          </el-form-item>
          <el-form-item label="关注">
            <el-select v-model="pageReq.data.isStar" clearable>
              <el-option label="已关注" :value="true"></el-option>
              <el-option label="未关注" :value="false"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="getCareerTalkPage">查询</el-button>
          </el-form-item>
        </el-form>
      </el-row>
      <el-row type="flex" class="paddingLR-15 marginB-30">
        <el-table :data="careerTalkList" tooltip-effect="dark">
          <el-table-column prop="yearName" label="年度" width="60"></el-table-column>
          <el-table-column label="季度" width="50">
            <template
              slot-scope="scope"
            >{{scope.row.quarter===0?'秋季':scope.row.quarter===1?'春季':''}}</template>
          </el-table-column>
          <el-table-column prop="jobFairTitle" label="招聘会" width="200" show-overflow-tooltip></el-table-column>
          <el-table-column prop="campusName" label="校区" width="120"></el-table-column>
          <el-table-column prop="companyName" label="企业名称" width="200" show-overflow-tooltip></el-table-column>
          <el-table-column prop="careerTalkTitle" label="宣讲会" show-overflow-tooltip></el-table-column>
          <el-table-column prop="timeCycle" label="时间" width="200" show-overflow-tooltip></el-table-column>
          <el-table-column prop="siteName" label="地点" width="100" show-overflow-tooltip></el-table-column>
          <el-table-column label="操作" width="180">
            <template slot-scope="scope">
              <el-button type="text" size="mini" @click="look(scope.row)">查看</el-button>
              <el-button type="text" size="mini" v-if="scope.row.companyId" @click="toChat(scope.row.companyId)">发消息</el-button>
              <el-button
                type="text"
                size="mini"
                v-if="scope.row.careerTalkStudentId"
                @click="cancelJoin(scope.row)"
              >取消参加</el-button>
              <el-button type="text" size="mini" v-else @click="openJobDialog(scope.row)">参加</el-button>
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
    <div class="pageMain marginB-10">
      <part title="面试" type="main" />
      <el-row type="flex" class="paddingLR-15 marginB-30">
        <el-table :data="interViewList" tooltip-effect="dark">
          <el-table-column prop="companyName" label="企业名称" width="200" show-overflow-tooltip></el-table-column>
          <el-table-column prop="jobName" label="职位" width="150" show-overflow-tooltip></el-table-column>
          <el-table-column prop="regularPay" label="转正薪资" width="150"></el-table-column>
          <el-table-column prop="workPlace" label="工作地点" width="100" show-overflow-tooltip></el-table-column>
          <el-table-column prop="welfare" label="福利待遇" show-overflow-tooltip></el-table-column>
          <el-table-column label="Office进度" width="150" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button type="info" size="mini" plain v-if="scope.row.status===0">未开始</el-button>
              <el-button type="primary" size="mini" plain v-if="scope.row.status===1">待回复</el-button>
              <el-button type="success" size="mini" plain v-if="scope.row.status===2">已收到</el-button>
              <el-button type="danger" size="mini" plain v-if="scope.row.status===3">被拒绝</el-button>
            </template>
          </el-table-column>
          <el-table-column label="最终去向" width="80">
            <template slot-scope="scope" v-if="scope.row.status===2">
              <i
                :class="[scope.row.isLastChoice===1?'el-icon-star-on':'el-icon-star-off']"
                @click="finalChoiceChangeConfirm(scope.row)"
                :style="{fontSize:(scope.row.isLastChoice===1?35:25)+'px', cursor:'pointer', color:'#E6A23C'}"
              ></i>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
    </div>

    <!-- 弹窗 -->
    <el-dialog title="选择职位" :visible.sync="dialogVisible" width="80%">
      <el-row type="flex">
        <el-table :data="jobList">
          <el-table-column property="jobName" label="职位" width="120"></el-table-column>
          <el-table-column property="jobDesc" label="工作描述">
            <template slot-scope="scope">
              <div v-html="scope.row.jobDesc"></div>
            </template>
          </el-table-column>
          <el-table-column property="regularPay" label="薪资" width="80"></el-table-column>
          <el-table-column property="workPlace" label="工作地点" width="80"></el-table-column>
          <el-table-column property="experience" label="经验要求" width="80"></el-table-column>
          <el-table-column property="certificate" label="学历要求" width="80"></el-table-column>
          <el-table-column property="skill" label="技能要求"></el-table-column>
          <el-table-column property="welfare" label="福利" show-overflow-tooltip></el-table-column>
          <el-table-column label="操作" width="80">
            <template slot-scope="scope">
              <el-button type="text" size="mini" @click="join(scope.row)">选择</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
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
        page: 1,
        rows: 5,
        data: {
          yearId: "",
          quarter: "",
          jobFairTitle: "",
          companyName: "",
          isStar: ""
        }
      },
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      careerTalkList: [
        {
          id: 1,
          companyName: "alibaba",
          careerTalk: "宣讲会标题-1",
          time: "2020-03-15 14:30:00",
          place: "A907",
          remark: "没有备注"
        },
        {
          id: 1,
          companyName: "alibaba",
          careerTalk: "宣讲会标题-1",
          time: "2020-03-15 14:30:00",
          place: "A907",
          remark: "没有备注"
        },
        {
          id: 1,
          companyName: "alibaba",
          careerTalk: "宣讲会标题-1",
          time: "2020-03-15 14:30:00",
          place: "A907",
          remark: "没有备注"
        },
        {
          id: 1,
          companyName: "alibaba",
          careerTalk: "宣讲会标题-1",
          time: "2020-03-15 14:30:00",
          place: "A907",
          remark: "没有备注"
        }
      ],
      dialogVisible: false,
      jobList: [],
      joinCareerTalkReq: {
        careerTalkId: "",
        companyId: "",
        jobVacancyId: ""
      },
      interViewList: [
        {
          id: 1,
          companyName: "alibaba",
          jobName: "数据库运营",
          minRegularPay: "7",
          maxRegularPay: "10",
          welfare: "五险一金，965，弹性工作，零食下午茶",
          workPlace: "北京",
          officeStatus: 0,
          isFinalChioce: 0
        },
        {
          id: 2,
          companyName: "字节跳动",
          jobName: "Java开发",
          minRegularPay: "7",
          maxRegularPay: "10",
          welfare: "五险一金，美女多",
          workPlace: "北京",
          officeStatus: 0,
          isFinalChioce: 1
        },
        {
          id: 3,
          companyName: "美团",
          jobName: "Java开发",
          minRegularPay: "7",
          maxRegularPay: "10",
          welfare: "五险一金，美女多",
          workPlace: "北京",
          officeStatus: 0,
          isFinalChioce: 0
        },
        {
          id: 4,
          companyName: "饿了么",
          jobName: "Java开发",
          minRegularPay: "7",
          maxRegularPay: "10",
          welfare: "五险一金，美女多",
          workPlace: "北京",
          officeStatus: 0,
          isFinalChioce: 0
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
      this.getYearList();
      this.getCareerTalkPage();
      this.getIterviewList();
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
    getCareerTalkPage() {
      this.$post("/careerTalk/stu/pageList", this.pageReq)
        .then(res => {
          if (res.data.ok) {
            this.careerTalkList = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
          }
        })
        .catch(e => {});
    },
    getIterviewList() {
      this.$get("/interview/list")
        .then(res => {
          if (res.data.ok) {
            this.interViewList = res.data.data;
          }
        })
        .catch(e => {});
    },
    handleSizeChange(val) {
      this.pageReq.rows = val;
      this.getCareerTalkPage();
    },
    handleCurrentChange(val) {
      this.pageReq.page = val;
      this.getCareerTalkPage();
    },
    // 查看
    look(row) {
      console.log(row);
    },
    // 取消参加
    cancelJoin(row) {
      this.$delete("/careerTalkStudent/" + row.careerTalkStudentId)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "取消成功",
              type: "success"
            });
            this.getCareerTalkPage();
            this.getIterviewList();
          }
        })
        .catch(e => {});
    },
    openJobDialog(row) {
      this.joinCareerTalkReq.careerTalkId = row.id;
      this.joinCareerTalkReq.companyId = row.companyId;
      this.dialogVisible = true;
      this.$get("/jobVacancy/list/" + row.companyId)
        .then(res => {
          if (res.data.ok) {
            this.jobList = res.data.data;
          }
        })
        .catch(e => {});
    },
    join(row) {
      this.dialogVisible = false;
      this.joinCareerTalkReq.jobVacancyId = row.id;
      this.$post("/careerTalkStudent/join", this.joinCareerTalkReq)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "参加成功",
              type: "success"
            });
            this.getCareerTalkPage();
            this.getIterviewList();
          }
        })
        .catch(e => {});
    },
    // 改变最终去向
    finalChoiceChangeConfirm(row) {
      console.log(row);
      this.$confirm("您确定要改变最终去向吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.finalChoiceChange(row.id);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改"
          });
        });
    },
    finalChoiceChange(id) {
      this.$get("/interview/choice/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              type: "success",
              message: "修改成功!"
            });
            this.getIterviewList();
          }
        })
        .catch(e => {});
    },
    toChat(companyId) {
      this.$router.push("/student/chat/" + companyId)
    }
  }
};
</script>