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
      default: () => {
        return {
          title: "ECharts",
          series: {
            name: "ECharts",
            data: []
          }
        };
      }
    }
  },
  data() {
    return {
      chart: null
    };
  },
  computed: {},
  watch: {},
  created() {},
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
        backgroundColor: "#2c343c",
        title: {
          text: this.dataSource.title,
          top: 20,
          left: 20,
          textStyle: {
            color: "rgba(255, 255, 255, 0.3)"
          }
        },
        textStyle: {
          color: "rgba(255, 255, 255, 0.3)"
        },
        labelLine: {
          lineStyle: {
            color: "rgba(255, 255, 255, 0.3)"
          }
        },
        tooltip: {},
        series: [
          {
            name: this.dataSource.series.name,
            type: "pie",
            roseType: "angle",
            radius: "55%",
            data: this.dataSource.series.data,
            itemStyle: {
              emphasis: {
                shadowBlur: 200,
                shadowColor: "rgba(0, 0, 0, 0.5)"
              }
            }
          }
        ]
      });
    }
  }
};
</script>