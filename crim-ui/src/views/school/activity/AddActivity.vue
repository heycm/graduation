<template>
  <div class="pageMain">
    <part title="编辑预览" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <div class="add_act_preview">
        <div v-if="showPreview" v-html="preview">{{preview}}</div>
        <h1 v-if="activity.title">{{activity.title}}</h1>
        <div v-html="activity.editorContent">{{activity.editorContent}}</div>
      </div>
    </el-row>
    <part title="标题" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-input v-model="activity.title" placeholder="请输入标题"></el-input>
    </el-row>
    <part title="内容" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <WangEditor @editorContent="getEditorContent" ref="editor" placeholder="请编辑活动内容..." />
    </el-row>
    <part title="活动时间" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-date-picker
        v-model="datePicker"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
      ></el-date-picker>
    </el-row>
    <part title="附件" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <el-col :span="6">
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
        >
          <el-button type="primary">点击上传</el-button>
        </el-upload>
      </el-col>
    </el-row>
    <el-row class="paddingLR-15 marginB-15 text-center">
      <el-button type="warning" @click="reset">重置</el-button>
      <el-button type="primary">发布</el-button>
      <el-button type="primary" plain>保存</el-button>
    </el-row>
  </div>
</template>

<script>
import WangEditor from "@/components/WangEditor";
export default {
  components: { WangEditor },
  data() {
    return {
      datePicker: "",
      preview: '<p style="color:#ccc;text-align: center;">这里是预览区...</p>',
      showPreview: true,
      activity: {
        title: "",
        editorContent: ""
      },
      options: [
        {
          value: "选项1",
          label: "黄金糕"
        },
        {
          value: "选项2",
          label: "双皮奶"
        },
        {
          value: "选项3",
          label: "蚵仔煎"
        },
        {
          value: "选项4",
          label: "龙须面"
        },
        {
          value: "选项5",
          label: "北京烤鸭"
        }
      ],
      value: "",
      fileList: [
        {
          name: "food.jpeg",
          url:
            "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100"
        },
        {
          name: "food2.jpeg",
          url:
            "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100"
        }
      ]
    };
  },
  computed: {},
  watch: {
    activity: {
      handler(val) {
        this.showPreview = false;
      },
      deep: true //对象内部的属性监听，也叫深度监听
      // immediate: true // 监听初始值
    }
  },
  created() {},
  methods: {
    getEditorContent(data) {
      console.log(data);
      this.activity.editorContent = data;
    },
    reset() {
      this.$refs.editor.clear();
      this.editorContent = "";
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 3 个文件，本次选择了 ${
          files.length
        } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    }
  }
};
</script>