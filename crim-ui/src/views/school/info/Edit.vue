<template>
  <div class="pageMain">
    <part title="LOGO" type="main" />
    <el-row type="flex" class="marginB-15">
      <el-col :span="6" class="paddingLR-15">
        <div class="demo-image marginB-5">
          <el-image class="info_logo_img" :src="url" fit="scale-down"></el-image>
        </div>
      </el-col>
      <el-col :span="12" :push="6" class="paddingLR-15">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
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
        <h1 class="marginB-15">河北科技师范学院</h1>
      </el-col>
      <el-col :span="12" class="paddingLR-15">
        <el-input placeholder="请输入新名称"></el-input>
      </el-col>
    </el-row>
    <part title="地址 / 邮政编码" type="main" />
    <el-row type="flex" class="marginB-15">
      <el-col :span="12" class="paddingLR-15">
        <p>地址：哈哈哈哈哈哈哈&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮政编码：066004</p>
      </el-col>
      <el-col :span="12" class="paddingLR-15">
        <el-form :inline="true" class="demo-form-inline">
          <el-form-item label="地址">
            <el-input placeholder="请输入地址"></el-input>
          </el-form-item>
          <el-form-item label="邮编">
            <el-input placeholder="请输入邮政编码"></el-input>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <part title="简介" type="main" />
    <el-row type="flex" class="marginB-15">
      <el-col class="paddingLR-15">
        <div class="info_jianjie_content marginB-5">
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
            省属普通高等学校，坐落于滨海旅
            游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
            获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
            员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
            展试点院校、河北省创新创业教育示范高校。
          </p>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
            省属普通高等学校，坐落于滨海旅
            游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
            获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
            员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
            展试点院校、河北省创新创业教育示范高校。
          </p>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
            省属普通高等学校，坐落于滨海旅
            游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
            获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
            员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
            展试点院校、河北省创新创业教育示范高校。
          </p>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
            省属普通高等学校，坐落于滨海旅
            游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
            获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
            员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
            展试点院校、河北省创新创业教育示范高校。
          </p>
        </div>
      </el-col>
    </el-row>
    <el-row class="paddingLR-15 marginB-15">
        <WangEditor @editorContent="getEditorContent" ref="editor" placeholder="请输入简介..." />
    </el-row>
    <el-row class="paddingLR-15 marginB-15 text-center">
        <el-button type="warning" @click="reset">重置</el-button>
         <el-button type="primary">保存</el-button>
    </el-row>
  </div>
</template>

<script>
import WangEditor from "@/components/WangEditor";
export default {
  components: { WangEditor },
  data() {
    return {
      url: "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      jianjie: ""
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
      // 图片格式
      let imgType = ["image/jpeg", "image/gif", "image/png"];
      const isJPG = imgType.indexOf(file.type) > -1;

      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("只能上传jpg/jpeg/png/gif文件");
      }
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过2MB!");
      }
      return isJPG && isLt2M;
    },
    getEditorContent(data) {
      console.log(data)
      this.jianjie = data
    },
    reset() {
        this.$refs.editor.clear()
        this.jianjie = ""
    }
  }
};
</script>