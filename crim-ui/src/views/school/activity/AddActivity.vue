<template>
  <div class="pageMain">
    <part title="编辑预览" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <div class="add_act_preview">
        <div v-if="showPreview" v-html="preview">{{preview}}</div>
        <h1 v-if="activity.jobFairTitle">{{activity.jobFairTitle}}</h1>
        <div v-html="activity.jobFairContent">{{activity.jobFairContent}}</div>
      </div>
    </el-row>
    <el-row :gutter="20">
      <el-col :span="12">
        <part title="标题" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-input v-model="activity.jobFairTitle" placeholder="请输入标题"></el-input>
        </el-row>
      </el-col>
      <el-col :span="12">
        <part title="年份" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-select v-model="activity.yearLevelId">
            <el-option
              v-for="year in yearList"
              :key="year.id"
              :label="year.yearName"
              :value="year.id"
            ></el-option>
          </el-select>
        </el-row>
      </el-col>
      <el-col :span="12">
        <part title="活动时间" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-date-picker
            v-model="activity.jarFairCycle"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-row>
      </el-col>
      <el-col :span="12">
        <part title="季度" type="main" />
        <el-row class="paddingLR-15 marginB-10">
          <el-select v-model="activity.quarter">
            <el-option label="秋季" :value="0"></el-option>
            <el-option label="春季" :value="1"></el-option>
          </el-select>
        </el-row>
      </el-col>
    </el-row>

    <part title="内容" type="main" />
    <el-row class="paddingLR-15 marginB-10">
      <WangEditor @editorContent="getEditorContent" ref="editor" placeholder="请编辑活动内容..." />
    </el-row>
    <el-row class="paddingLR-15 marginB-15 text-center">
      <el-button type="warning" @click="reset">内容重置</el-button>
      <el-button type="primary" @click="add('publish')">发布</el-button>
      <el-button type="primary" plain @click="add('save')">保存</el-button>
    </el-row>
  </div>
</template>

<script>
import WangEditor from "@/components/WangEditor";
export default {
  components: { WangEditor },
  data() {
    return {
      preview: '<p style="color:#ccc;text-align: center;">这里是预览区...</p>',
      showPreview: true,
      yearList: [
        {
          id: 8,
          yearName: "2020"
        }
      ],
      activity: {
        jobFairTitle: "",
        yearLevelId: "",
        quarter: "",
        jarFairCycle: [],
        startTime: "",
        endTime: "",
        jobFairContent: ""
      },
      
    };
  },
  computed: {
    headers() {
      return {
        Authorization: this.getUserInfo().token
      };
    }
  },
  watch: {
    activity: {
      handler(val) {
        this.showPreview = false;
      },
      deep: true //对象内部的属性监听，也叫深度监听
      // immediate: true // 监听初始值
    }
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      this.getYearList();
    },
    getYearList() {
      this.$get("/type/year/list")
        .then(res => {
          if (res.data.ok) {
            this.yearList = res.data.data;
          }
        })
        .catch(e => {});
    },
    getEditorContent(data) {
      this.activity.jobFairContent = data;
    },
    reset() {
      this.$refs.editor.clear();
      this.jobFairContent = "";
    },
    add(addType) {
      this.activity.startTime = this.activity.jarFairCycle[0];
      this.activity.endTime = this.activity.jarFairCycle[1];
      this.$post(`/jobFair/${addType}`, this.activity)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: addType === "publish" ? "发布成功" : "已保存",
              type: "success"
            });
            this.activity = this.clearObj(this.activity);
            this.reset();
          }
        })
        .catch(e => {});
    },
  }
};
</script>