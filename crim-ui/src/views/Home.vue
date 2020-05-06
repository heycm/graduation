<template>
  <div class="pageMain">
    <el-form :model="chat" label-width="120px">
      <el-form-item label="发送人">
        <el-input v-model="chat.from" placeholder="发送人"></el-input>
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="chat.content" placeholder="内容"></el-input>
      </el-form-item>
      <el-form-item label="接收人">
        <el-input v-model="chat.to" placeholder="接收人"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="connect">连接服务器</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="send">发送</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-upload
        class="upload-demo"
        ref="upload"
        action
        multiple
        :limit="3"
        :file-list="fileList"
        :on-exceed="handleExceed"
        :on-change="handleChange"
        :before-remove="beforeRemove"
        :on-remove="handleRemove"
        :auto-upload="false"
      >
        <el-button slot="trigger" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" type="success" @click="submitUpload">上传到服务器</el-button>
      </el-upload>
    </div>
    <div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import SockJS from "sockjs-client";
import Stomp from "stompjs";
export default {
  components: {},
  data() {
    return {
      fileList: [],

      chat: {
        from: "",
        content: "",
        to: ""
      },
      stompClient: "",
      value: ""
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
  created() {},
  beforeDestroy() {},
  methods: {
    ...mapActions("demo", ["sendGet"]),
    connect() {
      let socket = new SockJS("http://localhost:9999/ws/stomp");
      this.stompClient = Stomp.over(socket);
      this.header.Authorization = this.chat.from;

      this.stompClient.connect(
        this.header,
        () => {
          console.log("连接成功");
          this.stompClient.subscribe("/user/queue/chat", chat => {
            console.log("收到消息");
            console.log(chat);
          });
        },
        e => {
          console.log("连接失败");
          console.log(e);
        }
      );
    },
    connectCallback() {},
    errorCallback(e) {},
    send() {
      this.header.Authorization = this.chat.from;
      this.stompClient.send(
        "/app/chat",
        this.header,
        JSON.stringify(this.chat)
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
    },
    // 上传文件
    submitUpload() {
      let param = new FormData();

      for(let item of  this.fileList){
        param.append("files", item.raw);
      }
      this.$post("/file/upload", param)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "上传成功",
              type: "success"
            });
            this.fileList = []
          }
        })
        .catch(e => {});
    },
    // 选择文件超出数量限制时
    handleExceed(files, fileList) {
      console.log(files);
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      );
    },
    // 上传文件之前
    handleChange(file, fileList) {
      const isLess10MB = file.size / 1024 / 1024 < 10;
      if (!isLess10MB) {
        this.$message.error(`文件限制10MB，请检查文件 ${file.name}`);
        fileList.splice(fileList.indexOf(file), 1);
      }
      this.fileList = fileList;
    },
    // 移除文件之前
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    // 移除文件
    handleRemove(file, fileList) {
      this.fileList = fileList;
    }
  }
};
</script>