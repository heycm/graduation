<template>
  <div id="wangeditor">
    <!-- <div ref="editorElem" style="text-align:left;"></div> -->
    <div ref="editorElem" style=" border: 1px solid #ccc;background:rgb(241,241,241);"></div>
    <div ref="editorElem2" style="text-align:left;border: 1px solid #ccc;border-top:0px;
            height: 500px;">
    </div>
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
    // this.editor = new E(this.$refs.editorElem);
    this.editor = new E(this.$refs.editorElem, this.$refs.editorElem2);
    // 编辑器的事件，每次改变会获取其html内容
    this.editor.customConfig.onchange = html => {
      this.editorContent = html;
      // this.catchData(this.editorContent); 
      this.$emit('editorContent', this.editorContent); // 把这个html通过emit传入父组件
    };
    this.editor.customConfig.menus = [
      // 菜单配置
      "head", // 标题
      "bold", // 粗体
      "fontSize", // 字号
      "fontName", // 字体
      "italic", // 斜体
      "underline", // 下划线
      "strikeThrough", // 删除线
      "foreColor", // 文字颜色
      "backColor", // 背景颜色
      "link", // 插入链接
      "list", // 列表
      "justify", // 对齐方式
      "quote", // 引用
      "emoticon", // 表情
      "image", // 插入图片
      "table", // 表格
      "code", // 插入代码
      "undo", // 撤销
      "redo" // 重复
    ];
    this.editor.create(); // 创建富文本实例
    this.editor.txt.html('<p>'+this.placeholder ? this.placeholder : ''+'</p>')
  },
  methods: {
    clear() {
      this.editor.txt.clear()
    }
  }
};
</script>