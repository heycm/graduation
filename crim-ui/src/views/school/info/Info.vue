<template>
  <div class="pageMain">
    <part title="基本信息" type="main" />
    <el-row type="flex">
      <el-col :span="6" class="paddingLR-15">
        <div class="demo-image marginB-5">
          <el-image class="info_logo_img" :src="url" fit="scale-down"></el-image>
        </div>
      </el-col>
      <el-col :span="18" class="paddingLR-15">
        <h1 class="marginB-15">河北科技师范学院</h1>
        <h4 class="marginB-5">简介：</h4>
        <div class="info_jianjie_content marginB-5">
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
            省属普通高等学校，坐落于滨海旅
            游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
            获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
            员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
            展试点院校、河北省创新创业教育示范高校。
          </p>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
            省属普通高等学校，坐落于滨海旅
            游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
            获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
            员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
            展试点院校、河北省创新创业教育示范高校。
          </p>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
            省属普通高等学校，坐落于滨海旅
            游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
            获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
            员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
            展试点院校、河北省创新创业教育示范高校。
          </p>
          <p>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
            省属普通高等学校，坐落于滨海旅
            游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
            获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
            员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
            展试点院校、河北省创新创业教育示范高校。
          </p>
        </div>
        <p>地址：哈哈哈哈哈哈哈&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮政编码：066004</p>
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
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    toEdit() {
      this.$router.push({ path: "/school/info/edit" })
    }
  }
};
</script>