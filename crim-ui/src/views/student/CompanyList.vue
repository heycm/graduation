<template>
  <div class="pageMain">
    <part title="筛选条件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form v-model="pageReq.data" :inline="true" class="demo-form-inline">
        <el-form-item label="公司名称">
          <el-input v-model="pageReq.data.companyName" placeholder="例如：阿里巴巴" clearable></el-input>
        </el-form-item>
        <el-form-item label="热招职位">
          <el-input v-model="pageReq.data.hotJob" placeholder="例如：UI设计师" clearable></el-input>
        </el-form-item>
        <el-form-item label="关注">
          <el-select v-model="pageReq.data.isStart" clearable>
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
        <el-table-column label="操作" width="160">
          <template slot-scope="scope">
            <el-button type="text" size="mini" @click="toCompanyPage(scope.row)">查看</el-button>
            <el-button type="text" size="mini" @click="chat(scope.row)">私信</el-button>
            <el-button
              type="text"
              size="mini"
              @click="start(scope.row)"
              v-if="scope.row.isStart===0"
            >关注</el-button>
            <el-button
              type="text"
              size="mini"
              @click="cancelStart(scope.row)"
              v-if="scope.row.isStart===1"
            >取消关注</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row class="paddingLR-15 marginB-10">
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
</template>

<script>
export default {
  components: {},
  data() {
    return {
      pageReq: {
        page: "",
        rows: "",
        data: {
          companyName: "",
          hotJob: "",
          isStart: ""
        }
      },
      pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 10
      },
      tableData: [
        {
          id: 1,
          companyName: "百度",
          hotJob:
            "Hadoop Web工程师 测试开发工程师 数据分析产品经理 搜索产品中高级评估助理",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 1
        },
        {
          id: 2,
          companyName: "阿里巴巴集团",
          hotJob:
            "测试开发 金融技术专家 高级测试工程师 Java 高级体验设计师 体验设计师 高级前端开发工程师",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 1
        },
        {
          id: 3,
          companyName: "滴滴出行",
          hotJob:
            "司机服务经理 产品实习生 PHP/GO开发工程师 Java 体验设计实习生 用户运营",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 0
        },
        {
          id: 4,
          companyName: "高德地图",
          hotJob:
            "地图数据专家 数据质量保证专家 高级Java开发工程师/专家 数据产品经理 C++ 资深时空数据挖掘工程师",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 0
        },
        {
          id: 5,
          companyName: "携程旅行网",
          hotJob:
            "英语客服 客服专员 数据仓库工程师 供应链专员 运营助理（实习生） 内容运营专员",
          careerTalk: "宣讲会标题-1,宣讲会标题-2",
          isStart: 0
        }
      ],
      value: ""
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {},
    onSubmit() {
      // do select
      console.log(this.form);
    },
    // 查看企业
    toCompanyPage(row) {
      console.log(row);
    },
    // 私信
    chat(row) {
      console.log(row);
    },
    // 关注
    start(row) {
      console.log(row);
    },
    // 取消关注
    cancelStart(row) {
      console.log(row);
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