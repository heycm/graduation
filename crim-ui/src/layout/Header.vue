<template>
  <div class="header">
    <div class="menu-switch">
      <i :class="[isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold']" @click="menuSwitch"></i>
    </div>
    <breadCrumb />
    <div class="avatar">
      <el-avatar
        shape="square"
        :size="40"
        :src="circleUrl"
        @click.native="$router.push({ path: '/user' })"
        style="cursor:pointer;"
      ></el-avatar>
      <el-button type="text" style="margin-left:10px;">注销</el-button>
    </div>
  </div>
</template>

<script>
import BreadCrumb from '@/components/BreadCrumb';
export default {
  components: {
    breadCrumb : BreadCrumb
  },
  data() {
    return {
      isCollapse: false,
      circleUrl:
        "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg"
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    menuSwitch() {
      this.isCollapse = !this.isCollapse;
      this.$emit('menuSwitch', this.isCollapse);
    }
  }
};
</script>
<style scoped>
.header {
  height: 100%;
}
.menu-switch {
  float: left;
  width: 50px;
  height: 50px;
  line-height: 50px;
  text-align: center;
  font-size: 30px;
}
.menu-switch > i {
  cursor: pointer;
}
.avatar {
  width: 150px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  float: right;
}
</style>