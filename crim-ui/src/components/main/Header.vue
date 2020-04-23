<template>
   <div class="header">
       <el-row type="flex" class="logo" justify="space-between" align="middle">
        <el-col>
          <a href="http://www.hevttc.edu.cn/" target="_blank">
            <img src="../../assets/img/logo.png" />
          </a>
        </el-col>
        <el-col>
          <el-button
            size="small"
            style="float:right;"
            @click="$router.push({ path: '/login' })"
          >Sign in</el-button>
        </el-col>
      </el-row>
   </div>
</template>

<script>
export default {
   components: {},
   data() {
       return {};
   },
   computed: {},
   watch: {},
   created() {},
   methods: {},
}
</script>
<style scoped>
.header {
  background: rgb(64, 158, 255);
  padding-top: 3px;
}
.logo {
  width: 1000px;
  margin: 0 auto;
}
</style>