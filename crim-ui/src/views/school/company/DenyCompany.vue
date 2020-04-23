<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="信用代码">
          <el-input v-model="value" placeholder="请输入社会统一信用代码" clearable></el-input>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="value" placeholder="请输入企业名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="联系人">
          <el-input v-model="value" placeholder="请输入联系人名称" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="审核未通过企业" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="name" label="社会统一信用代码" width="200" show-overflow-tooltip></el-table-column>
        <el-table-column prop="name" label="企业名称" width="200" show-overflow-tooltip></el-table-column>
        <el-table-column prop="name" label="联系人" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="address" label="电话" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="address" label="备注" show-overflow-tooltip></el-table-column>
        <el-table-column prop="date" label="审核时间" width="170" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="110">
          <template>
            <el-button type="text">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row class="paddingLR-15 marginB-10">
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
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      tableData: [
        {
          date: "2016-05-02 16:18:33",
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
      value: ""
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection);
    },
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
    }
  }
};
</script>