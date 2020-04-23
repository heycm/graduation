<template>
  <div class="pageMain">
    <el-row class="marginB-10">
      <div class="chart-container">
        <LineChart id="LineChart" height="100%" width="100%" />
      </div>
    </el-row>
  </div>
</template>

<script>
import LineChart from "@/components/charts/LineChart";
export default {
  components: { LineChart },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  created() {},
  methods: {}
};
</script>