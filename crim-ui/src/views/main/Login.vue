<template>
  <div class="crim-flex crim-flex-a-center crim-flex-j-center bg">
    <el-card shadow="hover" class="crad">
      <router-link to="/" style="color:#000000;">
        <h2 style="margin-bottom: 30px;">校园招聘信息管理系统</h2>
      </router-link>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="密码登录" name="first">
          <el-input
            v-model="login.username"
            placeholder="请输入工号/学号/手机号"
            style="margin-bottom: 15px;"
          ></el-input>
          <el-input
            placeholder="请输入密码"
            v-model="login.password"
            show-password
            style="margin-bottom: 15px;"
          ></el-input>
          <el-row>
            <el-col :span="17">
              <el-input placeholder="请输入六位验证码" v-model="login.vcode" style="margin-bottom: 15px;"></el-input>
            </el-col>
            <el-col :span="7" class="text-right">
              <VCode @changeCode="getVCode" :identifyCode="identifyCode" />
            </el-col>
          </el-row>

          <div>
            <el-button type="text" style="float:right;">忘记密码？</el-button>
          </div>
          <div>
            <el-button type="primary" plain @click="loginByUP">登 录</el-button>
          </div>
        </el-tab-pane>
        <el-tab-pane label="验证码登录" name="second">
          <div>
            <el-input placeholder="请输入手机号" v-model="login.phone"></el-input>
          </div>
          <div style="margin-top: 15px;">
            <el-input placeholder="请输入验证码" v-model="login.smsCode" class="input-with-select">
              <el-button
                slot="append"
                style="border: none;"
                @click="getSmsCode"
                :loading="isLoading"
              >{{isLoading?'发送中...':showSec?sec+' s':'获取验证码'}}</el-button>
            </el-input>
          </div>
          <div style="margin-top: 15px;">
            <el-button type="primary" plain @click="loginBySms">登 录</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import VCode from "@/components/VCode";
export default {
  components: { VCode },
  data() {
    return {
      activeName: "first",
      identifyCode: "123456",
      login: {
        username: "",
        password: "",
        phone: "",
        vcode: "",
        smsCode: ""
      },
      isLoading: false,
      showSec: false,
      timer: null,
      sec: 60
    };
  },
  computed: {},
  watch: {
    "login.phone": function(newVal, oldVal) {
      this.isLoading = false;
      this.stopCountDown();
    }
  },
  created() {
    this.getVCode();
  },
  beforeDestroy() {
    this.stopCountDown();
  },
  methods: {
    ...mapActions("user", ["loginByUsernameAndPassword"]),
    handleClick(tab, event) {},
    getVCode() {
      this.$get("/authCode/open/getVCode")
        .then(res => {
          if (res.data.ok) {
            this.identifyCode = res.data.data;
          }
        })
        .catch(() => {});
    },
    loginByUP() {
      this.$post("/user/open/loginByUsernameAndPassword", this.login)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "登录成功",
              type: "success"
            });
            this.setUserInfo(res.data.data);
            this.$router.push({ path: this.$route.query.redirect || "/" });
          } else {
            this.$message.error(res.data.msg);
            this.getVCode();
            this.login.vcode = "";
          }
        })
        .catch(e => {});
    },
    getSmsCode() {
      if (this.sec !== 60) {
        this.$message("短信已发送，请注意查收");
        return;
      }
      this.isLoading = true;
      this.countDown();
      this.$post(
        "/authCode/open/getSmsCode",
        this.$qs.stringify({ phone: this.login.phone })
      )
        .then(res => {
          this.isLoading = false;
          if (res.data.ok) {
            this.$message({
              message: "短信发送成功",
              type: "success"
            });
          } else {
            this.$message.error(res.data.msg);
            this.stopCountDown();
          }
        })
        .catch(e => {
          this.isLoading = false;
          this.stopCountDown();
        });
    },
    countDown() {
      this.showSec = true;
      this.timer = setInterval(() => {
        this.sec--;
        if (this.sec === 0) {
          clearInterval(this.timer);
          this.timer == null;
          this.showSec = false;
          this.sec = 60;
        }
      }, 1000);
    },
    stopCountDown() {
      this.sec = 60;
      this.showSec = false;
      if (this.timer !== null) {
        clearInterval(this.timer);
        this.timer == null;
      }
    },
    loginBySms() {
      this.$post("/user/open/loginBySmsCode", this.login)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "登录成功",
              type: "success"
            });
            this.setUserInfo(res.data.data);
            this.$router.push({ path: this.$route.query.redirect || "/" });
          } else {
            this.$message.error(res.data.msg);
            this.login.smsCode = "";
          }
        })
        .catch(e => {});
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