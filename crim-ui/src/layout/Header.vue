<template>
  <div class="header">
    <div class="menu-switch">
      <i :class="[isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold']" @click="menuSwitch"></i>
    </div>
    <breadCrumb :role="userInfo.roles[0]" />
    <div class="avatar">
      <el-avatar
        shape="square"
        :size="40"
        :src="logoUrl?logoUrl:'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
        @click.native="userCenter"
        style="cursor:pointer;"
      ></el-avatar>
      <el-button type="text" style="margin-left:10px;" @click="logout">注销</el-button>
    </div>
  </div>
</template>

<script>
import bus from "@/utils/bus";
import BreadCrumb from "@/components/BreadCrumb";
export default {
  components: {
    breadCrumb: BreadCrumb
  },
  data() {
    return {
      isCollapse: false,
      userInfo: "",
      logoUrl: ""
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  mounted() {
    bus.$on("logoChange", () => {
      this.init();
    });
  },
  methods: {
    init() {
      this.userInfo = this.getUserInfo();
      if (
        this.userInfo.roles[0] === "school" ||
        this.userInfo.roles[0] === "school-child"
      ) {
        this.getSchoolInfo();
      }
      if (this.userInfo.roles[0] === "company") {
        this.getCompanyInfo();
      }
      if (this.userInfo.roles[0] === "student") {
        this.getStudentInfo();
      }
    },
    userCenter() {
      if (this.userInfo.isAudit === 0) {
        this.$router.push({ path: "/" + this.userInfo.roles[0] });
      }else{
        this.$router.push({ path: "/" + this.userInfo.roles[0] + "/register" });
      }
    },
    menuSwitch() {
      this.isCollapse = !this.isCollapse;
      this.$emit("menuSwitch", this.isCollapse);
    },
    // 用户登出
    logout() {
      this.$get("/user/open/logout");
      this.$message({
        message: "登出成功",
        type: "success"
      });
      this.removeUserInfo();
      this.$router.push("/");
    },
    // 获取学校的信息，包含logo
    getSchoolInfo() {
      this.$get("/school/info")
        .then(res => {
          if (res.data.ok) {
            this.logoUrl = res.data.data.logoUrl;
          }
        })
        .catch(() => {});
    },
    // 获取企业的信息，包含logo
    getCompanyInfo() {
      this.$get("/company/info")
        .then(res => {
          if (res.data.ok) {
            this.logoUrl = res.data.data.logoUrl;
          }
        })
        .catch(() => {});
    },
    // 获取学生的信息，包含logo
    getStudentInfo() {}
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