<template>
  <div style="height:100%;">
    <div class="panel-main" v-if="myId!==null && friendId!==null">
      <part :title="friendName" type="main" v-if="friendName!==null" />
      <el-row class="chat-content">
        <el-scrollbar>
          <div class="show-msg-box">
            <div v-for="(msg, index) in msgList" :key="index">
              <el-row
                :class="['chat-content-item', msg.senderId===myId?'chat-content-item-right':'']"
                v-if="(msg.senderId===friendId&&msg.receiverId===myId) || (msg.senderId===myId&&msg.receiverId===friendId)"
              >
                <div class="chat-content-item-idPhoto">
                  <el-avatar :size="40" :src="msg.senderIdPhoto">
                    <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png" />
                  </el-avatar>
                </div>
                <div
                  :class="['chat-content-item-content', msg.senderId===myId?'content-right':'content-left']"
                >
                  <div
                    :class="['chat-content-item-content-triangle', msg.senderId===myId?'triangle-right':'triangle-left']"
                  ></div>
                  {{msg.content}}
                </div>
              </el-row>
            </div>
          </div>
        </el-scrollbar>
        <!-- </div> -->
      </el-row>
      <el-row class="chat-edit">
        <el-row class="chat-edit-menu">
          <el-tooltip effect="dark" content="发送简历" placement="top">
            <i class="el-icon-document chat-edit-icon"></i>
          </el-tooltip>
        </el-row>
        <el-row class="chat-edit-row">
          <ChatEditor @editorContent="getEditorContent" ref="editor" />
        </el-row>
        <el-row>
          <el-button round size="small" @click="sendMsg">发&nbsp;&nbsp;&nbsp;送</el-button>
        </el-row>
      </el-row>
    </div>
  </div>
</template>

<script>
import ChatEditor from "@/components/ChatEditor";
export default {
  components: { ChatEditor },
  props: {
    myId: {
      type: Number,
      default: null
    },
    friendId: {
      type: Number,
      default: null
    },
    friendName: {
      type: String,
      default: null
    },
    msgList: {
      type: Array,
      default: () => {
        return [];
      }
    }
  },
  data() {
    return {
      editorContent: "",
      chat: {
        senderId: "",
        senderName: "",
        senderIdPhoto: "",
        receiverId: "",
        receiverName: "",
        receiverIdPhoto: "",
        content: "",
        msgType: 1,
        sendTime: ""
      }
    };
  },
  computed: {},
  watch: {
    myId: {
      handler(newVal) {
        this.chat.senderId = newVal;
      },
      immediate: true
    },
    friendId: {
      handler(newVal) {
        this.chat.receiverId = newVal;
      },
      immediate: true
    }
  },
  created() {},
  methods: {
    getEditorContent(data) {
      this.editorContent = data;
    },
    sendMsg() {
      this.chat.content = this.editorContent;
      this.chat.sendTime = this.dateFormat(new Date());
      this.$emit("sendMsg", this.chat);
      this.$refs.editor.clear();
      this.editorContent = "";
    }
  }
};
</script>
<style scoped>
.panel-main {
  height: 100%;
  display: flex;
  flex-direction: column;
}
.chat-content {
  height: 70%;
  border: 1px solid #ebeef5;
  border-left: none;
  border-right: none;
  box-sizing: border-box;
}
.show-msg-box {
  padding: 12px;
}
.chat-content-item {
  display: flex;
  padding: 5px 0;
}
.chat-content-item-right {
  flex-direction: row-reverse;
}
.chat-content-item-content {
  background-color: #f2f5fa;
  padding: 8px 10px;
  border-radius: 8px 8px 8px 8px;
  position: relative;
}
.content-left {
  margin-left: 20px;
  margin-right: 60px;
}
.content-right {
  margin-left: 60px;
  margin-right: 20px;
}
.chat-content-item-content-triangle {
  width: 0px;
  height: 0px;
  border: 10px solid;
  border-color: transparent transparent #f2f5fa transparent;
  position: absolute;
  top: 0;
}
.triangle-left {
  left: -10px;
}
.triangle-right {
  right: -10px;
}
.chat-edit {
  height: 30%;
  /* height: 200px; */
  display: flex;
  flex-direction: column;
}
.chat-edit-menu {
  padding: 5px 20px;
  border-bottom: 1px solid #ebeef5;
  box-sizing: border-box;
  background-color: #f2f5fa;
}
.chat-edit-icon {
  font-size: 22px;
  color: #909399;
  cursor: pointer;
  margin-right: 8px;
}
.chat-edit-row {
  height: 100%;
  overflow: hidden;
  position: relative;
}
.chat-edit-row::after {
  content: "";
  height: 100%;
  width: 18px;
  background: #ffffff;
  position: absolute;
  top: 0;
  right: -1px;
  z-index: 1;
}
</style>