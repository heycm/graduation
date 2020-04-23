<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form v-model="form" :inline="true" class="demo-form-inline">
        <el-form-item label="年度">
          <el-input v-model="form.companyName" placeholder="例如：2020" clearable></el-input>
        </el-form-item>
        <el-form-item label="季度">
          <el-select v-model="form.companyName" placeholder="请选择">
            <el-option label="秋季" value="shanghai"></el-option>
            <el-option label="春季" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="招聘会">
          <el-input v-model="form.companyName" placeholder="请输入招聘会标题" clearable></el-input>
        </el-form-item>
        <el-form-item label="宣讲会">
          <el-input v-model="form.companyName" placeholder="请输入宣讲会标题" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="参加学生" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="tableData" tooltip-effect="dark">
        <el-table-column prop label="年度" width="60">
          <template>2020</template>
        </el-table-column>
        <el-table-column label="季度" width="50">
          <template>春季</template>
        </el-table-column>
        <el-table-column prop="title" label="招聘会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="title" label="宣讲会" show-overflow-tooltip></el-table-column>
        <el-table-column prop="title" label="专业" width="150" show-overflow-tooltip>
          <template>计算机科学与技术</template>
        </el-table-column>
        <el-table-column prop="title" label="年级" width="60">
          <template>2016</template>
        </el-table-column>
        <el-table-column prop="title" label="姓名" width="80">
          <template>公孙浅雪</template>
        </el-table-column>
        <el-table-column prop="date" label="性别" width="50">
          <template>女</template>
        </el-table-column>
        <el-table-column prop="date" label="电话" width="120">
          <template>13888888888</template>
        </el-table-column>
        <el-table-column prop="date" label="邮箱" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column prop="date" label="微信" width="120" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" width="140">
          <template>
            <el-button type="text" size="mini">查看简历</el-button>
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
      form: {
        companyName: "",
        hotJob: "",
        isStart: ""
      },
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
      ]
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  }
};
</script>