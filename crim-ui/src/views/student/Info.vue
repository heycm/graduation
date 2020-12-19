<template>
  <div class="pageMain">
    <part title="基本信息" type="main" />
    <el-row type="flex" class="marginB-30">
      <el-col :span="6" class="paddingLR-15">
        <div class="demo-image marginB-5">
          <el-image class="info_logo_img marginB-10" :src="baseInfo.logoUrl" fit="scale-down"></el-image>
          <el-upload
            class="upload-demo"
            accept=".jpg, .jpeg, .png, .gif"
            action="http://localhost:9999/api/v1/file/stu/logo"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :on-error="handleAvatarError"
            :before-upload="beforeAvatarUpload"
            :headers="headers"
          >
            <el-button size="small" type="primary">更换头像</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </div>
      </el-col>
      <el-col :span="18" class="paddingLR-15">
        <h1 class="marginB-15 stu_info_name">{{baseInfo.studentName}}</h1>
        <el-row type="flex" :gutter="10">
          <el-col :span="8">
            <el-card class="box-card stu_info_card">
              <h3>
                学号：
                <span class="float-right">{{baseInfo.studentNumber}}</span>
              </h3>
              <h3>
                学院：
                <span class="float-right">{{baseInfo.deptName}}</span>
              </h3>
              <h3>
                专业：
                <span class="float-right">{{baseInfo.professionName}}</span>
              </h3>
              <h3>
                年级：
                <span class="float-right">{{baseInfo.yearLevel}} 级</span>
              </h3>
              <h3 class="marginT-15">
                性别：
                <span class="float-right">{{baseInfo.studentSex===0?'男':'女'}}</span>
              </h3>
              <h3>
                出生年月：
                <span class="float-right">{{baseInfo.studentBirth}}</span>
              </h3>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="box-card stu_info_card">
              <h3>
                手机：
                <span class="float-right">{{baseInfo.studentPhone}}</span>
              </h3>
              <h3 v-show="baseInfo.studentPhone" class="marginB-15">
                手机号登录：
                <span class="float-right">
                  <el-switch
                    v-model="baseInfo.allowPhoneLogin"
                    @change="usePhoneLogin"
                    active-color="#13ce66"
                    inactive-color="#ff4949"
                    :active-value="0"
                    :inactive-value="1"
                  ></el-switch>
                </span>
              </h3>
              <h3>
                邮箱：
                <span class="float-right">{{baseInfo.studentEmail}}</span>
              </h3>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="box-card stu_info_card">
              <el-form ref="form" :model="editForm" label-width="40px">
                <el-form-item label="手机">
                  <el-input v-model="editForm.studentPhone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                  <el-input v-model="editForm.studentEmail"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateInfo">保存</el-button>
                </el-form-item>
              </el-form>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <part title="重置密码" type="main" />
    <el-row type="flex" :gutter="20" class="marginB-30">
      <el-col :span="12">
        <el-card class="box-card stu_info_card">
          <div slot="header" class="clearfix">
            <span>旧密码重置</span>
          </div>
          <el-form ref="form" :model="pwdForm" label-width="80px">
            <el-form-item label="原密码">
              <el-input v-model.trim="pwdForm.oldPwd" show-password></el-input>
            </el-form-item>
            <el-form-item label="新密码">
              <el-input v-model.trim="pwdForm.newPwd" show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码">
              <el-input v-model.trim="pwdForm.surePwd" show-password></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="changePwd">确定</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
      <el-col :span="12" v-if="editForm.allowPhoneLogin===0">
        <el-card class="box-card stu_info_card">
          <div slot="header" class="clearfix">
            <span>短信验证码重置</span>
          </div>
          <el-form ref="form" :model="smsForm" label-width="80px">
            <el-form-item label="新密码">
              <el-input v-model.trim="smsForm.newPwd" show-password></el-input>
            </el-form-item>
            <el-form-item label="验证码">
              <el-input v-model.trim="smsForm.smsCode">
                <el-button
                  slot="append"
                  style="border: none;"
                  @click="getSmsCode"
                  :loading="isLoading"
                >{{isLoading?'发送中...':showSec?sec+' s':'获取验证码'}}</el-button>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="changePwdBySms">确定</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import bus from "@/utils/bus";
export default {
  components: {},
  data() {
    return {
      baseInfo: {
        id: "",
        studentName: "",
        studentNumber: "",
        deptName: "",
        professionName: "",
        yearLevel: "",
        studentSex: "",
        studentBirth: "",
        studentPhone: "",
        studentEmail: "",
        allowPhoneLogin: "",
        logoUrl: ""
      },
      editForm: {
        id: "",
        studentPhone: "",
        studentEmail: ""
      },
      pwdForm: {
        oldPwd: "",
        newPwd: "",
        surePwd: ""
      },
      smsForm: {
        newPwd: "",
        smsCode: ""
      },
      isLoading: false,
      showSec: false,
      timer: null,
      sec: 60
    };
  },
  computed: {
    headers() {
      return {
        Authorization: this.getUserInfo().token
      };
    }
  },
  watch: {},
  created() {
    this.init();
  },
  beforeDestroy() {
    this.stopCountDown();
  },
  methods: {
    init() {
      this.getBaseInfo();
    },
    getBaseInfo() {
      this.$get("/student/info")
        .then(res => {
          if (res.data.ok) {
            this.baseInfo = res.data.data;
            this.editForm = this.copyObj(this.baseInfo);
          }
        })
        .catch(e => {});
    },
    updateInfo() {
      this.$post("/student/own/update", this.editForm).then(res => {
        if (res.data.ok) {
          this.$message({
            message: "保存成功",
            type: "success"
          });
          this.getBaseInfo();
        }
      });
    },
    handleAvatarSuccess(res, file) {
      if (res.ok) {
        this.$message({
          message: "上传成功",
          type: "success"
        });
        bus.$emit("logoChange");
      }
    },
    handleAvatarError(err, file) {
      this.$message.error("系统繁忙，稍后重试");
    },
    beforeAvatarUpload(file) {
      let imgType = ["image/jpeg", "image/gif", "image/png"];
      const isJPG = imgType.indexOf(file.type) > -1;
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("只能上传 JPG/PNG/GIF 文件");
        return;
      }
      if (!isLt2M) {
        this.$message.error("文件大小不能超过 2MB!");
        return;
      }
    },
    usePhoneLogin(val) {
      this.$get("/student/changeAllowPhoneLogin")
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "设置成功",
              type: "success"
            });
          }
          this.getBaseInfo();
        })
        .catch(e => {
          this.getBaseInfo();
        });
    },
    changePwd() {
      if (!this.pwdForm.oldPwd) {
        return this.$message("请输入原密码");
      }
      if (!this.pwdForm.newPwd) {
        return this.$message("请输入新密码");
      }
      if (!this.pwdForm.surePwd) {
        return this.$message("请输入确认密码");
      }
      if (this.pwdForm.surePwd !== this.pwdForm.newPwd) {
        this.pwdForm.surePwd = "";
        return this.$message({
          message: "确认密码不匹配",
          type: "warning"
        });
      }
      this.$post("/user/stu/pwd", this.pwdForm)
        .then(res => {
          if (res.data.ok) {
            this.resetSuccess();
          }
        })
        .catch(e => {});
    },
    changePwdBySms() {
      if (!this.smsForm.newPwd) {
        return this.$message("请输入新密码");
      }
      if (!this.smsForm.smsCode) {
        return this.$message("请输入验证码");
      }
      this.$post("/user/stu/pwd/sms", this.smsForm)
        .then(res => {
          if (res.data.ok) {
            this.resetSuccess();
          }
        })
        .catch(e => {});
    },
    resetSuccess() {
      this.$message({
        message: "重置成功",
        type: "success"
      });
      this.removeUserInfo();
      this.$router.push("/");
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
        this.$qs.stringify({ phone: this.baseInfo.studentPhone })
      )
        .then(res => {
          this.isLoading = false;
          if (res.data.ok) {
            this.$message({
              message: "短信发送成功",
              type: "success"
            });
          } else {
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
    }
  }
};
</script>