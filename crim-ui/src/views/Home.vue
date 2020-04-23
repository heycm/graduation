<template>
  <div class="bgFFF padding15">
    <el-upload
      class="avatar-uploader"
      action="http://localhost:8888/upload/img"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :on-error="handleAvatarError"
      :before-upload="beforeAvatarUpload"
      :headers="headers"
    >
      <img v-if="imageUrl" :src="imageUrl" class="avatar" />
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>
    <br />
    <el-upload
      class="upload-demo"
      drag
      action="http://localhost:8888/upload/img"
      :on-success="handleAvatarSuccess"
      :on-error="handleAvatarError"
      :before-upload="beforeAvatarUpload"
      :show-file-list="false"
    >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">
        将文件拖到此处，或
        <em>点击上传</em>
      </div>
      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
    <br />
    <el-button type="primary" @click="send">主要按钮</el-button>
    <br>
    <br>
    <br>
    <div v-html="editorContent">

    </div>
    <WangEditor @editorContent="getEditorContent" />
  </div>
</template>

<script>
import { mapActions } from "vuex";
import WangEditor from "@/components/WangEditor"
export default {
  components: {WangEditor},
  data() {
    return {
      imageUrl: "",
      userReq: {
        id: "zhongGuo shangHai",
        username: "中国",
        password: "上海"
      },
      editorContent: ''
    };
  },
  computed: {
    headers() {
      return {
        // Authorization: localStorage.getItem("token")
        sid: "token"
      };
    }
  },
  watch: {},
  created() {},
  methods: {
    // ...mapActions('demo', {
    //   sendGet: 'sendGet'
    // }),
    ...mapActions('demo', [
      'sendGet'
    ]),
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
      console.log(res);
      console.log(file);
    },
    handleAvatarError(err, file) {
      console.log(err);
      console.log(file);
    },
    beforeAvatarUpload(file) {
      console.log(file);
      // 图片格式
      let imgType = ["image/jpeg", "image/gif", "image/png"];
      const isJPG = imgType.indexOf(file.type) > -1;

      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error(
          '上传头像图片只能是 ["image/jpeg", "image/gif", "image/png"] 格式!'
        );
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      //   return isJPG && isLt2M;
    },
    send() {
      // this.$post('/demoxx/saveee', this.userReq)
      // .then(res=>{
      //     console.log(res)
      // })
      // .catch(err => {
      //     console.log(err)
      // })

      // this.$get("/demo/" + "asjhdjasnhjash")
      //   .then(res => {
      //     console.log(res);
      //   })
      //   .catch(err => {
      //     console.log(err);
      //   });

      // this.$store.dispatch('demo/sendGet', "ahshshs")
      // .then(res => {
      //   console.log(res)
      // })
      // .catch(err => {
      //   console.log(err)
      // })

      this.sendGet("hhhs")
      .then(res => {
        console.log(res)
      })
      .catch(err => {
        console.log(err)
      })
    },
    getEditorContent(data) {
      console.log(data)
      this.editorContent = data;
    }
  }
};
</script>
<style scoped>
/* @import url(); 引入公共css类 */
.avatar-uploader {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>