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
            <ChatPanel :myId="myId" :friendId="friendId" :friendName="friendName" :msgList="chatList" @sendMsg="sendMsg" />
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
      myId: null,
      myIdPhoto: null,
      friendId: null,
      friendIdPhoto: null,
      friendName: null,
      chatList: [
        {
          id: 1,
          senderId: 12,
          senderName: "爱奇艺",
          senderIdPhoto:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587384568160&di=718b397ab66ccd6deb044a897b20b3d0&imgtype=0&src=http%3A%2F%2Fwww.szbacia.com%2Fprivate%2Fnewsimgs%2Fe12c43c325791eae.png",
          receiverId: 13,
          receiverName: "阿里巴巴",
          receiverIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          content: "爱奇艺发消息给阿里巴巴 ",
          sendTime: "01:57:07"
        },
        {
          id: 2,
          senderId: 13,
          senderName: "阿里巴巴",
          senderIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          receiverId: 12,
          receiverName: "爱奇艺",
          receiverIdPhoto:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587384568160&di=718b397ab66ccd6deb044a897b20b3d0&imgtype=0&src=http%3A%2F%2Fwww.szbacia.com%2Fprivate%2Fnewsimgs%2Fe12c43c325791eae.png",
          content: "阿里巴巴发消息给爱奇艺",
          sendTime: "23:57:07"
        },
        {
          id: 18,
          senderId: 13,
          senderName: "阿里巴巴",
          senderIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          receiverId: 14,
          receiverName: "字节跳动",
          receiverIdPhoto:
            "http://img0.imgtn.bdimg.com/it/u=4200502629,1250847505&fm=26&gp=0.jpg",
          content: "阿里巴巴发消息给字节跳动",
          sendTime: "23:57:07"
        },
        {
          id: 3,
          senderId: 14,
          senderName: "字节跳动",
          senderIdPhoto:
            "http://img0.imgtn.bdimg.com/it/u=4200502629,1250847505&fm=26&gp=0.jpg",
          receiverId: 13,
          receiverName: "阿里巴巴",
          receiverIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          content: "消息内容 消息内容 消息内容 消息内容 消息内容 ",
          sendTime: "01:57:07"
        },
        {
          id: 4,
          senderId: 15,
          senderName: "美团",
          senderIdPhoto:
            "http://i.gongxiao8.com/uploads/i_5_1589736371x3099320422_26.jpg",
          receiverId: 13,
          receiverName: "阿里巴巴",
          receiverIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          content: "消息内容 消息内容 消息内容 消息内容 消息内容 ",
          sendTime: "01:57:07"
        },
        {
          id: 5,
          senderId: 16,
          senderName: "饿了么",
          senderIdPhoto:
            "http://a1624.phobos.apple.com/us/r30/Purple3/v4/00/2d/b0/002db0a6-5d66-412d-9ea1-738186f39abc/mzl.cnxocuip.png",
          receiverId: 13,
          receiverName: "阿里巴巴",
          receiverIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          content: "消息内容 消息内容 消息内容 消息内容 消息内容 ",
          sendTime: "01:57:07"
        },
        {
          id: 6,
          senderId: 17,
          senderName: "百度",
          senderIdPhoto:
            "http://bpic.588ku.com/element_origin_min_pic/01/59/64/82574866cc0fadd.jpg",
          receiverId: 13,
          receiverName: "阿里巴巴",
          receiverIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          content: "消息内容 消息内容 消息内容 消息内容 消息内容 ",
          sendTime: "01:57:07"
        },
        {
          id: 7,
          senderId: 18,
          senderName: "腾讯",
          senderIdPhoto:
            "http://a.hiphotos.baidu.com/baike/pic/item/b90e7bec54e736d1bf865c3897504fc2d562696d.jpg",
          receiverId: 13,
          receiverName: "阿里巴巴",
          receiverIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          content: "消息内容 消息内容 消息内容 消息内容 消息内容 ",
          sendTime: "01:57:07"
        },
        {
          id: 8,
          senderId: 19,
          senderName: "华为",
          senderIdPhoto:
            "http://5b0988e595225.cdn.sohucs.com/q_70,c_zoom,w_640/images/20170831/f45a51010eaa482985dfd99d50c0a6c0.jpeg",
          receiverId: 13,
          receiverName: "阿里巴巴",
          receiverIdPhoto:
            "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
          content: "消息内容 消息内容 消息内容 消息内容 消息内容 ",
          sendTime: "01:57:07"
        }
      ]
    };
  },
  computed: {},
  watch: {},
  created() {
    this.myId = 13;
  },
  methods: {
    // 关闭聊天对象
    closeChat(chat) {
      console.log(chat);
      this.chatList.splice(this.chatList.indexOf(chat), 1);
      this.friendId = null;
      this.friendName = null;
    },
    // 选择聊天对象
    chooseChat(chat) {
      console.log(chat)
      this.friendId = chat.senderId;
      this.friendName = chat.senderName;
      this.friendIdPhoto = chat.senderIdPhoto;
      this.myIdPhoto = chat.receiverIdPhoto;
    },
    sendMsg(chat) {
      chat.senderIdPhoto = this.myIdPhoto;
      chat.receiverName = this.friendName;
      chat.receiverIdPhoto = this.friendIdPhoto;
      console.log(chat);
      let item = new Object();
      item = this.copyObj(chat);
      this.chatList = this.chatList.concat(item);
      console.log(this.chatList);
    }
  }
};
</script>