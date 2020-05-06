<template>
  <div class="pageMain">
    <part title="Excel导入" type="main" />
    <el-row class="add_import_excel">
      <el-upload
        class="upload-demo inline-block"
        accept=".xls, .xlsx"
        action="http://localhost:9999/api/v1/student/import"
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
      <el-form :inline="true" :model="student" class="demo-form-inline">
        <el-form-item label="班级代码">
          <el-input v-model="student.gradeClassId" placeholder="例如：1" clearable></el-input>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="student.studentNumber" placeholder="例如：0913190101" clearable></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="student.studentName" placeholder="例如：张三" clearable></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="student.studentSex" placeholder="选择性别">
            <el-option label="男" :value="0"></el-option>
            <el-option label="女" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生年月">
          <el-date-picker
            v-model="student.studentBirth"
            type="date"
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
      student: {
        gradeClassId: "",
        studentNumber: "",
        studentName: "",
        studentSex: "",
        studentBirth: ""
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
        Authorization: this.getUserInfo().token
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
    handleAvatarSuccess(res, file) {
      if(res.ok){
        this.$message({
          type: "success",
          message: "导入成功"
        })
      }else{
        this.$message.error(res.msg)
      }
    },
    handleAvatarError(err, file) {
      this.$message.error("服务器繁忙，请稍后重试")
    },
    beforeAvatarUpload(file) {
      let arr = file.name.split(".");
      let fileType = arr[arr.length - 1];
      let allowType = ["xls", "xlsx"];
      const isXls = allowType.indexOf(fileType) > -1;
      const isLt2M = file.size / 1024 / 1024 < 10;
      if (!isXls) {
        this.$message.error("只能上传xls/xlsx文件");
        return;
      }
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过10MB!");
        return;
      }
    },
    onSubmit() {
     this.$post("/student/save", this.student)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "提交成功",
              type: "success"
            });
            this.student = this.clearObj(this.student)
          }
        })
        .catch(e => {});
    }
  }
};
</script>