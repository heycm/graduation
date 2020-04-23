<template>
  <div>
    <Header />
    <div style="background:rgb(246,246,248);padding:40px 0px 0px 0px">
      <el-card class="context" shadow="never">
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <el-divider content-position="left">公告附件</el-divider>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
         <p>NoticeDetail</p>
      </el-card>
    </div>
    <Footer />
  </div>
</template>

<script>
import Footer from "@/components/main/WebsiteFooter";
import Header from "@/components/main/Header";
export default {
  components: {
    Footer: Footer,
    Header: Header
  },
  data() {
    return {};
  },
  computed: {},
  watch: {},
  created() {},
  methods: {}
};
</script>
<style scoped>
.context {
   width: 1000px;
   min-height: 460px;
   margin: 0 auto;
   border-radius: 0;
}
</style>