<template>
  <div class="index">
    <!-- <div class="header">
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
    </div> -->
    <Header/>
    <div class="body">
      <el-row :gutter="20" style="width: 1000px;margin: 0 auto;padding:50px 0;">
        <!-- -------------------------------------- 招聘会列表 ------------------------------------------------ -->
        <el-col :xs="24" :sm="24" :md="14" :lg="14" :xl="14">
          <div class="grid-content" v-if="dataList && dataList.length>0">
            <el-card shadow="hover">
              <div class="clearfix">
                <span>{{dataList[0].title}}</span>
                <el-button
                  style="float: right; padding: 3px 0"
                  type="text"
                  @click="$router.push({ path: '/detail' })"
                >查看详情</el-button>
              </div>
            </el-card>
            <el-divider content-position="left">以往</el-divider>
            <el-card
              shadow="hover"
              v-for="(item, index) in dataList.slice(1, this.dataList.length)"
              :key="index"
            >
              <span>{{item.title}}</span>
            </el-card>
          </div>
          <div class="grid-content" v-else>
            <el-card shadow="hover">
              <span>暂无招聘会信息</span>
            </el-card>
          </div>
        </el-col>
        <!-- -------------------------------------- 企业注册 ------------------------------------------------ -->
        <el-col :xs="24" :sm="24" :md="10" :lg="10" :xl="10">
          <el-card shadow="hover" class="grid-content register">
            <h2>企业注册</h2>
            <div style="margin-top: 15px;">
              <el-input placeholder="请输入常用手机号" v-model="registerPhone" />
            </div>
            <div style="margin-top: 15px;">
              <el-input placeholder="请输入手机验证码" v-model="registerCoded">
                <el-button slot="append" :loading="false">获取验证码</el-button>
              </el-input>
            </div>
            <el-button type="primary" plain style="margin-top: 15px;width:100%">立即注册</el-button>
            <p style="margin-top: 15px;color: #909399;">
              注册即代表您已同意
              <router-link to="/" style="color: rgb(64, 158, 255)">XXX协议</router-link>
            </p>
            <p style="margin-top: 15px;">
              <router-link
                to="/login"
                style="text-decoration:underline;color: rgb(64, 158, 255)"
              >已有账号，直接登录</router-link>
            </p>
          </el-card>
        </el-col>
      </el-row>
      <!-- -------------------------- 校企合作 ------------------------------- -->
      <el-row style="background: rgb(246, 246, 248);margin:0;padding:50px 0;">
        <el-row :gutter="20" style="width:1000px;margin:0 auto;">
          <el-col class="xq-title">
            <span>校企合作</span>
          </el-col>
        </el-row>
        <el-row :gutter="10" style="width:1000px;margin:40px auto 0 auto;" class="xq-content">
          <el-col :span="6" v-for="(item, index) in companyList" :key="index">
            <el-card shadow="hover">
              <img :src="item.logoUrl" class="company-logo" />
              <p style="margin-top:10px;text-align:center;">{{item.companyName}}</p>
              <p style="margin-top:10px;text-align:center;color:#898e9d">{{item.industry}}</p>
              <div style="margin:10px auto 0 auto;text-align:center;">
                <el-button>{{item.jobs}}个热招职位</el-button>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <el-row type="flex" justify="center" style="width:1000px;margin:20px auto 0 auto;">
          <el-button type="primary" plain>换一批</el-button>
        </el-row>
      </el-row>
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
    return {
      registerPhone: "",
      registerCoded: "",
      dataList: [
        {
          id: 1,
          title: "2019-2020 春季招聘会公告",
          context: "",
          startTime: "",
          endTime: ""
        },
        {
          id: 2,
          title: "2019-2020 秋季招聘会公告",
          context: "",
          startTime: "",
          endTime: ""
        },
        {
          id: 3,
          title: "2018-2019 春季招聘会公告",
          context: "",
          startTime: "",
          endTime: ""
        },
        {
          id: 4,
          title: "2018-2019 秋季招聘会公告",
          context: "",
          startTime: "",
          endTime: ""
        },
        {
          id: 5,
          title: "2017-2018 春季招聘会公告",
          context: "",
          startTime: "",
          endTime: ""
        }
      ],
      companyList: [
        {
          id: 1,
          logoUrl:
            "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg",
          companyName: "某某公司1",
          industry: "互联网",
          jobs: 58
        },
        {
          id: 2,
          logoUrl:
            "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg",
          companyName: "某某公司2",
          industry: "互联网",
          jobs: 58
        },
        {
          id: 3,
          logoUrl:
            "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg",
          companyName: "某某公司3",
          industry: "互联网",
          jobs: 58
        },
        {
          id: 4,
          logoUrl:
            "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg",
          companyName: "某某公司4",
          industry: "互联网",
          jobs: 58
        },
        {
          id: 5,
          logoUrl:
            "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg",
          companyName: "某某公司5",
          industry: "教育",
          jobs: 58
        },
        {
          id: 6,
          logoUrl:
            "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg",
          companyName: "某某公司6",
          industry: "计算机软件",
          jobs: 58
        },
        {
          id: 7,
          logoUrl:
            "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg",
          companyName: "某某公司7",
          industry: "移动互联网",
          jobs: 58
        },
        {
          id: 8,
          logoUrl:
            "https://crim-img.oss-cn-beijing.aliyuncs.com/images/company-logo.jpeg",
          companyName: "某某公司8",
          industry: "互联网",
          jobs: 99
        }
      ]
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {}
};
</script>
<style scoped>
.index {
  height: 100%;
}
/* .header {
  background: rgb(64, 158, 255);
  padding-top: 3px;
}
.logo {
  width: 1000px;
  margin: 0 auto;
} */
.el-card {
  margin-bottom: 10px;
}
.grid-content {
  min-height: 393px;
}
.register {
  text-align: center;
  padding: 0 20px;
}
.xq-title {
  text-align: center;
}
.xq-title > span {
  position: relative;
  font-size: 30px;
}
.xq-title > span::before {
  content: "";
  width: 150px;
  border-top: 1px solid #cccccc;
  position: absolute;
  top: 50%;
  left: -180px;
}
.xq-title > span::after {
  content: "";
  width: 150px;
  border-top: 1px solid #cccccc;
  position: absolute;
  top: 50%;
  right: -180px;
}
.company-logo {
  width: 60px;
  height: 60px;
  display: block;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 2px;
  margin: 0 auto;
}
</style>