<template>
  <div class="pageMain">
    <el-row class="marginB-10">
      <div class="chart-container">
        <PieChart id="PieChart" :dataSource="dataSource" height="100%" width="100%" />
      </div>
    </el-row>
  </div>
</template>

<script>
import PieChart from "@/components/charts/PieChart";
export default {
  components: { PieChart },
  data() {
    return {
      dataSource: {
        title: "ECharts 示例",
        series: {
          name: "南丁格尔图",
          data: [
            { value: 235, name: "视频广告" },
            { value: 274, name: "联盟广告" },
            { value: 310, name: "邮件营销" },
            { value: 335, name: "直接访问" },
            { value: 400, name: "搜索引擎" }
          ]
        }
      }
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {}
};
</script>