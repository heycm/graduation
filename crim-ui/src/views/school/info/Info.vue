<template>
  <div class="pageMain">
    <part title="基本信息" type="main" />
    <el-row type="flex">
      <el-col :span="6" class="paddingLR-15">
        <div class="demo-image marginB-5">
          <el-image class="info_logo_img" :src="info.logoUrl" fit="scale-down"></el-image>
        </div>
      </el-col>
      <el-col :span="18" class="paddingLR-15">
        <h1 class="marginB-15">{{info.schoolName}}</h1>
        <h4 class="marginB-5">简介：</h4>
        <div class="info_jianjie_content marginB-5" v-html="info.schoolDesc">
          {{info.schoolDesc}}
        </div>
        <p>地址：{{info.schoolAddress}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮政编码：{{info.schoolPostalCode}}</p>
      </el-col>
    </el-row>
    <el-row class="padding-15 marginT-15 text-center">
      <el-button type="primary" plain @click="toEdit">编辑信息</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      url:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      info: {
        schoolName: "",
        schoolAddress: "",
        schoolPostalCode: "",
        schoolDesc: "",
        logoUrl: ""
      }
    };
  },
  computed: {},
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.$get("/school/info")
        .then(res => {
          if (res.data.ok) {
            this.info = res.data.data;
          }
        })
        .catch(() => {});
    },
    toEdit() {
      this.$router.push({ path: "/school/info/edit" });
    }
  }
};
</script>