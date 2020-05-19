<template>
  <el-scrollbar>
    <div class="chat-list" v-if="myId!==null">
      <el-row v-for="(chat, index) in userList" :key="chat.id">
        <div :class="['chat-list-item',index===0?'border-top':'']" v-if="chat.toId===myId">
          <div class="chat-idPhoto" @click="chooseChat(chat)">
            <el-avatar :size="60" :src="chat.fromPhotoUrl">
              <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png" />
            </el-avatar>
          </div>
          <div class="chat-company-item" @click="chooseChat(chat)">
            <p class="chat-company-item-name">{{chat.fromName}}</p>
            <div class="chat-company-item-tip">
              <p v-html="parseTextarea(chat.content)"></p>
              <span>{{getHms(chat.sendTime)}}</span>
            </div>
          </div>
          <div class="item-close">
            <i class="el-icon-close" @click="colseChat(chat)"></i>
          </div>
        </div>
      </el-row>
      <el-row v-if="!chatList || chatList.length===0">
        <div class="no-msg">暂无消息</div>
      </el-row>
    </div>
  </el-scrollbar>
</template>

<script>
export default {
  props: {
    chatList: {
      type: Array,
      default: () => {
        return [];
      }
    },
    myId: {
      type: Number,
      default: null
    }
  },
  data() {
    return {
      userList: []
    };
  },
  computed: {},
  watch: {
    chatList: {
      handler(newVal) {
        this.getList();
      },
      immediate: true
    }
  },
  created() {},
  methods: {
    getList() {
      let uList = this.copyObj(this.chatList);
      for (let i = 0; i < this.chatList.length - 1; i++) {
        for (let j = i + 1; j < this.chatList.length; j++) {
          if (
            this.chatList[i].fromId === this.chatList[j].fromId &&
            this.chatList[j].sendTime > this.chatList[i].sendTime
          ) {
            for (let k = 0; k < uList.length; k++) {
              if (
                uList[k].fromId === this.chatList[i].fromId &&
                uList[k].sendTime === this.chatList[i].sendTime
              ) {
                uList.splice(k, 1);
                break;
              }
            }
          }
        }
      }
      this.userList = uList;
    },
    // 关闭聊天列表item
    colseChat(chat) {
      this.$emit("colseChat", chat);
    },
    chooseChat(chat) {
      this.$emit("chooseChat", chat);
    },
    getHms(str) {
      if (!str) {
        return "";
      }
      let arr = this.dateFormat(new Date(str)).split(" ");
      if (arr.length === 1) {
        return arr[0];
      }
      return arr[1];
    }
  }
};
</script>
<style scoped>
.no-msg {
  height: 60px;
  line-height: 60px;
  text-align: center;
  color: #c0c4cc;
}
.chat-list-item {
  cursor: pointer;
  padding: 8px 0;
  transition: all 0.3s;
  border-bottom: 1px solid #ebeef5;
  box-sizing: border-box;
  display: flex;
  position: relative;
}
.border-top {
  border-top: 1px solid #ebeef5;
}
.item-close {
  position: absolute;
  top: 2px;
  right: 8px;
  visibility: hidden;
  opacity: 0;
  transition: all 0.3s;
  z-index: 99999;
}
.chat-list-item:hover {
  background-color: #f2f5fa;
}
.chat-list-item:hover .item-close {
  visibility: visible;
  opacity: 1;
}
.chat-idPhoto {
  width: 70px;
  height: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.chat-company-item {
  height: 60px;
  flex: 1;
  padding: 0 10px 0 5px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}
.chat-company-item-name {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 18px;
  font-weight: bold;
  z-index: 99;
}
.chat-company-item-tip {
  flex: 1;
  display: flex;
  align-items: center;
  color: #909399;
}
.chat-company-item-tip > p {
  flex: 1;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  padding-right: 10px;
  font-size: 14px;
  z-index: 0;
  height: 20px;
}
.chat-company-item-tip > span {
  font-size: 12px;
}
</style>