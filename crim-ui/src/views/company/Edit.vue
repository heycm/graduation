<template>
  <div class="pageMain">
    <part title="基本信息" type="main" />
    <el-row class="marginB-15">
      <el-col :span="4" class="paddingLR-15">
        <div class="marginB-5">
          <el-image class="info_logo_img" :src="baseInfo.logoUrl" fit="scale-down"></el-image>
        </div>
        <el-upload
          class="upload-demo"
          accept=".jpg, .jpeg, .png, .gif"
          action="http://localhost:9999/api/v1/file/logo"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :on-error="handleAvatarError"
          :before-upload="beforeAvatarUpload"
          :headers="headers"
        >
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png/gif文件，且不超过2mb</div>
        </el-upload>
      </el-col>
      <el-col :span="20" class="paddingLR-15">
        <el-form :model="baseInfo">
          <el-row :gutter="10">
            <el-col :span="24">
              <el-form-item label="公司简介">
                <el-input
                  type="textarea"
                  :autosize="{minRows:4, maxRows:10}"
                  v-model="baseInfo.companyDesc"
                  placeholder="请输入内容"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="公司名称">
                <el-input v-model="baseInfo.companyName" placeholder="例如：中国移动"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="法人代表">
                <el-input v-model="baseInfo.legalMan" placeholder="例如：张xx"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="注册资本">
                <el-input v-model="baseInfo.registeredCapital" placeholder="例如：5000.00万元人民币"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="成立时间">
                <br />
                <el-date-picker v-model="baseInfo.registeredDate" type="date" placeholder="选择日期"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="经营状态">
                <el-input v-model="baseInfo.businessStatus" placeholder="例如：在营、开业、在册"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="企业类型">
                <el-input v-model="baseInfo.companyType" placeholder="例如：其他股份有限公司（非上市）"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="注册地址">
                <el-input v-model="baseInfo.registeredPlace" placeholder="例如：北京市xx区xx街道xx号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="公司地址">
                <el-input v-model="baseInfo.companyAddress" placeholder="例如：北京市xx区xx街道xx号"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="统一信用代码">
                <el-input v-model="baseInfo.creditCode" placeholder="例如：10000000XXXXXXXXXX"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item label="经营范围">
                <el-input
                  type="textarea"
                  :rows="4"
                  v-model="baseInfo.businessScope"
                  placeholder="请输入内容"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="联系人">
                <el-input v-model="baseInfo.linkMan" placeholder="例如：张xx"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="联系电话">
                <el-input v-model="baseInfo.linkPhone" placeholder="例如：18888888888"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item class="text-right">
                <br />
                <el-button type="primary" @click="save">保存</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-col>
    </el-row>
    <part title="招聘职位" type="main" />
    <div class="info_jianjie_content marginB-15">
      <el-row class="marginB-10 text-right">
        <el-button @click="editDialogVisible=true">增加职位</el-button>
      </el-row>
      <el-row :gutter="10">
        <el-col class="marginB-10" :span="12" v-for="position in positionList" :key="position.id">
          <el-card class="com_info_position_card" shadow="never">
            <div class="com_info_position_name">
              <h4>
                {{position.jobName}}&nbsp;&nbsp;&nbsp;&nbsp;
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
              <div class="com_info_position_desc_content paddingL-15" v-html="position.jobDesc"></div>
            </div>
            <div>
              <h4 class="com_info_position_desc">技能要求</h4>
              <div class="padding-15">
                <el-tag
                  type="info"
                  class="marginB-5 marginR-5"
                  v-for="(item, index) in position.skills"
                  :key="index"
                >{{item}}</el-tag>
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
              <el-input v-model="position.jobName" placeholder="例如：UI设计师"></el-input>
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
                v-model="position.jobDesc"
                placeholder="请输入内容"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="jobSaveOrUpdate">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import bus from '@/utils/bus'
export default {
  components: {},
  data() {
    return {
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
      positionList: [],
      editDialogVisible: false,
      position: {
        id: "",
        companyId: "",
        jobName: "",
        workPlace: "",
        regularPay: "",
        experience: "",
        certificate: "",
        skill: "",
        jobDesc: ""
      }
    };
  },
  computed: {
    headers() {
      return {
        Authorization: this.getUserInfo().token
      };
    }
  },
  watch: {},
  created() {
    this.init();
  },
  methods: {
    init() {
      this.getBaseInfo();
      this.getPositionList();
    },
    getBaseInfo() {
      this.$get("/company/info")
        .then(res => {
          if (res.data.ok) {
            this.baseInfo = res.data.data;
            this.baseInfo.businessScope = this.parseTextarea(
              this.baseInfo.businessScope
            );
            this.baseInfo.companyDesc = this.parseTextarea(
              this.baseInfo.companyDesc
            );
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
    },
    handleAvatarSuccess(res, file) {
      if (res.ok) {
        this.$message({
          message: "上传成功",
          type: "success"
        });
        this.getBaseInfo();
        bus.$emit("logoChange");
      } else {
        this.$message.error(res.msg);
      }
    },
    handleAvatarError(err, file) {
      this.$message.error("服务器繁忙，请稍后重试");
    },
    beforeAvatarUpload(file) {
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
      let company = this.copyObj(this.baseInfo);
      company.companyDesc = this.transTextarea(company.companyDesc);
      company.businessScope = this.transTextarea(company.businessScope);
      this.$post("/company/save", company).then(res => {
        if (res.data.ok) {
          this.$message({
            message: "保存成功",
            type: "success"
          });
          this.getBaseInfo();
        }
      });
    },
    openDditPositionDialog(position) {
      this.editDialogVisible = true;
      this.position = this.copyObj(position);
      this.position.jobDesc = this.parseTextarea(this.position.jobDesc);
    },
    // 关闭dialog回调
    afterCloseEditDialog() {
      this.position = this.clearObj(this.position);
    },
    jobSaveOrUpdate() {
      let job = this.position;
      this.editDialogVisible = false;
      job.companyId = this.baseInfo.id;
      job.jobDesc = this.transTextarea(job.jobDesc);
      this.$post("/jobVacancy/save", job)
        .then(res => {
          this.$message({
            message: "操作成功",
            type: "success"
          });
          this.getPositionList();
        })
        .catch(e => {});
    },
    delPosition(id) {
      // do delete
    }
  }
};
</script>