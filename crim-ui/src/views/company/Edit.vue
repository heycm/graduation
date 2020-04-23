<template>
  <div class="pageMain">
    <part title="基本信息" type="main" />
    <el-row class="marginB-15">
      <el-col :span="4" class="paddingLR-15">
        <div class="marginB-5">
          <el-image class="info_logo_img" :src="company.companyLogoUrl" fit="scale-down"></el-image>
        </div>
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :on-error="handleAvatarError"
          :before-upload="beforeAvatarUpload"
          :headers="headers"
        >
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/jpeg/png/gif文件，且不超过2mb</div>
        </el-upload>
      </el-col>
      <el-col :span="20" class="paddingLR-15">
        <el-form :model="company">
          <el-row :gutter="10">
            <el-col :span="24">
              <el-form-item label="公司简介">
                <el-input
                  type="textarea"
                  :autosize="{minRows:4, maxRows:10}"
                  v-model="company.companyDesc"
                  placeholder="请输入内容"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="公司名称">
                <el-input v-model="company.companyName" placeholder="例如：中国移动"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="法人代表">
                <el-input v-model="company.legalPerson" placeholder="例如：张xx"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="注册资本">
                <el-input v-model="company.registeredCapital" placeholder="例如：5000.00万元人民币"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="成立时间">
                <br>
                <el-date-picker v-model="company.registeredDate" type="date" placeholder="选择日期"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="经营状态">
                <el-input v-model="company.businessStatus" placeholder="例如：在营、开业、在册"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="企业类型">
                <el-input v-model="company.companyType" placeholder="例如：其他股份有限公司（非上市）"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="注册地址">
                <el-input v-model="company.registeredPlace" placeholder="例如：北京市xx区xx街道xx号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="公司地址">
                <el-input v-model="company.companyAddress" placeholder="例如：北京市xx区xx街道xx号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="统一信用代码">
                <el-input v-model="company.creditCode" placeholder="例如：10000000XXXXXXXXXX"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="经营范围">
                <el-input
                  type="textarea"
                  :rows="4"
                  v-model="company.businessScope"
                  placeholder="请输入内容"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item class="text-right">
            <el-button type="primary" @click="save">保存</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
    <part title="招聘职位" type="main" />
    <div class="info_jianjie_content marginB-15">
      <el-row class="marginB-10 text-right">
        <el-button @click="editDialogVisible=true">增加职位</el-button>
      </el-row>
      <el-row :gutter="10">
        <el-col
          class="marginB-10"
          :span="12"
          v-for="position in company.positionList"
          :key="position.id"
        >
          <el-card class="com_info_position_card" shadow="never">
            <div>
              <div class="com_info_position_name">
                <h4>
                  {{position.positionName}}&nbsp;&nbsp;&nbsp;&nbsp;
                  <span
                    class="com_info_position_label"
                  >[{{position.workPlace}}]</span>
                </h4>
                <div>
                  <i class="el-icon-edit-outline" @click="openDditPositionDialog(position)"></i>
                  <i class="el-icon-delete" @click="delPosition(position.id)"></i>
                </div>
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
                <div
                  class="com_info_position_desc_content paddingL-15"
                  v-html="position.positionDesc"
                >{{position.positionDesc}}</div>
              </div>
              <div>
                <h4 class="com_info_position_desc">技能要求</h4>
                <div class="padding-15">
                  <el-tag
                    type="info"
                    class="marginB-5 marginR-5"
                    v-for="(item, index) in position.skillList"
                    :key="index"
                  >{{item}}</el-tag>
                </div>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 弹窗 -->
    <el-dialog title="编辑" :visible.sync="editDialogVisible" @close="afterCloseEditDialog">
      <el-form :model="position">
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="职位名称">
              <el-input v-model="position.positionName" placeholder="例如：UI设计师"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="工作地点">
              <el-input v-model="position.workPlace" placeholder="例如：北京"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="正式薪资">
              <el-input v-model="position.regularPay" placeholder="例如：10-12K"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="经验要求">
              <el-input v-model="position.experience" placeholder="例如：1-3年/应届生"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学历要求">
              <el-input v-model="position.certificate" placeholder="例如：本科"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="技能要求">
              <el-input v-model="position.skill" placeholder="例如：Java,Linux(多个之间英文逗号间隔)"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="职位描述">
              <el-input
                type="textarea"
                :autosize="{minRows:3}"
                v-model="position.positionDesc"
                placeholder="请输入内容"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      company: {
        id: "",
        companyLogoUrl:
          "http://img3.imgtn.bdimg.com/it/u=3307217202,934082925&fm=26&gp=0.jpg",
        companyName: "",
        legalPerson: "",
        registeredCapital: "",
        registeredDate: "",
        businessStatus: "",
        companyType: "",
        registeredPlace: "",
        companyAddress: "",
        creditCode: "",
        businessScope: "",
        companyDesc: "",
        positionList: [
          {
            id: 1,
            positionName: "高级Java",
            workPlace: "北京",
            regularPay: "13-21K",
            experience: "应届生",
            certificate: "本科",
            skill: "Java,后端开发,JavaScript,Linux,spring",
            skillList: ["Java", "后端开发", "JavaScript", "Linux", "spring"],
            positionDesc:
              "全日&nbsp;&nbsp;&nbsp;&nbsp;制统招本科<br>熟悉JAVA开发，熟悉spring、spring mvc、spring cloud、spring boot等开发框架<br>了解前端开发技术"
          },
          {
            id: 2,
            positionName: "Java",
            workPlace: "天津",
            regularPay: "13-21K",
            experience: "应届生",
            certificate: "本科",
            skill: "Java,后端开发,JavaScript,Linux,spring",
            skillList: ["Java", "后端开发", "JavaScript", "Linux", "spring"],
            positionDesc:
              "全日制统招本科<br>熟悉JAVA开发，熟悉spring、spring mvc、spring cloud、spring boot等开发框架<br>了解前端开发技术"
          },
          {
            id: 3,
            positionName: "Java",
            workPlace: "武汉",
            regularPay: "13-21K",
            experience: "应届生",
            certificate: "本科",
            skill: "Java,后端开发,JavaScript,Linux,spring",
            skillList: ["Java", "后端开发", "JavaScript", "Linux", "spring"],
            positionDesc:
              "全日制统招本科<br>熟悉JAVA开发，熟悉spring、spring mvc、spring cloud、spring boot等开发框架<br>了解前端开发技术"
          },
          {
            id: 4,
            positionName: "Java",
            workPlace: "重庆",
            regularPay: "13-21K",
            experience: "应届生",
            certificate: "本科",
            skill: "Java,后端开发,JavaScript,Linux,spring",
            skillList: ["Java", "后端开发", "JavaScript", "Linux", "spring"],
            positionDesc:
              "全日制统招本科<br>熟悉JAVA开发，熟悉spring、spring mvc、spring cloud、spring boot等开发框架<br>了解前端开发技术"
          }
        ]
      },
      editDialogVisible: false,
      position: {
        id: "",
        positionName: "",
        workPlace: "",
        regularPay: "",
        experience: "",
        certificate: "",
        skill: "",
        positionDesc: ""
      }
    };
  },
  computed: {
    headers() {
      return {
        // Authorization: localStorage.getItem("token")
        Authorization: "token"
      };
    }
  },
  watch: {},
  created() {},
  methods: {
    handleAvatarSuccess(res, file) {},
    handleAvatarError(err, file) {},
    beforeAvatarUpload(file) {
      console.log(file);
      // 图片格式
      let imgType = ["image/jpeg", "image/gif", "image/png"];
      const isJPG = imgType.indexOf(file.type) > -1;

      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("只能上传jpg/jpeg/png/gif文件");
      }
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过2MB!");
      }
      return isJPG && isLt2M;
    },
    save() {
      let company = this.copyObj(this.company);
      company.companyDesc = this.transTextarea(company.companyDesc);
      console.log(company);
    },
    openDditPositionDialog(position) {
      this.editDialogVisible = true;
      this.position = this.copyObj(position);
      this.position.positionDesc = this.parseTextarea(
        this.position.positionDesc
      );
    },
    // 关闭dialog回调
    afterCloseEditDialog() {
      this.position = this.clearObj(this.position);
    },
    delPosition(id) {
      // do delete
    }
  }
};
</script>