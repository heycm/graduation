<template>
  <div :id="id" :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from "echarts";
import resize from "./mixins/resize";
export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart"
    },
    id: {
      type: String,
      default: "chart"
    },
    width: {
      type: String,
      default: "200px"
    },
    height: {
      type: String,
      default: "200px"
    },
    dataSource: {
      type: Object,
      default: function() {
        return {
          title: "ECharts",
          xAxis: [],
          series: [{ name: "item", data: [] }]
        };
      }
    }
  },
  data() {
    return {
      chart: null,
      legendData: []
    };
  },
  computed: {},
  watch: {},
  created() {
    this.initLegendData();
    this.initSeries();
  },
  mounted() {
    this.initChart();
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    initChart() {
      this.chart = echarts.init(document.getElementById(this.id));
      this.chart.setOption({
        title: {
          text: this.dataSource.title
        },
        tooltip: {},
        legend: {
          top: 20,
          itemWidth: 14,
          itemHeight: 5,
          itemGap: 13,
          data: this.legendData,
          right: "4%",
          textStyle: {
            fontSize: 12
          }
        },
        xAxis: {
          data: this.dataSource.xAxis
        },
        yAxis: {},
        series: this.dataSource.series
      });
    },
    initLegendData() {
      for (let item of this.dataSource.series) {
        this.legendData = this.legendData.concat(item.name);
      }
    },
    initSeries() {
      this.dataSource.series.forEach(item => {
        this.$set(item, "type", "bar");
      });
    }
  }
};
</script>