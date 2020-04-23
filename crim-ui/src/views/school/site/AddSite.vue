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
    <part title="新增校区" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.addr"></el-input>
        </el-form-item>
        <el-form-item label="详细说明">
          <el-input type="textarea" v-model="form.detail"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="新增楼号" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="校区">
          <el-select v-model="form.campus" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="详细说明">
          <el-input type="textarea" v-model="form.detail"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-row>
    <part title="新增场地" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="校区">
          <el-select v-model="form.campus" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="楼号">
          <el-select v-model="form.building" placeholder="请选择活动区域">
            <el-option label="区域一" value="shanghai"></el-option>
            <el-option label="区域二" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="详细说明">
          <el-input type="textarea" v-model="form.detail"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">创建</el-button>
          <el-button>取消</el-button>
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
      form: {
        name: "",
        addr: "",
        campus: "",
        building: "",
        detail: ""
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
    onSubmit() {
      console.log("submit!");
    },
    handleAvatarSuccess(res, file) {},
    handleAvatarError(err, file) {},
    beforeAvatarUpload(file) {
      console.log(file);
      let arr = file.name.split(".");
      let fileType = arr[arr.length-1];
      let allowType = ["xls", "xlsx"];
      const isXls = allowType.indexOf(fileType) > -1;
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isXls) {
        this.$message.error("只能上传xls/xlsx文件")
        return;
      }
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过2MB!");
        return;
      }
    }
  }
};
</script>