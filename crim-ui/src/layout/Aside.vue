<template>
  <el-scrollbar>
    <el-menu
      :default-active="active"
      class="el-menu-vertical-demo"
      @select="select"
      :collapse="menuSwitch"
      background-color="#304156"
      text-color="#BFCBD9"
    >
      <!-- 学校 -->
      <el-menu-item disabled>学校</el-menu-item>
      <el-menu-item index="1">
        <i class="el-icon-user"></i>
        <span slot="title">个人中心</span>
      </el-menu-item>
      <el-menu-item index="18">
        <i class="el-icon-setting"></i>
        <span slot="title">学院设置</span>
      </el-menu-item>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-school"></i>
          <span slot="title">场地管理</span>
        </template>
        <el-menu-item index="2-1">场地信息录入</el-menu-item>
        <el-menu-item index="2-2">场地列表</el-menu-item>
        <el-menu-item index="2-3">场地分配</el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-date"></i>
          <span slot="title">招聘会管理</span>
        </template>
        <el-menu-item index="3-1">发布招聘会</el-menu-item>
        <el-menu-item index="3-2">招聘会列表</el-menu-item>
        <el-menu-item index="3-3">参与企业列表</el-menu-item>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span slot="title">毕业生管理</span>
        </template>
        <el-menu-item index="4-1">毕业生列表</el-menu-item>
        <el-menu-item index="4-2">信息录入</el-menu-item>
      </el-submenu>
      <el-submenu index="5">
        <template slot="title">
          <i class="el-icon-office-building"></i>
          <span slot="title">企业管理</span>
        </template>
        <el-menu-item index="5-1">企业列表</el-menu-item>
        <el-menu-item index="5-2">待审核企业</el-menu-item>
        <el-menu-item index="5-3">审核未通过企业</el-menu-item>
        <el-menu-item index="5-4">黑名单</el-menu-item>
      </el-submenu>
      <el-submenu index="6">
        <template slot="title">
          <i class="el-icon-s-data"></i>
          <span slot="title">数据报表</span>
        </template>
        <el-menu-item index="6-1">柱状图</el-menu-item>
        <el-menu-item index="6-2">曲线图</el-menu-item>
        <el-menu-item index="6-3">南丁格尔图</el-menu-item>
      </el-submenu>
      <el-menu-item index="7">
        <i class="el-icon-document"></i>
        <span slot="title">附件</span>
      </el-menu-item>
      <!-- 学生 -->
      <el-menu-item disabled>学生</el-menu-item>
      <el-menu-item index="8">
        <i class="el-icon-user"></i>
        <span slot="title">个人中心</span>
      </el-menu-item>
      <el-menu-item index="9">
        <i class="el-icon-document"></i>
        <span slot="title">简历</span>
      </el-menu-item>
      <el-menu-item index="10">
        <i class="el-icon-office-building"></i>
        <span slot="title">企业列表</span>
      </el-menu-item>
      <!-- <el-menu-item index="11">
        <i class="el-icon-star-on"></i>
        <span slot="title">关注</span>
      </el-menu-item> -->
      <el-menu-item index="12">
        <i class="el-icon-s-opportunity"></i>
        <span slot="title">求职</span>
      </el-menu-item>
      <el-menu-item index="13">
        <i class="el-icon-s-comment"></i>
        <span slot="title">私信</span>
      </el-menu-item>
      <!-- 企业 -->
      <el-menu-item disabled>企业</el-menu-item>
      <el-menu-item index="14">
        <i class="el-icon-user"></i>
        <span slot="title">个人中心</span>
      </el-menu-item>
      <el-submenu index="15">
        <template slot="title">
          <i class="el-icon-date"></i>
          <span slot="title">招聘管理</span>
        </template>
        <el-menu-item index="15-4">申请入会</el-menu-item>
        <el-menu-item index="15-1">招聘会</el-menu-item>
        <el-menu-item index="15-2">宣讲会</el-menu-item>
        <el-menu-item index="15-3">参加学生</el-menu-item>
      </el-submenu>
      <el-menu-item index="16">
        <i class="el-icon-s-comment"></i>
        <span slot="title">私信</span>
      </el-menu-item>
      <el-menu-item index="17">
        <i class="el-icon-document"></i>
        <span slot="title">附件</span>
      </el-menu-item>
    </el-menu>
  </el-scrollbar>
</template>

<script>
import bus from "@/utils/bus";
export default {
  components: {},
  props: {
    menuSwitch: Boolean
  },
  data() {
    return {
      active: "1"
    };
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    // 接收面包屑路由跳转动态
    bus.$on("routerChange", route => {
      console.log(route)
      if (route && route.meta && route.meta.index) {
        this.active = route.meta.index;
      }
    });
  },
  methods: {
    select(index) {
      this.active = index;
      const actions = new Map([
        ['1', '/school/info'],
        ['18', '/school/management'],
        ['2-1', '/school/site/add'],
        ['2-2', '/school/site/list'],
        ['2-3', '/school/site/apportion'],
        ['3-1', '/school/activity/add'],
        ['3-2', '/school/activity/list'],
        ['3-3', '/school/activity/company'],
        ['4-1', '/school/student/list'],
        ['4-2', '/school/student/add'],
        ['5-1', '/school/company/list'],
        ['5-2', '/school/company/pending'],
        ['5-3', '/school/company/deny'],
        ['5-4', '/school/company/disable'],
        ['6-1', '/school/chart/bar'],
        ['6-2', '/school/chart/line'],
        ['6-3', '/school/chart/pie'],
        ['7', '/school/accessory'],
        ['8', '/student/info'],
        ['9', '/student/resume'],
        ['10', '/student/company/list'],
        // ['11', '/student/company/star'],
        ['12', '/student/company/interview'],
        ['13', '/student/chat'],
        ['14', '/company/info'],
        ['15-1', '/company/activity/jobfair'],
        ['15-2', '/company/activity/careertalk'],
        ['15-3', '/company/activity/student/list'],
        ['15-4', '/company/activity/apply'],
        ['16', '/company/chat'],
        ['17', '/company/accessory'],
        ['default', '/404']
      ])
      let path = actions.get(index) || actions.get('default')
      this.jumpTo(path)

    },
    jumpTo(path) {
      this.$router.push({path: path})
    }
  }
};
</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>