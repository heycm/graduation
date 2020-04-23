<template>
  <div class="pageMain">
    <part title="Excel导入" type="main" />
    <el-row class="add_import_excel">
      <el-upload
        class="upload-demo inline-block"
        action="https://jsonplaceholder.typicode.com/posts/"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :on-error="handleAvatarError"
        :before-upload="beforeAvatarUpload"
        :headers="headers"
      >
        <el-button type="primary">点击导入</el-button>
      </el-upload>
      <el-button type="info" plain class="marginL-10">下载模板</el-button>
    </el-row>
    <part title="逐条录入" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="年级">
          <el-select v-model="formInline.region" placeholder="活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="专业">
          <el-select v-model="formInline.region" placeholder="活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="班级">
          <el-select v-model="formInline.region" placeholder="活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="formInline.user" placeholder="输入学号"></el-input>
        </el-form-item>
         <br />
        <el-form-item label="姓名">
          <el-input v-model="formInline.user" placeholder="输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="formInline.region" placeholder="选择性别">
            <el-option label="男" :value="0"></el-option>
            <el-option label="女" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生年月">
          <el-date-picker
            v-model="formInline.birth"
            type="month"
            placeholder="选择日期"
            :default-value="defaultYear"
          ></el-date-picker>
        </el-form-item>
        <br />
        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      formInline: {
        user: "",
        region: "",
        birth: ""
      },
      defaultYear: null
    };
  },
  created() {
    this.initDefaultYear();
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
  methods: {
    initDefaultYear() {
        const now = new Date()
        const year = now.getFullYear()
        const defaultYear = year - 23
        this.defaultYear = new Date(`${defaultYear}-1-1`);
    },
    handleAvatarSuccess(res, file) {},
    handleAvatarError(err, file) {},
    beforeAvatarUpload(file) {
      console.log(file);
      let arr = file.name.split(".");
      let fileType = arr[arr.length - 1];
      let allowType = ["xls", "xlsx"];
      const isXls = allowType.indexOf(fileType) > -1;
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isXls) {
        this.$message.error("只能上传xls/xlsx文件");
        return;
      }
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过2MB!");
        return;
      }
    },
    onSubmit() {
      console.log("submit!");
    }
  }
};
</script>