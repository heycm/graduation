<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form v-model="form" :inline="true" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input v-model="form.companyName" placeholder="例如：阿里巴巴" clearable></el-input>
        </el-form-item>
        <el-form-item label="热招职位">
          <el-input v-model="form.hotJob" placeholder="例如：UI设计师" clearable></el-input>
        </el-form-item>
        <el-form-item label="关注">
          <el-select v-model="form.isStart">
            <el-option label="不限" value></el-option>
            <el-option label="已关注" :value="1"></el-option>
            <el-option label="未关注" :value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="企业列表" type="main" />
    <el-row type="flex" class="paddingLR-15 marginB-10">
      <el-table :data="tableData" tooltip-effect="dark">
        <el-table-column prop="companyName" label="企业名称" width="200" show-overflow-tooltip></el-table-column>
        <el-table-column prop="hotJob" label="热招职位" show-overflow-tooltip></el-table-column>
        <el-table-column prop="careerTalk" label="宣讲会" width="300"></el-table-column>
        <el-table-column label="操作" width="160">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="toCompanyPage(scope.row)">查看</el-button>
            <el-button type="text" size="mini" @click="chat(scope.row)">私信</el-button>
            <el-button type="text" size="mini" @click="start(scope.row)" v-if="scope.row.isStart===0">关注</el-button>
            <el-button type="text" size="mini" @click="cancelStart(scope.row)" v-if="scope.row.isStart===1">取消关注</el-button>
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
      form: {
        companyName: "",
        hotJob: "",
        isStart: ""
      },
      tableData: [
        {
          id: 1,
          companyName: "alibaba",
          hotJob:
            "王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 1
        },
        {
          id: 2,
          companyName: "alibaba",
          hotJob:
            "王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 1
        },
        {
          id: 3,
          companyName: "alibaba",
          hotJob:
            "王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 0
        },
        {
          id: 4,
          companyName: "alibaba",
          hotJob:
            "王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎 王小虎",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 0
        }
      ],
      value: ""
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    onSubmit() {
      // do select
      console.log(this.form);
    },
    // 查看企业
    toCompanyPage(row) {
        console.log(row)
    },
    // 私信
    chat(row) {
        console.log(row)
    },
    // 关注
    start(row) {
        console.log(row)
    },
    // 取消关注
    cancelStart(row) {
        console.log(row)
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