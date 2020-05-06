<template>
  <div class="pageMain">
    <el-steps :active="active" finish-status="success" align-center>
      <el-step title="基础信息"></el-step>
      <el-step title="企业介绍"></el-step>
      <el-step title="营业执照"></el-step>
    </el-steps>
    <el-row class="padding-15">
      <el-col v-if="active===0">
        <el-form :model="company">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-form-item label="公司名称">
                <el-input v-model="company.companyName" placeholder="例如：中国移动"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="法人代表">
                <el-input v-model="company.legalMan" placeholder="例如：张xx"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="注册资本">
                <el-input v-model="company.registeredCapital" placeholder="例如：5000.00万元人民币"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="成立时间">
                <br />
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
            <el-col :span="8">
              <el-form-item label="企业联系人">
                <el-input v-model="company.linkMan" placeholder="例如：张xx"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="联系电话">
                <el-input v-model="company.linkPhone" placeholder="例如：18888888888"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-col>
      <el-col v-if="active===1">
        <el-form :model="company">
          <el-row :gutter="20">
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
          </el-row>
        </el-form>
      </el-col>
      <el-col v-if="active===2" class="crim-flex crim-flex-j-center">
        <div>
          <el-upload
            class="avatar-uploader"
            accept=".jpg, .jpeg"
            action="http://localhost:9999/api/v1/file/license"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :on-error="handleAvatarError"
            :before-upload="beforeAvatarUpload"
            :headers="headers"
          >
            <img v-if="company.licenseUrl" :src="company.licenseUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
      </el-col>
      <!--  -->
      <el-col v-if="active===3" style="height:200px" class="text-center">
        <el-image :src="okImg" fit="scale-down" style="height:100%"></el-image>
      </el-col>
    </el-row>
    <el-row class="text-center">
      <el-button @click="prev" v-if="active>0&&active<=2">上一步</el-button>
      <el-button type="primary" @click="save" v-if="active<2">保存并下一步</el-button>
      <el-button type="primary" @click="submitAudit" v-if="active===2">提交</el-button>
    </el-row>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      okImg: require("@/assets/img/ok.jpg"),
      active: 0,
      company: {
        id: "",
        companyName: "",
        legalMan: "",
        registeredCapital: "",
        registeredDate: "",
        businessStatus: "",
        companyType: "",
        registeredPlace: "",
        companyAddress: "",
        creditCode: "",
        businessScope: "",
        companyDesc: "",
        licenseUrl: "",
        linkPhone: "",
        linkMan: ""
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
    prev() {
      this.active--;
    },
    next() {
      this.active++;
    },
    init() {
      this.getCompanyInfo().then(() => {
        if (this.company.status === 1) {
          this.active = 3;
        }
      });
    },
    async getCompanyInfo() {
      await this.$get("/company/info")
        .then(res => {
          if (res.data.ok) {
            this.company = res.data.data;
            if (
              this.company.businessScope &&
              this.company.businessScope !== null
            ) {
              this.company.businessScope = this.parseTextarea(
                this.company.businessScope
              );
            }
            if (this.company.companyDesc && this.company.companyDesc !== null) {
              this.company.companyDesc = this.parseTextarea(
                this.company.companyDesc
              );
            }
          }
        })
        .catch(e => {});
    },
    save() {
      if (this.company.businessScope && this.company.businessScope !== null) {
        this.company.businessScope = this.transTextarea(
          this.company.businessScope
        );
      }
      if (this.company.companyDesc && this.company.companyDesc !== null) {
        this.company.companyDesc = this.transTextarea(this.company.companyDesc);
      }
      this.$post("/company/save", this.company)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getCompanyInfo();
            this.next();
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
        this.getCompanyInfo();
      } else {
        this.$message.error(res.msg);
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传LOGO图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传LOGO图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    handleAvatarError(err, file) {
      this.$message.error("服务器繁忙，请稍后重试");
    },
    submitAudit() {
      this.$get("/auditRecord/register")
        .then(res => {
          this.$message({
            message: "提交成功，等待审核",
            type: "success"
          });
          this.next();
        })
        .catch(e => {});
    }
  }
};
</script>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>