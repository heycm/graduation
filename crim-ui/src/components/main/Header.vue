<template>
  <div class="header">
    <el-row type="flex" class="logo" justify="space-between" align="middle">
      <el-col>
        <a href="http://www.hevttc.edu.cn/" target="_blank">
          <img src="../../assets/img/logo.png" />
        </a>
      </el-col>
      <el-col>
        <el-button size="small" style="float:right;" @click="toUserCenter" v-if="isUserLogin">个人中心</el-button>
        <el-button
          size="small"
          style="float:right;"
          @click="$router.push({ path: '/login' })"
          v-else
        >Sign in</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  components: {},
  data() {
    return {
      userInfo: {},
      isUserLogin: false,
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.userInfo = this.getUserInfo();
      if(this.userInfo){
        this.isUserLogin = true;
        this.userRole = this.userInfo.roles[0]
      }
    },
    toUserCenter() {
      this.$router.push({path: this.userInfo.roles[0]})
    }
  }
};
</script>
<style scoped>
.header {
  background: rgb(64, 158, 255);
  padding-top: 3px;
}
.logo {
  width: 1000px;
  margin: 0 auto;
}
</style>