<template>
  <div class="pageMain">
    <part title="LOGO" type="main" />
    <el-row type="flex" class="marginB-15">
      <el-col :span="6" class="paddingLR-15">
        <div class="demo-image marginB-5">
          <el-image class="info_logo_img" :src="info.logoUrl" fit="scale-down"></el-image>
        </div>
      </el-col>
      <el-col :span="12" :push="6" class="paddingLR-15">
        <el-upload
          class="upload-demo"
          action="http://localhost:9999/api/v1/file/sch/logo"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :on-error="handleAvatarError"
          :before-upload="beforeAvatarUpload"
          :headers="headers"
        >
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/jpeg/png/gif文件，且不超过2mb</div>
        </el-upload>
      </el-col>
    </el-row>
    <part title="名称" type="main" />
    <el-row type="flex" class="marginB-15">
      <el-col :span="12" class="paddingLR-15">
        <h1 class="marginB-15">{{info.schoolName}}</h1>
      </el-col>
      <el-col :span="12" class="paddingLR-15">
        <el-input v-model="editSch.schoolName" placeholder="请输入新名称"></el-input>
      </el-col>
    </el-row>
    <part title="地址 / 邮政编码" type="main" />
    <el-row type="flex" class="marginB-15">
      <el-col :span="12" class="paddingLR-15">
        <p>地址：{{info.schoolAddress}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮政编码：{{info.schoolPostalCode}}</p>
      </el-col>
      <el-col :span="12" class="paddingLR-15">
        <el-form :inline="true" class="demo-form-inline">
          <el-form-item label="地址">
            <el-input v-model="editSch.schoolAddress" placeholder="请输入地址"></el-input>
          </el-form-item>
          <el-form-item label="邮编">
            <el-input v-model="editSch.schoolPostalCode" placeholder="请输入邮政编码"></el-input>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <part title="简介" type="main" />
    <el-row type="flex" class="marginB-15">
      <el-col class="paddingLR-15">
        <div class="info_jianjie_content marginB-5" v-html="info.schoolDesc">{{info.schoolDesc}}</div>
      </el-col>
    </el-row>
    <el-row class="paddingLR-15 marginB-15">
      <WangEditor @editorContent="getEditorContent" ref="editor" placeholder="请输入简介..." />
    </el-row>
    <el-row class="paddingLR-15 marginB-15 text-center">
      <el-button type="warning" @click="reset">重置</el-button>
      <el-button type="primary" @click="update">保存</el-button>
    </el-row>
  </div>
</template>

<script>
import bus from '@/utils/bus'
import WangEditor from "@/components/WangEditor";
export default {
  components: { WangEditor },
  data() {
    return {
      editSch: {
        schoolName: "",
        schoolAddress: "",
        schoolPostalCode: "",
        schoolDesc: ""
      },
      info: {
        schoolName: "",
        schoolAddress: "",
        schoolPostalCode: "",
        schoolDesc: "",
        logoUrl:
          "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
      }
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
  methods: {
    init() {
      this.$get("/school/info")
        .then(res => {
          if (res.data.ok) {
            this.info = res.data.data;
            this.editSch = this.copyObj(res.data.data);
          }
        })
        .catch(() => {});
    },
    handleAvatarSuccess(res, file) {
      console.log(res);
      this.$message({
        message: "上传成功",
        type: "success"
      });
      this.init();
      bus.$emit('logoChange')
    },
    handleAvatarError(err, file) {
      err = JSON.parse(err)
      console.log(err);
      this.$message.error(err.data.msg);
    },
    beforeAvatarUpload(file) {
      // 图片格式
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
    getEditorContent(data) {
      console.log(data);
      this.editSch.schoolDesc = data;
    },
    reset() {
      this.$refs.editor.clear();
      this.editSch.schoolDesc = "";
    },
    update() {
      this.$post("/school/info", this.editSch)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.init();
          }
        })
        .catch(() => {});
    }
  }
};
</script>