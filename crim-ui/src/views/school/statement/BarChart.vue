<template>
  <div class="pageMain">
    <el-row class="marginB-10">
      <div class="chart-container">
      <BarChart id="BarChart" :dataSource="dataSource" height="100%" width="100%" />
      </div>
    </el-row>
  </div>
</template>

<script>
import BarChart from "@/components/charts/BarChart";
export default {
  components: { BarChart },
  data() {
    return {
      dataSource: {
        title: "ECharts 示例",
        xAxis: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
        series: [
          {
            name: "销量",
            data: [5, 20, 36, 10, 10, 20]
          },
          {
            name: "预期",
            data: [37, 46, 22, 18, 45, 30]
          },
          {
            name: "库存",
            data: [50, 27, 39, 100, 101, 198]
          }
        ]
      }
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {}
};
</script>