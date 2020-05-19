<template>
  <div>
    <el-row type="flex" :gutter="20">
      <el-col :span="8">
        <div class="pageMain stu-chat-left">
          <part title="私信列表" type="main" />
          <ChatList
            :myId="myId"
            :chatList="chatList"
            @colseChat="closeChat"
            @chooseChat="chooseChat"
          />
        </div>
      </el-col>
      <el-col :span="16">
        <div class="pageMain stu-chat-right">
          <div class="stu-chat-panel">
            <ChatPanel
              :myId="myId"
              :friendId="friendId"
              :friendName="friendName"
              :msgList="chatList"
              @sendMsg="sendMsg"
            />
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import ChatList from "@/components/chat/ChatList";
import ChatPanel from "@/components/chat/ChatPanel";
export default {
  components: { ChatList, ChatPanel },
  data() {
    return {
      userId: "",
      socket: null,
      chat: {
        fromId: "",
        fromName: "",
        fromPhotoUrl: "",
        toId: "",
        toName: "",
        toPhotoUrl: "",
        content: "",
        type: 0,
        sendTime: ""
      },
      chatList: [],
      myId: null,
      myIdPhoto: null,
      myName: null,
      friendId: null,
      friendIdPhoto: null,
      friendName: null,
      timer: null
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  beforeDestroy() {
    if (this.timer !== null) {
      clearInterval(this.timer);
      this.timer = null;
    }
    if (this.socket !== null) {
      this.socket.close();
    }
  },
  methods: {
    init() {
      this.getUserId();
      this.openWS();
      this.createChatObj();
    },
    getUserId() {
      this.userId = this.getUserInfo().id;
    },
    createChatObj() {
      const companyId = this.$route.params.companyid;
      console.log(companyId);
      if (companyId) {
        this.$get("/company/user/" + companyId)
          .then(res => {
            if (res.data.ok) {
              this.getChatObj(res.data.data);
            }
          })
          .catch(e => {});
      }
    },
    getChatObj(userId) {
      this.$get("/chat/obj/" + userId)
        .then(res => {
          if (res.data.ok) {
            this.chatList = this.chatList.concat(res.data.data);
            this.myId = res.data.data.toId;
            this.myName = res.data.data.toName;
            this.myIdPhoto = res.data.data.toPhotoUrl;
            this.chooseChat(res.data.data);
          }
        })
        .catch(e => {});
    },
    openWS() {
      if (typeof WebSocket == "undefined") {
        this.$message("遗憾：您的浏览器不支持WebSocket");
      } else {
        const _self = this;
        this.socket = new WebSocket("ws://localhost:9999/ws/" + this.userId);
        this.socket.onopen = function(e) {
          _self.onOpen(e);
        };
        this.socket.onmessage = function(chat) {
          _self.onMessage(chat);
        };
        this.socket.onclose = function(e) {
          _self.onClose(e);
        };
        this.socket.onerror = function(e) {
          _self.onError(e);
        };
      }
    },
    onOpen(e) {
      this.$message("上线成功");
      this.socket.send(this.getUserInfo().token);
      this.keepAlive();
    },
    onMessage(chat) {
      console.log("onMessage");
      let msg = JSON.parse(chat.data);
      console.log(msg);
      if (msg.ok) {
        return;
      }
      this.myId = msg.toId;
      this.myName = msg.toName;
      this.myIdPhoto = msg.toPhotoUrl;
      this.chatList = this.chatList.concat(msg);
    },
    onClose(e) {},
    onError(e) {
      this.$message.error("服务器繁忙");
    },
    keepAlive() {
      let _self = this;
      this.timer = setInterval(() => {
        _self.socket.send("我还活着吗...？");
      }, 60000);
    },
    // 关闭聊天对象
    closeChat(chat) {
      for (let i = 0; i < this.chatList.length; i++) {
        if (this.chatList[i].fromId === chat.fromId) {
          this.chatList.splice(i, 1);
          i--;
        }
      }
      this.friendId = null;
      this.friendName = null;
    },
    // 选择聊天对象
    chooseChat(chat) {
      this.friendId = chat.fromId;
      this.friendName = chat.fromName;
      this.friendIdPhoto = chat.fromPhotoUrl;
    },
    sendMsg(chat) {
      chat.fromPhotoUrl = this.myIdPhoto;
      chat.fromName = this.myName;
      chat.fromId = this.myId;
      chat.toPhotoUrl = this.friendIdPhoto;
      chat.toName = this.friendName;
      chat.toId = this.friendId;
      console.log(chat);
      let item = new Object();
      item = this.copyObj(chat);
      this.chatList = this.chatList.concat(item);
      this.sendHandler(chat);
    },
    sendHandler(chat) {
      this.$post("/chat/sendOne", chat)
        .then(res => {
          if (res.data.ok) {
            this.$message(res.data.data);
          }
        })
        .catch(e => {});
    }
  }
};
</script>