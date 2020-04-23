<template>
  <div class="footer">
    <div class="footer-content">
      <el-divider />
      <div>
        <span>©版权 吧啦吧啦</span>
        <el-divider direction="vertical"></el-divider>
        <span>备案 吧啦吧啦</span>
        <el-divider direction="vertical"></el-divider>
        <span>地址 巴拉巴拉</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Footer",
  components: {},
  data() {
    return {};
  },
  computed: {},
  watch: {},
  created() {},
  methods: {}
};
</script>
<style scoped>
.footer {
  background: rgb(246, 246, 248);
  padding: 20px 0 50px 0;
}
.footer-content {
  width: 1000px;
  margin: 0 auto;
}
.footer-content>div{
    text-align: center;
    color: #999999
}
</style>