<template>
  <div class="crim-flex crim-flex-a-center crim-flex-j-center bg">
    <el-card shadow="hover" class="crad">
      <router-link to="/" style="color:#000000;">
        <h2 style="margin-bottom: 30px;">校园招聘信息管理系统</h2>
      </router-link>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="密码登录" name="first">
          <el-input v-model="username" placeholder="请输入工号/学号/手机号" style="margin-bottom: 15px;"></el-input>
          <el-input
            placeholder="请输入登录密码"
            v-model="password"
            show-password
            style="margin-bottom: 15px;"
          ></el-input>
          <div>
            <el-button type="text" style="float:right;">忘记密码？</el-button>
          </div>
          <div>
            <el-button type="primary" plain>登 录</el-button>
          </div>
        </el-tab-pane>
        <el-tab-pane label="验证码登录" name="second">
          <div>
            <el-input placeholder="请输入手机号" v-model="input1"></el-input>
          </div>
          <div style="margin-top: 15px;">
            <el-input placeholder="请输入验证码" v-model="input3" class="input-with-select">
              <el-button slot="append">获取验证码</el-button>
            </el-input>
          </div>
          <div style="margin-top: 15px;">
            <el-button type="primary" plain>登 录</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
export default {
  // import 《组件名称》 from '《组件路径》';
  components: {},
  data() {
    return {
      activeName: "first",
      username: "",
      password: "",
      input1: "",
      input2: "",
      input3: ""
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
};
</script>
<style scoped>
.bg {
  min-height: 700px;
  background: #409eff;
  position: relative;
}
.bg::after {
  content: "";
  width: 100%;
  height: 50%;
  background: #ffffff;
  position: absolute;
  bottom: 0;
}
.crad {
  width: 40%;
  min-width: 500px;
  z-index: 99;
  padding: 30px;
  border-radius: 0;
}
</style>