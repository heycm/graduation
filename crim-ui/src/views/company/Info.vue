<template>
  <div class="pageMain">
    <part title="基本信息" type="main" />
    <el-row>
      <el-col :span="6" class="paddingLR-15">
        <div class="demo-image marginB-5">
          <el-image class="info_logo_img" :src="baseInfo.logoUrl" fit="scale-down"></el-image>
        </div>
      </el-col>
      <el-col :span="18" class="paddingLR-15">
        <h1 class="marginB-15">{{baseInfo.companyName}}</h1>
        <div class="marginB-30">
          <h4 class="marginB-15">公司简介</h4>
          <div class="info_jianjie_content" v-html="baseInfo.companyDesc"></div>
        </div>
        <div class="marginB-30">
          <h4 class="marginB-15">公司地址</h4>
          <div class="info_jianjie_content marginB-15">{{baseInfo.companyAddress}}</div>
        </div>
        <div class="marginB-30">
          <h4 class="marginB-15">联系方式</h4>
          <div
            class="info_jianjie_content marginB-15"
          >{{baseInfo.linkMan}}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{baseInfo.linkPhone}}</div>
        </div>
      </el-col>
    </el-row>
    <part title="工商信息" type="main" />
    <el-row class="paddingLR-15 marginB-30">
      <div class="info_jianjie_content marginB-15">
        <el-row :gutter="10">
          <el-col class="marginB-10" :span="6">
            <span class="com_info_gs_title">法人代表：</span>
            {{baseInfo.legalMan}}
          </el-col>
          <el-col class="marginB-10" :span="6">
            <span class="com_info_gs_title">注册资本：</span>
            {{baseInfo.registeredCapital}}
          </el-col>
          <el-col class="marginB-10" :span="6">
            <span class="com_info_gs_title">成立时间：</span>
            {{baseInfo.registeredDateString}}
          </el-col>
          <el-col class="marginB-10" :span="6">
            <span class="com_info_gs_title">经营状态：</span>
            {{baseInfo.businessStatus}}
          </el-col>
          <el-col class="marginB-10" :span="12">
            <span class="com_info_gs_title">企业类型：</span>
            {{baseInfo.companyType}}
          </el-col>
          <el-col class="marginB-10" :span="12">
            <span class="com_info_gs_title">注册地址：</span>
            {{baseInfo.registeredPlace}}
          </el-col>
          <el-col class="marginB-10" :span="24">
            <span class="com_info_gs_title">统一信用代码：</span>
            {{baseInfo.creditCode}}
          </el-col>
        </el-row>
        <el-row>
          <span class="com_info_gs_title">经营范围：</span>
          <div v-html="baseInfo.businessScope"></div>
        </el-row>
      </div>
    </el-row>
    <part title="在招职位" type="main" />
    <el-row class="paddingLR-15 marginB-30">
      <div class="info_jianjie_content marginB-15">
        <el-row type="flex" :gutter="10">
          <el-col class="marginB-10" :span="8" v-for="position in positionList" :key="position.id">
            <el-card class="com_info_position_card" shadow="never">
              <div>
                <div class="com_info_position_name">
                  <h4>
                    {{position.jobName}}&nbsp;&nbsp;&nbsp;&nbsp;
                    <span
                      class="com_info_position_label"
                    >[{{position.workPlace}}]</span>
                  </h4>
                  <i class="el-icon-chat-dot-round" v-if="role!=='company'"></i>
                </div>
                <div class="marginB-10">
                  <span class="com_info_position_pay">{{position.regularPay}}</span>
                  <el-divider direction="vertical" />
                  <span class="com_info_position_label">{{position.experience}}</span>
                  <el-divider direction="vertical" />
                  <span class="com_info_position_label">{{position.certificate}}</span>
                </div>
                <div class="marginB-10">
                  <h4 class="com_info_position_desc">职位描述</h4>
                  <div class="com_info_position_desc_content paddingL-15" v-html="position.jobDesc"></div>
                </div>
                <div>
                  <h4 class="com_info_position_desc">技能要求</h4>
                  <div class="padding-15">
                    <el-tag
                      type="info"
                      class="marginB-5 marginR-5"
                      v-for="(skill, index) in position.skills"
                      :key="index"
                    >{{skill}}</el-tag>
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-row>
    <el-row class="padding-15 marginT-15 text-center" v-if="role === 'company'">
      <el-button type="primary" plain @click="toEdit">编辑信息</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      role: "company",
      baseInfo: {
        id: "",
        companyName: "",
        linkMan: "",
        linkPhone: "",
        legalMan: "",
        registeredCapital: "",
        registeredDate: "",
        registeredDateString: "",
        registeredPlace: "",
        businessStatus: "",
        companyType: "",
        companyAddress: "",
        creditCode: "",
        businessScope: "",
        companyDesc: "",
        status: "",
        licenseUrl: "",
        logoUrl: ""
      },
      positionList: []
    };
  },
  computed: {},
  watch: {},
  created() {
    if (this.getUserInfo().isAudit === 1) {
      this.$router.push({ path: `/${this.getUserInfo().roles[0]}/register` });
    }
    this.init();
  },
  methods: {
    toEdit() {
      this.$router.push({ path: "/company/info/edit" });
    },
    init() {
      this.role = this.getUserInfo().roles[0];
      this.getBaseInfo();
      this.getPositionList();
    },
    getBaseInfo() {
      this.$get("/company/info")
        .then(res => {
          if (res.data.ok) {
            this.baseInfo = res.data.data;
          }
        })
        .catch(e => {});
    },
    getPositionList() {
      this.$get("/company/position")
        .then(res => {
          if (res.data.ok) {
            this.positionList = res.data.data;
          }
        })
        .catch(e => {});
    }
  }
};
</script>