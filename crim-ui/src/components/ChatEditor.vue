<template>
    <div id="wangeditor">
      <div ref="chatEditorElem" style=" border:none;background:#FFF;"></div>
      <div ref="chatEditorElem2" style="text-align:left;height: 100%;"></div>
    </div>
</template>
<script>
import E from "wangeditor";
export default {
  name: "Editor",
  data() {
    return {
      editor: null,
      editorContent: ""
    };
  },
  // catchData是一个类似回调函数，来自父组件，当然也可以自己写一个函数，主要是用来获取富文本编辑器中的html内容用来传递给服务端
  props: ["catchData", "placeholder"], // 接收父组件的方法
  mounted() {
    // this.editor = new E(this.$refs.chatEditorElem);
    this.editor = new E(this.$refs.chatEditorElem, this.$refs.chatEditorElem2);
    // 编辑器的事件，每次改变会获取其html内容
    this.editor.customConfig.onchange = html => {
      this.editorContent = html;
      // this.catchData(this.editorContent);
      this.$emit("editorContent", this.editorContent); // 把这个html通过emit传入父组件
    };
    this.editor.customConfig.menus = [];
    this.editor.create(); // 创建富文本实例
    this.editor.txt.html(
      "<p>" + this.placeholder ? this.placeholder : "" + "</p>"
    );
  },
  methods: {
    clear() {
      this.editor.txt.clear();
    }
  }
};
</script>
<style scoped>
#wangeditor {
  height: 100%;
}
</style>