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
      chat: {
        from: "",
        content: "",
        to: ""
      },
      header: {
        Authorization: ""
      },
      stompClient: ""
    };
  },
  computed: {},
  watch: {},
  created() {},
  beforeDestroy: function() {
    this.disconnect();
  },
  methods: {
    ...mapActions("demo", ["sendGet"]),
    connect() {
      let socket = new SockJS("http://localhost:9999/ws/stomp");
      this.stompClient = Stomp.over(socket);
      this.header.Authorization = this.chat.from;

      this.stompClient.connect(
        this.header,
        () => {
          console.log("连接成功")
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
    }
  }
};
</script>