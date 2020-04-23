<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="年级">
          <el-select v-model="value" placeholder="请选择" @change="selectChange">
            <el-option label="不限" value></el-option>
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="专业">
          <el-select v-model="value">
            <el-option label="不限" value></el-option>
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select v-model="value">
            <el-option label="不限" value></el-option>
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <br />
        <el-form-item label="学号">
          <el-input v-model="value" placeholder="学号" clearable></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="value" placeholder="姓名" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
          <el-button type="info" plain>重置</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="毕业生列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="tableData" tooltip-effect="dark">
        <el-table-column prop="name" label="学号" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="name" label="姓名" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="name" label="性别" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="name" label="出生年月" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="address" label="专业" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="address" label="班级" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="150">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              size="mini"
              circle
              @click="editStudent(scope.row)"
            ></el-button>
            <el-button type="danger" size="mini" plain @click="resetPassword(scope.row)">重置密码</el-button>
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
          :current-page="1"
          :page-sizes="[5, 10, 20]"
          :page-size="5"
          layout="sizes, prev, pager, next"
          :total="1000"
          class="text-right"
        ></el-pagination>
      </el-col>
    </el-row>

    <!-- 弹窗 -->
    <el-dialog title="编辑" :visible.sync="dialogVisible">
      <el-form :model="form" label-width="100px">
        <el-form-item label="专业">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select v-model="form.region" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
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
      options: [
        {
          value: "1",
          label: "秦皇岛校区"
        },
        {
          value: "2",
          label: "开发区校区"
        },
        {
          value: "3",
          label: "昌黎校区"
        }
      ],
      value: "",
      dialogVisible: false,
      form: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: ""
      },
      formLabelWidth: "120px"
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    selectChange(value) {
      console.log(value);
    },
    onSubmit() {
      console.log("submit!");
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    editStudent(row) {
      this.dialogVisible = true;
      console.log(row);
    },
    resetPassword(row) {
      this.$confirm(`此操作将重置 ${row.name} 的登录密码, 是否继续?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            type: "success",
            message: "重置成功!"
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消重置"
          });
        });
    }
  }
};
</script>