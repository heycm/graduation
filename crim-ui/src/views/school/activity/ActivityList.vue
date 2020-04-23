<template>
  <div class="pageMain">
    <part title="查询条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="标题">
          <el-input placeholder="请输入标题" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="活动列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="title" label="标题" show-overflow-tooltip></el-table-column>
        <el-table-column prop="date" label="开始日期" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="date" label="结束日期" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column label="附件" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-button
              type="text"
              size="mini"
              v-for="item in scope.row.accessoryList"
              :key="item.name"
              @click.prevent="clickAccessory(item)"
            >{{item.name}}</el-button>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="80"></el-table-column>
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
          status: "王小虎",
          title: "上海市普陀区金沙江路 1518 弄",
          accessoryList: [
            { name: "123.doc" },
            { name: "456.xls" },
            { name: "附件三.ppt" }
          ]
        },
        {
          date: "2016-05-04",
          status: "王小虎",
          title: "上海市普陀区金沙江路 1517 弄",
          accessoryList: [{ name: "123.doc" }, { name: "456.xls" }]
        },
        {
          date: "2016-05-01",
          status: "王小虎",
          title: "上海市普陀区金沙江路 1519 弄",
          accessoryList: [{ name: "123.doc" }, { name: "456.xls" }]
        },
        {
          date: "2016-05-03",
          status: "王小虎",
          title: "上海市普陀区金沙江路 1516 弄",
          accessoryList: [{ name: "123.doc" }, { name: "456.xls" }]
        }
      ],
      multipleSelection: []
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
    clickAccessory(accessory) {
      console.log(accessory);
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