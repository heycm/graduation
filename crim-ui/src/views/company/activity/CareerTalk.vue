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
          <el-button type="primary" @click="getCareerTalkPage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="宣讲会" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="careerTalkPage" tooltip-effect="dark">
        <el-table-column prop="yearName" label="年度" width="60"></el-table-column>
        <el-table-column label="季度" width="50">
          <template slot-scope="scope">{{scope.row.quarter===0?'秋季':'春季'}}</template>
        </el-table-column>
        <el-table-column prop="jobFairTitle" label="招聘会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="campusName" label="校区" show-overflow-tooltip></el-table-column>
        <el-table-column prop="careerTalkTitle" label="宣讲会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="startTimeString" label="开始时间" width="160"></el-table-column>
        <el-table-column prop="endTimeString" label="结束时间" width="160"></el-table-column>
        <el-table-column prop="progress" label="状态" width="100"></el-table-column>
        <el-table-column label="操作" width="140">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="mini"
              v-if="!scope.row.id"
              @click="openDialog(scope.row)"
            >新增</el-button>
            <el-button type="text" size="mini" v-if="scope.row.id" @click="openDialog(scope.row)">详细</el-button>
            <el-button
              type="text"
              size="mini"
              v-if="scope.row.id"
              @click="delCareerTalk(scope.row.id)"
            >删除</el-button>
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
    <el-dialog title="编辑" :visible.sync="dialogFormVisible" @closed="closeDialog">
      <el-form :model="careerTalk">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="年度">
              <el-input v-model="careerTalk.yearName" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="季度">
              <el-input :value="careerTalk.quarter===0?'秋季':'春季'" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="招聘会">
              <el-input v-model="careerTalk.jobFairTitle" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="校区">
              <el-input v-model="careerTalk.campusName" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="招聘会开始日期">
              <el-input v-model="careerTalk.jobFairStartTime" disabled></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="招聘会结束日期">
              <el-input v-model="careerTalk.jobFairEndTime" disabled></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="活动标题">
          <el-input v-model.trim="careerTalk.careerTalkTitle" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动时间">
          <br />
          <el-date-picker
            v-model="careerTalk.dateInput"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期"
          ></el-date-picker>
          <el-time-select
            placeholder="起始时间"
            v-model="careerTalk.startTimeInput"
            :picker-options="{
              start: '08:30',
              step: '00:15',
              end: '18:30'
            }"
          ></el-time-select>
          <el-time-select
            placeholder="结束时间"
            v-model="careerTalk.endTimeInput"
            :picker-options="{
              start: '08:30',
              step: '00:15',
              end: '18:30',
              minTime: careerTalk.startTimeInput
            }"
          ></el-time-select>
        </el-form-item>
        <el-form-item label="活动备注">
          <el-input
            type="textarea"
            :rows="4"
            v-model="careerTalk.careerTalkDesc"
            autocomplete="off"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
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
      careerTalkPage: [],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      careerTalk: {
        id: "",
        jobFairCompanyId: "",
        careerTalkTitle: "",
        dateInput: "",
        startTimeInput: "",
        endTimeInput: "",
        startTime: "",
        endTime: "",
        careerTalkDesc: "",
        jobFairStartTime: ""
      },
      dialogFormVisible: false,
      value: ""
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
      this.$post("/careerTalk/pageList", this.pageReq)
        .then(res => {
          if (res.data.ok) {
            this.careerTalkPage = res.data.data.records;
            this.pagination.total = res.data.data.total;
            this.pagination.pageSize = res.data.data.size;
            this.pagination.currentPage = res.data.data.current;
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
    openDialog(row) {
      this.careerTalk = this.copyObj(row);
      if (this.careerTalk.careerTalkDesc) {
        this.careerTalk.careerTalkDesc = this.parseTextarea(
          this.careerTalk.careerTalkDesc
        );
      }
      if (this.careerTalk.startTimeString && this.careerTalk.endTimeString) {
        const startHMS = this.careerTalk.startTimeString
          .split(" ")[1]
          .split(":");
        const endHMS = this.careerTalk.endTimeString.split(" ")[1].split(":");
        this.$set(
          this.careerTalk,
          "dateInput",
          this.careerTalk.startTimeString.split(" ")[0]
        );
        this.$set(
          this.careerTalk,
          "startTimeInput",
          startHMS[0] + ":" + startHMS[1]
        );
        this.$set(this.careerTalk, "endTimeInput", endHMS[0] + ":" + endHMS[1]);
      }
      this.dialogFormVisible = true;
    },
    closeDialog() {
      this.careerTalk = this.clearObj(this.careerTalk);
    },
    save() {
      const checkRes = this.checkForm();
      if (checkRes) {
        this.dialogFormVisible = false;
        this.$post("/careerTalk/save", checkRes)
          .then(res => {
            if (res.data.ok) {
              this.$message({
                message: "保存成功",
                type: "success"
              });
              this.getCareerTalkPage();
            }
          })
          .catch(e => {});
      }
    },
    checkForm() {
      let careerTalk = this.copyObj(this.careerTalk);
      if (!careerTalk.careerTalkTitle) {
        this.$message("请填写活动标题");
        return false;
      }
      if (!careerTalk.dateInput) {
        this.$message("请选择活动日期");
        return false;
      }
      const jobFairStart = new Date(careerTalk.jobFairStartTime).getTime();
      const jobFairEnd = new Date(careerTalk.jobFairEndTime).getTime();
      const select = new Date(careerTalk.dateInput).getTime();
      if (select < jobFairStart) {
        this.$message("活动日期不能早于 " + careerTalk.jobFairStartTime);
        return false;
      }
      if (select > jobFairEnd) {
        this.$message("活动日期不能晚于 " + careerTalk.jobFairEndTime);
        return false;
      }
      if (!careerTalk.startTimeInput || !careerTalk.endTimeInput) {
        this.$message("请选择活动时间");
        return false;
      }
      careerTalk.startTime = new Date(
        `${careerTalk.dateInput} ${careerTalk.startTimeInput}:00`
      );
      careerTalk.endTime = new Date(
        `${careerTalk.dateInput} ${careerTalk.endTimeInput}:00`
      );
      if (careerTalk.careerTalkDesc) {
        careerTalk.careerTalkDesc = this.transTextarea(
          careerTalk.careerTalkDesc
        );
      }
      return careerTalk;
    },
    delCareerTalk(id) {
      this.$delete("/careerTalk/" + id)
        .then(res => {
          if (res.data.ok) {
              this.$message({
                message: "删除成功",
                type: "success"
              });
              this.getCareerTalkPage();
            }
        })
        .catch(e => {});
    }
  }
};
</script>