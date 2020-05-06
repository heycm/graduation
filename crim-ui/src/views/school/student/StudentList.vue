<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form v-model="pageReq.data" :inline="true" class="demo-form-inline">
        <el-form-item label="专业">
          <el-select v-model="pageReq.data.proId" clearable>
            <el-option
              v-for="item in proList"
              :key="item.id"
              :label="item.professionName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年级">
          <el-select v-model="pageReq.data.yearId" placeholder="请选择" clearable>
            <el-option
              v-for="year in yearList"
              :key="year.id"
              :label="year.yearName"
              :value="year.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select v-model="pageReq.data.classId">
            <el-option
              v-for="item in classList"
              :key="item.id"
              :label="item.gradeClassName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="pageReq.data.number" placeholder="学号" clearable></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="pageReq.data.name" placeholder="姓名" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getStudentPage">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="毕业生列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="studentPage" tooltip-effect="dark">
        <el-table-column prop="professionName" label="专业" show-overflow-tooltip></el-table-column>
        <el-table-column prop="yearName" label="年级" show-overflow-tooltip></el-table-column>
        <el-table-column prop="className" label="班级" show-overflow-tooltip></el-table-column>
        <el-table-column prop="studentNumber" label="学号" show-overflow-tooltip></el-table-column>
        <el-table-column prop="studentName" label="姓名" show-overflow-tooltip></el-table-column>
        <el-table-column prop="name" label="性别">
          <template slot-scope="scope">{{scope.row.studentSex===0?'男':'女'}}</template>
        </el-table-column>
        <el-table-column prop="birthString" label="出生年月" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              circle
              @click="editStudent(scope.row)"
            ></el-button>
            <el-button type="danger" size="mini" plain @click="resetPasswordConfirm(scope.row)">重置密码</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row type="flex" class="paddingLR-15 marginB-10">
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
    <el-dialog title="编辑" :visible.sync="dialogVisible">
      <el-form :model="editForm">
        <el-row :gutter="20">
          <el-col :span="12">
            <el-form-item label="姓名">
              <el-input v-model="editForm.studentName" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别">
              <br />
              <el-select v-model="editForm.studentSex">
                <el-option label="男" :value="0"></el-option>
                <el-option label="女" :value="1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="出生年月">
              <br />
              <el-date-picker v-model="editForm.studentBirth" type="date" placeholder="选择日期"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      proList: [
        {
          id: 1,
          professionName: "计算机科学与技术"
        }
      ],
      yearList: [
        {
          id: 8,
          yearName: "2020"
        }
      ],
      classList: [
        {
          id: 1,
          gradeClassName: "计算机 1801班"
        }
      ],
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      pageReq: {
        page: 1,
        rows: 5,
        data: {
          proId: "",
          yearId: "",
          classId: "",
          number: "",
          name: ""
        }
      },
      studentPage: [],

      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        }
      ],
      multipleSelection: [],
      dialogVisible: false,
      editForm: {
        id: "",
        studentName: "",
        studentSex: "",
        studentBirth: ""
      }
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.getProList();
      this.getYearList();
      this.getClassList();
      this.getStudentPage();
    },
    getProList() {
      this.$get("/profession/list")
        .then(res => {
          if (res.data.ok) {
            this.proList = res.data.data;
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
    getClassList() {
      this.$get("/gradeClass/list")
        .then(res => {
          if (res.data.ok) {
            this.classList = res.data.data;
          }
        })
        .catch(e => {});
    },
    getStudentPage() {
      this.$post("/student/pageList", this.pageReq)
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
      this.codeReq.rows = val;
      this.getStudentPage();
    },
    handleCurrentChange(val) {
      this.codeReq.page = val;
      this.getStudentPage();
    },
    editStudent(row) {
      this.dialogVisible = true;
      this.editForm.id = row.studentId;
      this.editForm.studentName = row.studentName;
      this.editForm.studentSex = row.studentSex;
      this.editForm.studentBirth = row.studentBirth;
    },
    update() {
      this.dialogVisible = false;
      this.$post("/student/update", this.editForm)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "修改成功",
              type: "success"
            });
            this.getStudentPage();
          }
        })
        .catch(e => {});
    },
    resetPasswordConfirm(row) {
      this.$confirm(
        `此操作将重置 ${row.studentName} 的登录密码, 是否继续?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.resetPassword(row.userId);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消重置"
          });
        });
    },
    resetPassword(id) {
      this.$get("/user/resetPwd/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              type: "success",
              message: "重置成功!"
            });
          }
        })
        .catch(e => {});
    }
  }
};
</script>