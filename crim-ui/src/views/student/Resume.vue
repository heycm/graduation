<template>
  <div class>
    <el-row type="flex" :gutter="20">
      <el-col :span="isStudent?18:24">
        <div class="padding-15 bgFFF">
          <el-row type="flex" class="marginB-15" v-if="isStudent">
            <el-col>
              <part title="在线简历" type="main" />
            </el-col>
            <el-col class="text-right">
              <el-button size="mini">预览简历</el-button>
            </el-col>
          </el-row>
          <!-- 基本信息 -->
          <el-divider />
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="基本信息" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-edit-outline"
                @click="openDialogBaseInfo"
              >编辑</el-button>
            </el-col>
            <el-col :span="20" class="paddingLR-15">
              <h2 class="marginB-15 marginT-15">{{resume.name}}</h2>
              <div class="marginB-15 paddingL-20 stu_resume_jianli_main_color">
                <span>{{resume.sex === 1 ? '男' : resume.sex === 0 ? '女' : ''}}</span>
                <el-divider v-if="resume.birth" direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{resume.birth?resume.birth.split("-")[0] + '-' + resume.birth.split("-")[1]:''}}</span>
                <el-divider
                  v-if="resume.classLevel"
                  direction="vertical"
                  class="stu_resume_vertical"
                ></el-divider>
                <span v-if="resume.classLevel">{{resume.classLevel}} 届</span>
                <el-divider
                  v-if="resume.jobStatus !== null"
                  direction="vertical"
                  class="stu_resume_vertical"
                ></el-divider>
                <span>{{resume.jobStatus===0?'离职-随时到岗':resume.jobStatus===1?'在职-暂不考虑':resume.jobStatus===2?'在职-考虑机会':resume.jobStatus===3?'在职-月内到岗':''}}</span>
              </div>
              <div class="paddingL-20 stu_resume_jianli_main_color">
                <span>{{resume.phone}}</span>
                <el-divider v-if="resume.weixin" direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{resume.weixin}}</span>
                <el-divider v-if="resume.email" direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{resume.email}}</span>
              </div>
            </el-col>
            <el-col :span="4" class="paddingLR-15">
              <el-row type="flex" justify="end">
                <el-upload
                  class="upload-demo marginT-10"
                  accept=".jpg, .jpeg"
                  action="http://localhost:9999/api/v1/file/idPhoto"
                  :show-file-list="false"
                  :on-success="uploadIdPhotoSuccess"
                  :on-error="uploadIdPhotoError"
                  :before-upload="beforeUploadIdPhoto"
                  :headers="headers"
                  :disabled="!isStudent"
                >
                  <el-image class="stu_resume_zjz" :src="resume.idPhotoUrl" fit="fill">
                    <div slot="error" class="image-slot">
                      <p>点击上传</p>
                      <span>115 x 150</span>
                    </div>
                  </el-image>
                </el-upload>
              </el-row>
            </el-col>
          </el-row>
          <!-- 个人优势 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="个人优势" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-edit-outline"
                @click="openDialogPersonalAdvantage"
              >编辑</el-button>
            </el-col>
            <el-col :span="24" class="padding-15 stu_resume_jianli_main_color">
              <div v-html="resume.advantage"></div>
            </el-col>
          </el-row>
          <!-- 期望职位 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="期望职位" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="dialogExpectedPosition = true"
              >添加</el-button>
            </el-col>
            <el-col
              :span="24"
              class="padding-15 stu_resume_jianli_main_color resume_hover_out"
              v-for="item in resume.expectedPositionList"
              :key="item.id"
            >
              <div>
                <span>{{item.positionName}}</span>
                <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{item.expectedPay}}</span>
                <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{item.expectedTrade}}</span>
                <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{item.expectedCity}}</span>
              </div>
              <div class="resume_hover_in" v-if="isStudent">
                <el-button
                  @click="openDialogExpectedPosition(item)"
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                >编辑</el-button>
                <el-button
                  @click="delExpectedPosition(item.id)"
                  type="text"
                  style="padding:0"
                  icon="el-icon-delete"
                >删除</el-button>
              </div>
            </el-col>
          </el-row>
          <!-- 实习经历 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="实习经历" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="dialogInternshipExperience=true"
              >添加</el-button>
            </el-col>
            <el-col
              :span="24"
              class="padding-15 stu_resume_jianli_main_color resume_hover_out"
              v-for="item in resume.internshipExceptionList"
              :key="item.id"
            >
              <h3 class="marginB-15">
                {{item.companyName}}
                <span
                  class="stu_resume_practice_date"
                >{{item.jobStart}} - {{item.jobEnd}}</span>
              </h3>
              <h4 class="marginB-10">
                <div>
                  <span>{{item.affiliatedDept}}</span>
                  <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                  <span>{{item.positionName}}</span>
                  <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                  <span>{{item.companyTrade}}</span>
                </div>
              </h4>
              <div class="stu_resume_practice_content">
                <h4>内容：</h4>
                <div v-html="item.workContent"></div>
              </div>
              <div class="resume_hover_in" v-if="isStudent">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                  @click="openDialogInternshipExperience(item)"
                >编辑</el-button>
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-delete"
                  @click="delInternshipExperience(item.id)"
                >删除</el-button>
              </div>
            </el-col>
          </el-row>
          <!-- 项目经历 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="项目经历" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="dialogProjectExperience = true"
              >添加</el-button>
            </el-col>
            <el-col
              :span="24"
              class="padding-15 stu_resume_jianli_main_color resume_hover_out"
              v-for="item in resume.projectExperienceList"
              :key="item.id"
            >
              <h3 class="marginB-15">
                {{item.projectName}}
                <span
                  class="stu_resume_practice_date"
                >{{item.projectStart}} - {{item.projectEnd}}</span>
              </h3>
              <h4 class="marginB-10">{{item.projectRole}}</h4>
              <div class="marginB-10 stu_resume_practice_content">
                <h4>项目描述：</h4>
                <div v-html="item.projectDesc"></div>
              </div>
              <div class="stu_resume_practice_content">
                <h4>项目连接：</h4>
                {{item.projectLink}}
              </div>
              <div class="resume_hover_in" v-if="isStudent">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                  @click="openDialogProjectExperience(item)"
                >编辑</el-button>
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-delete"
                  @click="delProjectExperience(item.id)"
                >删除</el-button>
              </div>
            </el-col>
          </el-row>
          <!-- 教育经历 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="教育经历" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="dialogEducationExperience = true"
              >添加</el-button>
            </el-col>
            <el-col
              :span="24"
              class="padding-15 stu_resume_jianli_main_color resume_hover_out"
              v-for="item in resume.educationExperienceList"
              :key="item.id"
            >
              <h3 class="marginB-15">
                {{item.schoolName}}
                <span
                  class="stu_resume_practice_date"
                >{{item.studyStart}} - {{item.studyEnd}}</span>
              </h3>
              <h4 class="marginB-10">
                <div>
                  <span>{{item.certificate===0?'初中及以下':item.certificate===1?'中专/中技':item.certificate===2?'高中':item.certificate===3?'大专':item.certificate===4?'本科':item.certificate===5?'硕士':item.certificate===6?'博士':''}}</span>
                  <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                  <span>{{item.major}}</span>
                </div>
              </h4>
              <div v-html="item.schoolExperience"></div>
              <div class="resume_hover_in" v-if="isStudent">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                  @click="opendDialogEducationExperience(item)"
                >编辑</el-button>
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-delete"
                  @click="delEducationExperience(item.id)"
                >删除</el-button>
              </div>
            </el-col>
          </el-row>
          <!-- 资格证书 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="资格证书" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="dialogQualification=true"
              >添加</el-button>
            </el-col>
            <el-col :span="24" class="padding-15">
              <el-tag
                type="info"
                effect="plain"
                :closable="isStudent"
                class="stu_resume_tag"
                v-for="tag in resume.qualificationList"
                :key="tag.id"
                @close="delQualification(tag.id)"
              >{{tag.qualificationName}}</el-tag>
            </el-col>
          </el-row>
          <!-- 社交主页 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="社交主页" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="dialogSocialHomePage=true"
              >添加</el-button>
            </el-col>
            <el-col
              :span="24"
              class="padding-15 stu_resume_jianli_main_color resume_hover_out"
              v-for="item in resume.homePageList"
              :key="item.id"
            >
              <div>{{item.homePageUrl}}</div>
              <div class="resume_hover_in" v-if="isStudent">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-delete"
                  @click="delSocialHomePage(item.id)"
                >删除</el-button>
              </div>
            </el-col>
          </el-row>
          <!-- 志愿服务 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="志愿服务经历" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px" v-if="isStudent">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="dialogVolunteerExperience = true"
              >添加</el-button>
            </el-col>
            <el-col
              :span="24"
              class="padding-15 stu_resume_jianli_main_color resume_hover_out"
              v-for="item in resume.volunteerExperienceList"
              :key="item.id"
            >
              <h3 class="marginB-15">
                {{item.projectName}}
                <span
                  class="stu_resume_practice_date"
                >{{item.projectStart}} - {{item.projectEnd}}</span>
              </h3>
              <h4 class="marginB-10">{{item.serviceDuration}} 小时</h4>
              <div class="marginB-10 stu_resume_practice_content">
                <h4>内容：</h4>
                <div v-html="item.projectDesc"></div>
              </div>
              <div class="resume_hover_in" v-if="isStudent">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                  @click="opendDialogVolunteerExperience(item)"
                >编辑</el-button>
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-delete"
                  @click="delVolunteerExperience(item.id)"
                >删除</el-button>
              </div>
            </el-col>
          </el-row>
          <el-divider />
        </div>
      </el-col>
      <!-- 附件管理 -->
      <el-col :span="6" v-if="isStudent">
        <div class="padding-15 bgFFF">
          <part title="附件管理" type="main" />
          <el-row v-for="o in 4" :key="o" class="stu_resume_jianli_list">
            <el-col :span="20">
              <el-tooltip class="item" effect="dark" :content="String(o)" placement="top">
                <p>
                  <i class="el-icon-document"></i>
                  {{'列表内容 ' + o }}
                </p>
              </el-tooltip>
            </el-col>
            <el-col :span="4">
              <p>
                <i class="el-icon-download handleIcon"></i>&nbsp;
                <i class="el-icon-delete handleIcon"></i>
              </p>
            </el-col>
          </el-row>
          <el-upload
            class="upload-demo marginT-10"
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
            :on-success="uploadResumeSuccess"
            :on-error="uploadResumeError"
            :before-upload="beforeUploadResume"
            :headers="headers"
          >
            <el-button size="small" type="primary">上传简历</el-button>
          </el-upload>
        </div>
      </el-col>
    </el-row>

    <!-- 弹窗 -->
    <!-- 基本信息 -->
    <el-dialog title="基本信息" :visible.sync="dialogBaseInfo">
      <el-form :model="editForm" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="姓名">
              <el-input
                v-model="editForm.name"
                placeholder="请输入您的真实姓名"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-radio-group v-model="editForm.sex" size="medium">
                <el-radio-button :label="1">男</el-radio-button>
                <el-radio-button :label="0">女</el-radio-button>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生日">
              <el-date-picker
                v-model="editForm.birth"
                type="month"
                :default-value="new Date('1990-01')"
                placeholder="请选择您的出生年月"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="毕业年份">
              <el-date-picker v-model="editForm.classLevel" type="year" placeholder="毕业年份"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="电话">
              <el-input
                v-model="editForm.phone"
                placeholder="请输入您的联系电话"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="求职状态">
              <el-select v-model="editForm.jobStatus" placeholder="请选择您的求职状态">
                <el-option label="离职-随时到岗" :value="0"></el-option>
                <el-option label="在职-暂不考虑" :value="1"></el-option>
                <el-option label="在职-考虑机会" :value="2"></el-option>
                <el-option label="在职-月内到岗" :value="3"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="微信(选填)">
              <el-input
                v-model="editForm.weixin"
                placeholder="请输入您的微信号"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱(选填)">
              <el-input v-model="editForm.email" placeholder="请输入您的邮箱" autocomplete="off" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogBaseInfo = false">取 消</el-button>
        <el-button type="primary" @click="editBaseInfo">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 个人优势 -->
    <el-dialog title="个人优势" :visible.sync="dialogPersonalAdvantage">
      <el-form :model="editForm">
        <el-form-item>
          <el-input
            type="textarea"
            :show-word-limit="true"
            :maxlength="240"
            :autosize="{ minRows: 4}"
            placeholder="请输入内容"
            v-model="editForm.advantage"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPersonalAdvantage = false">取 消</el-button>
        <el-button type="primary" @click="editBaseInfo">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 期望职位 -->
    <el-dialog title="期望职位" :visible.sync="dialogExpectedPosition" @closed="closeDialog">
      <el-form :model="expectedPosition" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="职位">
              <el-input
                v-model="expectedPosition.positionName"
                placeholder="例如：UI设计师"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="薪资">
              <el-input
                v-model="expectedPosition.expectedPay"
                placeholder="例如：10-12K"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行业">
              <el-input
                v-model="expectedPosition.expectedTrade"
                placeholder="例如：互联网/金融/物流/房地产..."
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="城市">
              <el-input
                v-model="expectedPosition.expectedCity"
                placeholder="例如：北京/上海/广州/深圳..."
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogExpectedPosition = false">取 消</el-button>
        <el-button type="primary" @click="editExpectedPosition">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 实习经历 -->
    <el-dialog title="实习经历" :visible.sync="dialogInternshipExperience" @closed="closeDialog">
      <el-form :model="internshipExperience" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="公司名称">
              <el-input
                v-model="internshipExperience.companyName"
                placeholder="例如：阿里巴巴"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属行业">
              <el-input
                v-model="internshipExperience.companyTrade"
                placeholder="例如：互联网/金融/物流/房地产..."
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属部门">
              <el-input
                v-model="internshipExperience.affiliatedDept"
                placeholder="例如：产品部"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职位名称">
              <el-input
                v-model="internshipExperience.positionName"
                placeholder="例如：UI设计师"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="在职时间">
              <el-date-picker
                v-model="internshipExperience.onJobCycle"
                type="monthrange"
                range-separator="至"
                start-placeholder="入职时间"
                end-placeholder="离职时间"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="工作内容">
              <el-input
                type="textarea"
                :show-word-limit="true"
                :maxlength="240"
                :autosize="{ minRows: 4}"
                placeholder="请输入您实习工作内容"
                v-model="internshipExperience.workContent"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogInternshipExperience = false">取 消</el-button>
        <el-button type="primary" @click="editInternshipExperience">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 项目经历 -->
    <el-dialog title="添加项目经历" :visible.sync="dialogProjectExperience" @closed="closeDialog">
      <el-form :model="projectExperience" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="项目名称">
              <el-input
                v-model="projectExperience.projectName"
                placeholder="请输入项目名称"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目角色">
              <el-input
                v-model="projectExperience.projectRole"
                placeholder="例如：UI设计师"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目链接(选填)">
              <el-input
                v-model="projectExperience.projectLink"
                placeholder="例如：github.com/xxx"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目周期">
              <el-date-picker
                v-model="projectExperience.projectCycle"
                type="monthrange"
                range-separator="至"
                start-placeholder="选择年月"
                end-placeholder="选择年月"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目描述">
              <el-input
                type="textarea"
                :show-word-limit="true"
                :maxlength="240"
                :autosize="{ minRows: 4}"
                placeholder="请输入项目描述"
                v-model="projectExperience.projectDesc"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogProjectExperience = false">取 消</el-button>
        <el-button type="primary" @click="editProjectExperience">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 教育经历 -->
    <el-dialog title="添加教育经历" :visible.sync="dialogEducationExperience" @closed="closeDialog">
      <el-form :model="educationExperience" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="学校名称">
              <el-input
                v-model="educationExperience.schoolName"
                placeholder="例如：河北科技师范学院"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学历">
              <el-select v-model="educationExperience.certificate" placeholder="选择学历">
                <el-option label="初中及以下" :value="0"></el-option>
                <el-option label="中专/中技" :value="1"></el-option>
                <el-option label="高中" :value="2"></el-option>
                <el-option label="大专" :value="3"></el-option>
                <el-option label="本科" :value="4"></el-option>
                <el-option label="硕士" :value="5"></el-option>
                <el-option label="博士" :value="6"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="专业">
              <el-input
                v-model="educationExperience.major"
                placeholder="例如：计算机科学与技术"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="时间段">
              <el-date-picker
                v-model="educationExperience.studyCycle"
                type="monthrange"
                range-separator="至"
                start-placeholder="选择年份"
                end-placeholder="选择年份"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="在校经历(选填)">
              <el-input
                type="textarea"
                :show-word-limit="true"
                :maxlength="240"
                :autosize="{ minRows: 4}"
                placeholder="请填写内容"
                v-model="educationExperience.schoolExperience"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogEducationExperience = false">取 消</el-button>
        <el-button type="primary" @click="editEducationExperience">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 资格证书 -->
    <el-dialog title="资格证书" :visible.sync="dialogQualification" @closed="closeDialog">
      <el-form>
        <el-form-item>
          <el-input
            v-model="qualification"
            placeholder="多个证书以英文 “,” 分隔，例如：CET-4 或 CET-4,CET-6"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogQualification = false">取 消</el-button>
        <el-button type="primary" @click="addQualification">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 社交主页 -->
    <el-dialog title="社交主页" :visible.sync="dialogSocialHomePage" @closed="closeDialog">
      <el-form :model="editForm">
        <el-form-item>
          <el-input
            v-model="socialHomePage"
            placeholder="例如：github.com/xxx"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogSocialHomePage = false">取 消</el-button>
        <el-button type="primary" @click="addSocialHomePage">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 志愿服务经历 -->
    <el-dialog title="添加志愿者经历" :visible.sync="dialogVolunteerExperience" @closed="closeDialog">
      <el-form :model="volunteerExperience" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="项目名称">
              <el-input
                v-model="volunteerExperience.projectName"
                placeholder="例如：中国红十字会医疗救助"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总服务时长">
              <el-input
                v-model="volunteerExperience.serviceDuration"
                placeholder="例如：32小时"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目周期">
              <el-date-picker
                v-model="volunteerExperience.projectCycle"
                type="monthrange"
                range-separator="至"
                start-placeholder="选择年月"
                end-placeholder="选择年月"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目描述">
              <el-input
                type="textarea"
                :show-word-limit="true"
                :maxlength="240"
                :autosize="{ minRows: 4}"
                placeholder="请输入项目描述"
                v-model="volunteerExperience.projectDesc"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVolunteerExperience = false">取 消</el-button>
        <el-button type="primary" @click="editVolunteerExperience">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      isStudent: false,
      resume: {
        id: "",
        userId: "",
        name: "",
        sex: "",
        brith: "",
        phone: "",
        classLevel: "",
        jobStatus: "",
        weixin: "",
        email: "",
        idPhotoUrl: "",
        personalAdvantage: "",
        expectedPositionList: [],
        internshipExperienceList: [],
        projectExperienceList: [],
        educationExperienceList: [],
        qualificationList: [],
        socialHomePageList: [],
        volunteerExperienceList: []
      },
      dialogBaseInfo: false,
      dialogPersonalAdvantage: false,
      dialogExpectedPosition: false,
      dialogInternshipExperience: false,
      dialogProjectExperience: false,
      dialogEducationExperience: false,
      dialogQualification: false,
      dialogSocialHomePage: false,
      dialogVolunteerExperience: false,
      editForm: {
        id: "",
        userId: "",
        name: "",
        sex: "",
        brith: "",
        phone: "",
        classLevel: "",
        jobStatus: "",
        weixin: "",
        email: "",
        personalAdvantage: ""
      },
      expectedPosition: {
        id: "",
        resumeId: "",
        positionName: "",
        expectedPay: "",
        expectedCity: "",
        expectedTrade: ""
      },
      internshipExperience: {
        id: "",
        resumeId: "",
        companyName: "",
        companyTrade: "",
        affiliatedDept: "",
        positionName: "",
        onJobCycle: "", // 在职时间：数组 [ "2020-04-23T16:00:00.000Z", "2020-05-21T15:59:59.000Z" ]
        workContent: ""
      },
      projectExperience: {
        id: "",
        resumeId: "",
        projectName: "",
        projectRole: "",
        projectLink: "",
        projectCycle: "", // 项目周期：数组 [ "2020-04-23T16:00:00.000Z", "2020-05-21T15:59:59.000Z" ]
        projectDesc: ""
      },
      educationExperience: {
        id: "",
        resumeId: "",
        schoolName: "",
        certificate: "",
        major: "",
        studyCycle: "", // 教育周期：数组 [ "2020-04-23T16:00:00.000Z", "2020-05-21T15:59:59.000Z" ]
        schoolExperience: ""
      },
      qualification: "",
      socialHomePage: "",
      volunteerExperience: {
        id: "",
        resumeId: "",
        projectName: "",
        serviceDuration: "",
        projectCycle: "", // 志愿服务周期：数组 [ "2020-04-23T16:00:00.000Z", "2020-05-21T15:59:59.000Z" ]
        projectDesc: ""
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
      this.getRole();
    },
    getRole() {
      const role = this.getUserInfo().roles[0];
      if (role === "student") {
        this.isStudent = true;
        this.getResumeInfo();
      } else {
        this.otherSeeResume();
      }
    },
    otherSeeResume() {
      this.$get("/resume/" + this.$route.params.studentid)
        .then(res => {
          if (res.data.ok) {
            this.resume = res.data.data;
          }
        })
        .catch(e => {});
    },
    getResumeInfo() {
      this.$get("/resume/info")
        .then(res => {
          if (res.data.ok) {
            this.resume = res.data.data;
          }
        })
        .catch(e => {});
    },
    uploadResumeSuccess(res, file) {},
    uploadResumeError(err, file) {
      console.log("系统繁忙，稍后重试");
    },
    beforeUploadResume(file) {
      console.log(file);
      let imgType = ["image/jpeg", "image/gif", "image/png"];
      const isJPG = imgType.indexOf(file.type) > -1;
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("只能上传jpg/jpeg/png/gif文件");
        return;
      }
      if (!isLt2M) {
        this.$message.error("上传文件大小不能超过2MB!");
        return;
      }
    },
    beforeUploadIdPhoto(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传证件照图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传证件照图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    uploadIdPhotoSuccess(res, file) {
      if (res.ok) {
        this.$message({
          message: "上传成功",
          type: "success"
        });
        this.getResumeInfo();
      } else {
        this.$message.error(res.msg);
      }
    },
    uploadIdPhotoError(err, file) {
      console.log("系统繁忙，稍后重试");
    },
    openDialogBaseInfo() {
      this.editForm = this.copyObj(this.resume);
      if (this.editForm.classLevel) {
        this.editForm.classLevel = new Date(
          this.editForm.classLevel.toString() + "-01-01"
        );
      }
      this.dialogBaseInfo = true;
    },
    openDialogPersonalAdvantage() {
      this.editForm = this.copyObj(this.resume);
      if (this.editForm.classLevel) {
        this.editForm.classLevel = new Date(
          this.editForm.classLevel.toString() + "-01-01"
        );
      }
      if (this.editForm.advantage) {
        this.editForm.advantage = this.parseTextarea(this.editForm.advantage);
      }
      this.dialogPersonalAdvantage = true;
    },
    // 编辑基本信息、编辑个人优势
    editBaseInfo() {
      if (this.editForm.classLevel) {
        this.editForm.classLevel = this.editForm.classLevel.getFullYear();
      }
      if (this.editForm.advantage) {
        this.editForm.advantage = this.transTextarea(this.editForm.advantage);
      }
      this.dialogPersonalAdvantage = false;
      this.dialogBaseInfo = false;
      this.$post("/resume/save", this.editForm)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 关闭弹窗回调
    closeDialog() {
      this.expectedPosition = this.clearObj(this.expectedPosition);
      this.internshipExperience = this.clearObj(this.internshipExperience);
      this.projectExperience = this.clearObj(this.projectExperience);
      this.educationExperience = this.clearObj(this.educationExperience);
      this.volunteerExperience = this.clearObj(this.volunteerExperience);
      this.qualification = "";
      this.socialHomePage = "";
    },
    // 打开期望职位弹窗
    openDialogExpectedPosition(expectedPosition) {
      this.expectedPosition = this.copyObj(expectedPosition);
      this.dialogExpectedPosition = true;
    },
    // 增加 or 修改 期望职位
    editExpectedPosition() {
      const param = this.copyObj(this.expectedPosition);
      this.dialogExpectedPosition = false;
      if (!this.resume.id) {
        return this.$message("请先编辑基本信息");
      }
      param.resumeId = this.resume.id;
      this.$post("/expectedPosition/save", param)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 删除期望职位
    delExpectedPosition(id) {
      this.$delete("/expectedPosition/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 打开实习经历弹窗
    openDialogInternshipExperience(internshipExperience) {
      this.internshipExperience = this.copyObj(internshipExperience);
      this.internshipExperience.workContent = this.parseTextarea(
        this.internshipExperience.workContent
      );
      this.$set(this.internshipExperience, "onJobCycle", [
        new Date(this.internshipExperience.jobStart),
        new Date(this.internshipExperience.jobEnd)
      ]);
      this.dialogInternshipExperience = true;
    },
    // 增加 or 修改 实习经历
    editInternshipExperience() {
      const param = this.copyObj(this.internshipExperience);
      this.dialogInternshipExperience = false;
      if (!this.resume.id) {
        return this.$message("请先编辑基本信息");
      }
      param.resumeId = this.resume.id;
      param.jobStart = param.onJobCycle[0];
      param.jobEnd = param.onJobCycle[1];
      param.workContent = this.transTextarea(param.workContent);
      this.$post("/internshipExperience/save", param)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 删除实习经历
    delInternshipExperience(id) {
      this.$delete("/internshipExperience/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 打开项目经历弹窗
    openDialogProjectExperience(projectExperience) {
      this.projectExperience = this.copyObj(projectExperience);
      this.projectExperience.projectDesc = this.parseTextarea(
        this.projectExperience.projectDesc
      );
      this.$set(this.projectExperience, "projectCycle", [
        new Date(this.projectExperience.projectStart),
        new Date(this.projectExperience.projectEnd)
      ]);
      this.dialogProjectExperience = true;
    },
    // 增加 or 修改 项目经历
    editProjectExperience() {
      const param = this.copyObj(this.projectExperience);
      this.dialogProjectExperience = false;
      if (!this.resume.id) {
        return this.$message("请先编辑基本信息");
      }
      param.resumeId = this.resume.id;
      param.projectStart = param.projectCycle[0];
      param.projectEnd = param.projectCycle[1];
      param.projectDesc = this.transTextarea(param.projectDesc);
      this.$post("/projectExperience/save", param)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 删除项目经历
    delProjectExperience(id) {
      this.$delete("/projectExperience/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 打开教育经历弹窗
    opendDialogEducationExperience(educationExperience) {
      this.educationExperience = this.copyObj(educationExperience);
      this.educationExperience.schoolExperience = this.parseTextarea(
        this.educationExperience.schoolExperience
      );
      this.$set(this.educationExperience, "studyCycle", [
        new Date(this.educationExperience.studyStart),
        new Date(this.educationExperience.studyEnd)
      ]);
      this.dialogEducationExperience = true;
    },
    // 增加 or 修改 教育经历
    editEducationExperience() {
      const param = this.copyObj(this.educationExperience);
      this.dialogEducationExperience = false;
      if (!this.resume.id) {
        return this.$message("请先编辑基本信息");
      }
      param.resumeId = this.resume.id;
      param.studyStart = param.studyCycle[0];
      param.studyEnd = param.studyCycle[1];
      param.schoolExperience = this.transTextarea(param.schoolExperience);
      this.$post("/educationExperience/save", param)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 删除教育经历
    delEducationExperience(id) {
      this.$delete("/educationExperience/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 增加资格证书
    addQualification() {
      const qualification = this.qualification;
      this.dialogQualification = false;
      this.$post(
        "/qualification/add/" + this.resume.id,
        this.$qs.stringify({
          qualification: qualification
        })
      )
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 删除资格证书
    delQualification(id) {
      this.$delete("/qualification/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 增加社交主页
    addSocialHomePage() {
      const socialHomePage = this.socialHomePage;
      this.dialogSocialHomePage = false;
      this.$post(
        "/homePage/add/" + this.resume.id,
        this.$qs.stringify({
          homePage: socialHomePage
        })
      )
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 删除社交主页
    delSocialHomePage(id) {
      this.$delete("/homePage/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 打开志愿经历弹窗
    opendDialogVolunteerExperience(volunteerExperience) {
      this.volunteerExperience = this.copyObj(volunteerExperience);
      this.volunteerExperience.projectDesc = this.parseTextarea(
        this.volunteerExperience.projectDesc
      );
      this.$set(this.volunteerExperience, "projectCycle", [
        new Date(this.volunteerExperience.projectStart),
        new Date(this.volunteerExperience.projectEnd)
      ]);
      this.dialogVolunteerExperience = true;
    },
    // 增加 or 修改 志愿经历
    editVolunteerExperience() {
      const param = this.copyObj(this.volunteerExperience);
      this.dialogVolunteerExperience = false;
      if (!this.resume.id) {
        return this.$message("请先编辑基本信息");
      }
      param.resumeId = this.resume.id;
      param.projectStart = param.projectCycle[0];
      param.projectEnd = param.projectCycle[1];
      param.projectDesc = this.transTextarea(param.projectDesc);
      this.$post("/volunteerExperience/save", param)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "保存成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    },
    // 删除志愿经历
    delVolunteerExperience(id) {
      this.$delete("/volunteerExperience/" + id)
        .then(res => {
          if (res.data.ok) {
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getResumeInfo();
          }
        })
        .catch(e => {});
    }
  }
};
</script>
<style lang="scss" scoped>
.box {
  width: 400px;

  .top {
    text-align: center;
  }

  .left {
    float: left;
    width: 60px;
  }

  .right {
    float: right;
    width: 60px;
  }

  .bottom {
    clear: both;
    text-align: center;
  }

  .item {
    margin: 4px;
  }

  .left .el-tooltip__popper,
  .right .el-tooltip__popper {
    padding: 8px 10px;
  }
}
</style>