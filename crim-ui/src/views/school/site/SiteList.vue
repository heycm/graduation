<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="校区">
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
        <el-form-item label="楼号">
          <el-select v-model="value" placeholder="活动区域">
            <el-option label="不限" value></el-option>
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="value" placeholder="不限" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
          <el-button type="info" plain>重置</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="场地列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column label="校区" width="120">
          <template slot-scope="scope">{{ scope.row.date }}</template>
        </el-table-column>
        <el-table-column prop="name" label="楼号" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="address" label="名称" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="address" label="详细说明" show-overflow-tooltip></el-table-column>
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
          :current-page="1"
          :page-sizes="[5, 10, 20]"
          :page-size="5"
          layout="sizes, prev, pager, next"
          :total="1000"
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