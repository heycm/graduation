<template>
  <div class="pageMain">
    <part title="基本信息" type="main" />
    <el-row type="flex">
      <el-col :span="6" class="paddingLR-15">
        <div class="demo-image marginB-5">
          <el-image class="info_logo_img marginB-10" :src="url" fit="scale-down"></el-image>
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
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
        <h1 class="marginB-15 stu_info_name">张三丰</h1>
        <el-row type="flex" :gutter="10">
          <el-col :span="8">
            <el-card class="box-card stu_info_card">
              <h3>
                学号：
                <span class="float-right">0913160406</span>
              </h3>
              <h3>
                学院：
                <span class="float-right">数学与信息科技学院</span>
              </h3>
              <h3>
                专业：
                <span class="float-right">网络工程</span>
              </h3>
              <h3>
                年级：
                <span class="float-right">2016 级</span>
              </h3>
              <h3 class="marginT-15">
                性别：
                <span class="float-right">男</span>
              </h3>
              <h3>
                出生年月：
                <span class="float-right">1998.03</span>
              </h3>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="box-card stu_info_card">
              <h3>
                手机：
                <span class="float-right">13878657039</span>
              </h3>
              <h3 v-show="true" class="marginB-15">
                手机号登录：
                <span class="float-right">
                  <el-switch
                    v-model="isUsePhoneLogin"
                    @change="usePhoneLogin"
                    active-color="#13ce66"
                    inactive-color="#ff4949"
                  ></el-switch>
                </span>
              </h3>
              <h3>
                邮箱：
                <span class="float-right">heycm@qq.com</span>
              </h3>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="box-card stu_info_card">
              <el-form ref="form" :model="form" label-width="40px">
                <el-form-item label="手机">
                  <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                  <el-input v-model="form.email"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">保存</el-button>
                </el-form-item>
              </el-form>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      url:
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587116785871&di=eef89dcb33f91e04b9899d9eeabb47f9&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201410%2F09%2F20141009224754_AswrQ.jpeg",
      isUsePhoneLogin: false,
      form: {
        phone: "",
        email: ""
      }
    };
  },
  computed: {
    headers() {
      return {
        // Authorization: localStorage.getItem("token")
        Authorization: "token"
      };
    }
  },
  watch: {},
  created() {},
  methods: {
    handleAvatarSuccess(res, file) {},
    handleAvatarError(err, file) {},
    beforeAvatarUpload(file) {
      console.log(file);
      let imgType = ["image/jpeg", "image/gif", "image/png"];
      const isJPG = imgType.indexOf(file.type) > -1;
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("只能上传jpg/jpeg/png/gif文件");
        return;
      }
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过2MB!");
        return;
      }
    },
    usePhoneLogin(val) {
      console.log(val);
    },
    onSubmit() {
      console.log("submit!");
    }
  }
};
</script>