<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <el-breadcrumb-item v-for="(item,index) in levelList" :key="item.path">
        <span v-if="item.redirect==='noRedirect'||index==levelList.length-1" class="no-redirect">{{ item.meta.title }}</span>
        <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
      </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
import bus from '@/utils/bus'
export default {
  components: {},
  data() {
    return {
      levelList: null
    };
  },
  computed: {},
  watch: {
    $route() {
      this.getBreadcrumb();
    }
  },
  created() {
    this.getBreadcrumb();
  },
  methods: {
    getBreadcrumb() {
      let matched = this.$route.matched.filter(
        item => item.meta && item.meta.title
      );
      const first = matched[0];
      if (!this.isInfo(first)) {
        // 判断一下当前用户角色，去不同的主页
        matched = [
          { path: "/school/info", meta: { title: "个人中心", role: "school", index: '1' } }
        ].concat(matched);
      }
      this.levelList = matched.filter(
        item => item.meta && item.meta.title && item.meta.breadcrumb !== false
      );
    },
    isInfo(route) {
      const name = route && route.name;
      if (!name) {
        return false;
      }
      const nameLower = name.trim().toLocaleLowerCase();
      return (
        nameLower === "schInfo".toLocaleLowerCase() ||
        nameLower === "stuInfo".toLocaleLowerCase() ||
        nameLower === "comInfo".toLocaleLowerCase()
      );
    },
    handleLink(route) {
      // 发送消息到Aside，改变激活菜单
      bus.$emit('routerChange', route)
      // 路由跳转
      const { redirect, path } = route
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(path)
    }
  }
};
</script>
<style lang="scss" scoped>
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 8px;

  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
</style>