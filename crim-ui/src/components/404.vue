<template>
  <div class="not_found_main">
    <el-image :src="src"></el-image>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      src: require("@/assets/img/404.gif"),
      sec: 5,
      timer: null
    };
  },
  computed: {},
  watch: {},
  created() {
    this.goBack();
  },
  methods: {
    goBack() {
      this.$message(`5秒后将返回上一层`);
      this.timer = setInterval(() => {
        if (this.sec > 0) {
          this.sec--;
        } else {
          clearInterval(this.timer);
          this.$router.go(-1)
        }
      }, 1000);
    }
  }
};
</script>