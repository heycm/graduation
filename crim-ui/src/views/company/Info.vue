<template>
  <div class="pageMain">
    <part title="基本信息" type="main" />
    <el-row>
      <el-col :span="6" class="paddingLR-15">
        <div class="demo-image marginB-5">
          <el-image class="info_logo_img" :src="url" fit="scale-down"></el-image>
        </div>
      </el-col>
      <el-col :span="18" class="paddingLR-15">
        <h1 class="marginB-15">阿里巴巴</h1>
        <div class="marginB-30">
          <h4 class="marginB-15">公司简介</h4>
          <div class="info_jianjie_content">
            <p>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;河北科技师范学院是一所具有硕士学位授予权的
              省属普通高等学校，坐落于滨海旅
              游城市——秦皇岛市。学校始建于1941年，1975年开始举办高等教育，1977年开始招收本科生，2006年
              获得硕士学位授予权。学校是教育部首批全国重点建设职教师资培养培训基地、科技部国家级科技特派
              员创业培训基地、农业部现代农业技术培训基地、中国科协首批全国科普教育基地，是河北省首批转型发
              展试点院校、河北省创新创业教育示范高校。
            </p>
          </div>
        </div>
        <div class="marginB-30">
          <h4 class="marginB-15">公司地址</h4>
          <div class="info_jianjie_content marginB-15">杭州市 西湖区 阿里巴巴飞天园区</div>
        </div>
      </el-col>
    </el-row>
    <part title="工商信息" type="main" />
    <el-row class="paddingLR-15 marginB-30">
      <div class="info_jianjie_content marginB-15">
        <el-row :gutter="10">
          <el-col class="marginB-10" :span="6">
            <span class="com_info_gs_title">法人代表：</span>刘天文
          </el-col>
          <el-col class="marginB-10" :span="6">
            <span class="com_info_gs_title">注册资本：</span>3856.8758万美元
          </el-col>
          <el-col class="marginB-10" :span="6">
            <span class="com_info_gs_title">成立时间：</span>2005-11-04
          </el-col>
          <el-col class="marginB-10" :span="6">
            <span class="com_info_gs_title">经营状态：</span>开业
          </el-col>
          <el-col class="marginB-10" :span="12">
            <span class="com_info_gs_title">企业类型：</span>有限责任公司（中外合资）
          </el-col>
          <el-col class="marginB-10" :span="12">
            <span class="com_info_gs_title">注册地址：</span>北京市海淀区西北旺东路10号院东区16号楼5层502
          </el-col>
          <el-col class="marginB-10" :span="24">
            <span class="com_info_gs_title">统一信用代码：</span>91110108781703664R
          </el-col>
        </el-row>
        <el-row>
          <span class="com_info_gs_title">经营范围：</span>
          计算机技术开发、技术服务、技术培训、技术咨询、计算机软硬件开发和
          批发（涉及配额许可证管理、专项规定管理的商品按照国家有关规定办理）；货物进
          出口、技术进出口、代理进出口（国家限定公司经营或禁止进出口的商品和技术除外
          ）；计算机系统服务；承办展览展示活动；旅游信息咨询；安全技术防范产品设计；
          出租办公用房；出租商业用房；销售医疗器械Ⅱ类。（依法须经批准的项目，经相关
          部门批准后依批准的内容开展经营活动。）
        </el-row>
      </div>
    </el-row>
    <part title="在招职位" type="main" />
    <el-row class="paddingLR-15 marginB-30">
      <div class="info_jianjie_content marginB-15">
        <el-row :gutter="10">
          <el-col class="marginB-10" :span="8" v-for="position in positionList" :key="position.id">
            <el-card class="com_info_position_card" shadow="never">
              <div @click="showPositionDetail(position)">
                <div class="com_info_position_name">
                  <h4>
                    {{position.positionName}}&nbsp;&nbsp;&nbsp;&nbsp;
                    <span
                      class="com_info_position_label"
                    >[{{position.workPlace}}]</span>
                  </h4>
                  <i class="el-icon-chat-dot-round"></i>
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
                  <div class="com_info_position_desc_content paddingL-15">
                    <p>全日制统招本科</p>
                    <p>熟悉JAVA开发，熟悉spring、spring mvc、spring cloud、spring boot等开发框架</p>
                    <p>了解前端开发技术</p>
                  </div>
                </div>
                <div>
                  <h4 class="com_info_position_desc">技能要求</h4>
                  <div class="padding-15">
                    <el-tag type="info" class="marginB-5">Java</el-tag>
                    <el-tag type="info" class="marginB-5">后端开发</el-tag>
                    <el-tag type="info" class="marginB-5">JavaScript</el-tag>
                    <el-tag type="info" class="marginB-5">Linux</el-tag>
                    <el-tag type="info" class="marginB-5">spring</el-tag>
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
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
        "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
      positionList: [
        {
          id: 1,
          positionName: "高级Java",
          workPlace: "北京",
          regularPay: "13-21K",
          experience: "应届生",
          certificate: "本科"
        },
        {
          id: 2,
          positionName: "Java",
          workPlace: "天津",
          regularPay: "13-21K",
          experience: "应届生",
          certificate: "本科"
        },
        {
          id: 3,
          positionName: "Java",
          workPlace: "武汉",
          regularPay: "13-21K",
          experience: "应届生",
          certificate: "本科"
        },
        {
          id: 4,
          positionName: "Java",
          workPlace: "重庆",
          regularPay: "13-21K",
          experience: "应届生",
          certificate: "本科"
        }
      ],
      positionDetail: true,
      dialogTitle: "",
      position: {
        id: "",
        positionName: "",
        workPlace: "",
        regularPay: "",
        experience: "",
        certificate: ""
      }
    };
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    toEdit() {
      this.$router.push({ path: "/company/info/edit" });
    }
  }
};
</script>