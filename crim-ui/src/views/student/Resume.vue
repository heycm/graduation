<template>
  <div class>
    <el-row type="flex" :gutter="20">
      <el-col :span="18">
        <div class="padding-15 bgFFF">
          <el-row type="flex" class="marginB-15">
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
            <el-col :span="4" class="text-right" style="padding-top:3px">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-edit-outline"
                @click="resumeInfo.baseInfo=copyObj(resume.baseInfo);dialogBaseInfo=true"
              >编辑</el-button>
            </el-col>
            <el-col :span="20" class="paddingLR-15">
              <h2 class="marginB-15 marginT-15">{{resume.baseInfo.name}}</h2>
              <div class="marginB-15 paddingL-20 stu_resume_jianli_main_color">
                <span>{{resume.baseInfo.sex === 1 ? '男' : '女'}}</span>
                <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{resume.baseInfo.brith}}</span>
                <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{resume.baseInfo.classLevel}} 届</span>
                <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                <span>{{resume.baseInfo.jobStatus===0?'离职-随时到岗':resume.baseInfo.jobStatus===1?'在职-暂不考虑':resume.baseInfo.jobStatus===2?'在职-考虑机会':resume.baseInfo.jobStatus===3?'在职-月内到岗':''}}</span>
              </div>
              <div class="paddingL-20 stu_resume_jianli_main_color">
                <span>{{resume.baseInfo.phone}}</span>
                <el-divider
                  v-if="resume.baseInfo.weixin"
                  direction="vertical"
                  class="stu_resume_vertical"
                ></el-divider>
                <span>{{resume.baseInfo.weixin}}</span>
                <el-divider
                  v-if="resume.baseInfo.email"
                  direction="vertical"
                  class="stu_resume_vertical"
                ></el-divider>
                <span>{{resume.baseInfo.email}}</span>
              </div>
            </el-col>
            <el-col :span="4" class="paddingLR-15">
              <el-row type="flex" justify="end">
                <el-upload
                  class="upload-demo marginT-10"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :show-file-list="false"
                  :on-success="uploadIdPhotoSuccess"
                  :on-error="uploadIdPhotoError"
                  :before-upload="beforeUploadIdPhoto"
                  :headers="headers"
                >
                  <el-image class="stu_resume_zjz" :src="resume.baseInfo.idPhotoUrl" fit="fill">
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
            <el-col :span="4" class="text-right" style="padding-top:3px">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-edit-outline"
                @click="resumeInfo.baseInfo=copyObj(resume.baseInfo);dialogPersonalAdvantage=true"
              >编辑</el-button>
            </el-col>
            <el-col
              :span="24"
              class="padding-15 stu_resume_jianli_main_color"
            >{{resume.baseInfo.personalAdvantage}}</el-col>
          </el-row>
          <!-- 期望职位 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="期望职位" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="openDialogExpectedPosition('add')"
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
              <div class="resume_hover_in">
                <el-button
                  @click="openDialogExpectedPosition('edit', item)"
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
            <el-col :span="4" class="text-right" style="padding-top:3px">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="openDialogInternshipExperience('add')"
              >添加</el-button>
            </el-col>
            <el-col
              :span="24"
              class="padding-15 stu_resume_jianli_main_color resume_hover_out"
              v-for="item in resume.internshipExperienceList"
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
                {{item.workContent}}
              </div>
              <div class="resume_hover_in">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                  @click="openDialogInternshipExperience('edit', item)"
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
            <el-col :span="4" class="text-right" style="padding-top:3px">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="openDialogProjectExperience('add')"
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
                {{item.projectDesc}}
              </div>
              <div class="stu_resume_practice_content">
                <h4>项目连接：</h4>
                {{item.projectLink}}
              </div>
              <div class="resume_hover_in">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                  @click="openDialogProjectExperience('edit', item)"
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
            <el-col :span="4" class="text-right" style="padding-top:3px">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="opendDialogEducationExperience('add')"
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
                  <span>{{item.certificate}}</span>
                  <el-divider direction="vertical" class="stu_resume_vertical"></el-divider>
                  <span>{{item.major}}</span>
                </div>
              </h4>
              <div>{{item.experienceInSchool}}</div>
              <div class="resume_hover_in">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                  @click="opendDialogEducationExperience('edit', item)"
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
            <el-col :span="4" class="text-right" style="padding-top:3px">
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
                closable
                class="stu_resume_tag"
                v-for="tag in resume.qualificationList"
                :key="tag.id"
                @close="delQualification(tag)"
              >{{tag.name}}</el-tag>
            </el-col>
          </el-row>
          <!-- 社交主页 -->
          <el-row class="marginB-30">
            <el-col :span="20">
              <part title="社交主页" type="main" />
            </el-col>
            <el-col :span="4" class="text-right" style="padding-top:3px">
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
              v-for="item in resume.socialHomePageList"
              :key="item.id"
            >
              <div>{{item.pageUrl}}</div>
              <div class="resume_hover_in">
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
            <el-col :span="4" class="text-right" style="padding-top:3px">
              <el-button
                type="text"
                style="padding:0"
                icon="el-icon-circle-plus"
                @click="opendDialogVolunteerExperience('add')"
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
                >{{item.serviceStart}} - {{item.serviceEnd}}</span>
              </h3>
              <h4 class="marginB-10">{{item.serviceDuration}} 小时</h4>
              <div class="marginB-10 stu_resume_practice_content">
                <h4>内容：</h4>
                {{item.projectDesc}}
              </div>
              <div class="resume_hover_in">
                <el-button
                  type="text"
                  style="padding:0"
                  icon="el-icon-edit-outline"
                  @click="opendDialogVolunteerExperience('edit', item)"
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
      <el-col :span="6">
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
      <el-form :model="resumeInfo.baseInfo" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="姓名">
              <el-input
                v-model="resumeInfo.baseInfo.name"
                placeholder="请输入您的真实姓名"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别">
              <el-radio-group v-model="resumeInfo.baseInfo.sex" size="medium">
                <el-radio-button :label="1">男</el-radio-button>
                <el-radio-button :label="0">女</el-radio-button>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="生日">
              <el-date-picker
                v-model="resumeInfo.baseInfo.brith"
                type="month"
                :default-value="new Date('1990-01')"
                placeholder="请选择您的出生年月"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="毕业年份">
              <el-date-picker
                v-model="resumeInfo.baseInfo.classLevel"
                type="year"
                placeholder="毕业年份"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="电话">
              <el-input
                v-model="resumeInfo.baseInfo.phone"
                placeholder="请输入您的联系电话"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="求职状态">
              <el-select v-model="resumeInfo.baseInfo.jobStatus" placeholder="请选择您的求职状态">
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
                v-model="resumeInfo.baseInfo.weixin"
                placeholder="请输入您的微信号"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱(选填)">
              <el-input
                v-model="resumeInfo.baseInfo.email"
                placeholder="请输入您的邮箱"
                autocomplete="off"
                clearable
              ></el-input>
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
      <el-form :model="resumeInfo.baseInfo">
        <el-form-item>
          <el-input
            type="textarea"
            :show-word-limit="true"
            :maxlength="240"
            :autosize="{ minRows: 4}"
            placeholder="请输入内容"
            v-model="resumeInfo.baseInfo.personalAdvantage"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPersonalAdvantage = false">取 消</el-button>
        <el-button type="primary" @click="editPersonalAdvantage">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 期望职位 -->
    <el-dialog title="期望职位" :visible.sync="dialogExpectedPosition">
      <el-form :model="resumeInfo.expectedPosition" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="职位">
              <el-input
                v-model="resumeInfo.expectedPosition.positionName"
                placeholder="例如：UI设计师"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="薪资">
              <el-input
                v-model="resumeInfo.expectedPosition.expectedPay"
                placeholder="例如：10-12K"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="行业">
              <el-input
                v-model="resumeInfo.expectedPosition.expectedTrade"
                placeholder="例如：互联网/金融/物流/房地产..."
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="城市">
              <el-input
                v-model="resumeInfo.expectedPosition.expectedCity"
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
    <el-dialog title="实习经历" :visible.sync="dialogInternshipExperience">
      <el-form :model="resumeInfo.internshipExperience" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="公司名称">
              <el-input
                v-model="resumeInfo.internshipExperience.companyName"
                placeholder="例如：阿里巴巴"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属行业">
              <el-input
                v-model="resumeInfo.internshipExperience.companyTrade"
                placeholder="例如：互联网/金融/物流/房地产..."
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属部门">
              <el-input
                v-model="resumeInfo.internshipExperience.affiliatedDept"
                placeholder="例如：产品部"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="职位名称">
              <el-input
                v-model="resumeInfo.internshipExperience.positionName"
                placeholder="例如：UI设计师"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="在职时间">
              <el-date-picker
                v-model="resumeInfo.internshipExperience.onJobCycle"
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
                v-model="resumeInfo.internshipExperience.workContent"
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
    <el-dialog title="添加项目经历" :visible.sync="dialogProjectExperience">
      <el-form :model="resumeInfo.projectExperience" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="项目名称">
              <el-input
                v-model="resumeInfo.projectExperience.projectName"
                placeholder="请输入项目名称"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目角色">
              <el-input
                v-model="resumeInfo.projectExperience.projectRole"
                placeholder="例如：UI设计师"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目链接(选填)">
              <el-input
                v-model="resumeInfo.projectExperience.projectLink"
                placeholder="例如：github.com/xxx"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目周期">
              <el-date-picker
                v-model="resumeInfo.projectExperience.projectCycle"
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
                v-model="resumeInfo.projectExperience.projectDesc"
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
    <el-dialog title="添加教育经历" :visible.sync="dialogEducationExperience">
      <el-form :model="resumeInfo.educationExperience" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="学校名称">
              <el-input
                v-model="resumeInfo.educationExperience.schoolName"
                placeholder="例如：河北科技师范学院"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="学历">
              <el-select v-model="resumeInfo.educationExperience.certificate" placeholder="选择学历">
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
                v-model="resumeInfo.educationExperience.major"
                placeholder="例如：计算机科学与技术"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="时间段">
              <el-date-picker
                v-model="resumeInfo.educationExperience.studyCycle"
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
                v-model="resumeInfo.educationExperience.experienceInSchool"
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
    <el-dialog title="资格证书" :visible.sync="dialogQualification">
      <el-form :model="resumeInfo">
        <el-form-item>
          <el-input
            v-model="resumeInfo.qualification"
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
    <el-dialog title="社交主页" :visible.sync="dialogSocialHomePage">
      <el-form :model="resumeInfo">
        <el-form-item>
          <el-input
            v-model="resumeInfo.socialHomePage"
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
    <el-dialog title="添加志愿者经历" :visible.sync="dialogVolunteerExperience">
      <el-form :model="resumeInfo.volunteerExperience" label-position="top">
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item label="项目名称">
              <el-input
                v-model="resumeInfo.volunteerExperience.projectName"
                placeholder="例如：中国红十字会医疗救助"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="总服务时长">
              <el-input
                v-model="resumeInfo.volunteerExperience.serviceDuration"
                placeholder="例如：32小时"
                autocomplete="off"
                clearable
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目周期">
              <el-date-picker
                v-model="resumeInfo.volunteerExperience.serviceCycle"
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
                v-model="resumeInfo.volunteerExperience.projectDesc"
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
      resume: {
        id: 1, // 简历ID
        userId: 1, // 用户ID
        baseInfo: {
          id: 1, // 基本信息ID
          resumeId: 1, // 简历ID
          name: "张三丰",
          sex: 1,
          brith: "1998-03",
          phone: "13888888888",
          classLevel: "2020",
          jobStatus: 3, // 0:离职-随时到岗 1:在职-暂不考虑 2:在职-考虑机会 3:在职-月内到岗
          weixin: "wx13888888888",
          email: "email@163.com",
          idPhotoUrl:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587123568106&di=9bb0c4eca1a56da9f60e446f9c5ec5cf&imgtype=0&src=http%3A%2F%2Fp0.meituan.net%2Fdpdeal%2Fb686c00b13705bd8b42b242f6c3b545331588.jpg",
          personalAdvantage:
            "熟练掌握ssm框架、springboot框架；熟练使用MySQL，SQLserver，Redis数据库， 熟练掌握html+css，jquery，vue，ajax，json等前端技术；熟练使用bootstrap，uniapp，vant，layui等前端框架； 熟练使用idea，eclipse，Linux等。"
        },
        expectedPositionList: [
          {
            id: 1, // 期望职位ID
            resumeId: 1, // 简历ID
            positionName: "Java开发",
            expectedPay: "7-8K",
            expectedCity: "广州",
            expectedTrade: "互联网"
          },
          {
            id: 2,
            resumeId: 1, // 简历ID
            positionName: "PHP开发",
            expectedPay: "7-8K",
            expectedCity: "深圳",
            expectedTrade: "不限"
          }
        ],
        internshipExperienceList: [
          {
            id: 1, // 实习经历ID
            resumeId: 1, // 简历ID
            companyName: "阿里巴巴",
            companyTrade: "金融",
            affiliatedDept: "产品部",
            positionName: "运营实习生",
            jobStart: "2019.11",
            jobEnd: "2020.01",
            workContent:
              "自**年*月进入进入**集团以来，我被安排到**集团**科技有限公司从事公司对外联络工作，主要负责与政府部门相关的项目申报、审批工作。在领导的热心指导下，在周围同事的支持帮助下，我努力学习，顺利地结束了实习阶段，很快适应了新的岗位，独立在岗位上完成了多个项目的申报、审批工作，为公司的发展做出了一份贡献。"
          },
          {
            id: 2,
            resumeId: 1, // 简历ID
            companyName: "字节跳动",
            companyTrade: "金融",
            affiliatedDept: "产品部",
            positionName: "运营实习生",
            jobStart: "2018.11",
            jobEnd: "2019.01",
            workContent:
              "自**年*月进入进入**集团以来，我被安排到**集团**科技有限公司从事公司对外联络工作，主要负责与政府部门相关的项目申报、审批工作。在领导的热心指导下，在周围同事的支持帮助下，我努力学习，顺利地结束了实习阶段，很快适应了新的岗位，独立在岗位上完成了多个项目的申报、审批工作，为公司的发展做出了一份贡献。"
          }
        ],
        projectExperienceList: [
          {
            id: 1, // 项目经历ID
            resumeId: 1, // 简历ID
            projectName: "CRIM 校园招聘信息管理系统",
            projectRole: "项目负责人",
            projectLink: "http://www.heycm.omline",
            projectStart: "2019-11",
            projectEnd: "2020-01",
            projectDesc:
              "自**年*月进入进入**集团以来，我被安排到**集团**科技有限公司从事公司对外联络工作，主要负责与政府部门相关的项目申报、审批工作。在领导的热心指导下，在周围同事的支持帮助下，我努力学习，顺利地结束了实习阶段，很快适应了新的岗位，独立在岗位上完成了多个项目的申报、审批工作，为公司的发展做出了一份贡献。"
          },
          {
            id: 2,
            resumeId: 1, // 简历ID
            projectName: "CRIM 校园招聘信息管理系统 v2.0",
            projectRole: "项目负责人",
            projectLink: "http://www.heycm.omline",
            projectStart: "2020-03",
            projectEnd: "2020-05",
            projectDesc:
              "自**年*月进入进入**集团以来，我被安排到**集团**科技有限公司从事公司对外联络工作，主要负责与政府部门相关的项目申报、审批工作。在领导的热心指导下，在周围同事的支持帮助下，我努力学习，顺利地结束了实习阶段，很快适应了新的岗位，独立在岗位上完成了多个项目的申报、审批工作，为公司的发展做出了一份贡献。"
          }
        ],
        educationExperienceList: [
          {
            id: 1,
            resumeId: 1, // 简历ID
            schoolName: "河北科技师范学院",
            certificate: "博士",
            major: "网络安全",
            studyStart: "2016.09",
            studyEnd: "2020.06",
            experienceInSchool: "吧啦吧啦一大堆"
          },
          {
            id: 2,
            resumeId: 1, // 简历ID
            schoolName: "北京大学",
            certificate: "硕士",
            major: "大数据",
            studyStart: "2016.09",
            studyEnd: "2020.06",
            experienceInSchool: "吧啦吧啦一大堆"
          },
          {
            id: 3,
            resumeId: 1, // 简历ID
            schoolName: "清华大学",
            certificate: "本科",
            major: "计算机科学与技术",
            studyStart: "2016.09",
            studyEnd: "2020.06",
            experienceInSchool: "吧啦吧啦一大堆"
          }
        ],
        qualificationList: [
          { id: 1, resumeId: 1, name: "大学英语四级" },
          { id: 2, resumeId: 1, name: "大学英语六级" },
          { id: 3, resumeId: 1, name: "日语十级" },
          { id: 4, resumeId: 1, name: "高分雅思" },
          { id: 5, resumeId: 1, name: "高分托福" },
          { id: 6, resumeId: 1, name: "Cisco思科认证" },
          { id: 7, resumeId: 1, name: "华为认证" },
          { id: 8, resumeId: 1, name: "机动车驾驶证" },
          { id: 9, resumeId: 1, name: "焊工职业资格证" },
          { id: 10, resumeId: 1, name: "国家裁判员" },
          { id: 11, resumeId: 1, name: "AOPA无人机执照" },
          { id: 12, resumeId: 1, name: "上海音乐学院考级" }
        ],
        socialHomePageList: [
          { id: 1, resumeId: 1, pageUrl: "http://www.baidu.com" },
          { id: 2, resumeId: 1, pageUrl: "http://www.vip.org" }
        ],
        volunteerExperienceList: [
          {
            id: 1,
            resumeId: 1, // 简历ID
            projectName: "志愿服务项目 1",
            serviceDuration: "16",
            serviceStart: "2019.11",
            serviceEnd: "2019.11",
            projectDesc: "不可描述不可描述不可描述"
          },
          {
            id: 2,
            resumeId: 1, // 简历ID
            projectName: "志愿服务项目 2",
            serviceDuration: "48",
            serviceStart: "2018.10",
            serviceEnd: "2018.10",
            projectDesc: "不可描述不可描述不可描述"
          }
        ]
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
      isEditExpectedPosition: false,
      isEditInternshipExperience: false,
      isEditProjectExperience: false,
      isEditEducationExperience: false,
      isEditSocialHomePage: false,
      isEditVolunteerExperience: false,
      resumeInfo: {
        id: null, // 简历ID
        userId: null, // 用户ID
        baseInfo: {
          id: null,
          resumeId: null,
          name: null,
          sex: null,
          brith: null,
          phone: null,
          classLevel: null,
          jobStatus: null,
          weixin: null,
          email: null,
          idPhotoUrl: null,
          personalAdvantage: null
        },
        expectedPosition: {
          id: null,
          resumeId: null,
          positionName: null,
          expectedPay: null,
          expectedCity: null,
          expectedTrade: null
        },
        internshipExperience: {
          id: null,
          resumeId: null,
          companyName: null,
          companyTrade: null,
          affiliatedDept: null,
          positionName: null,
          onJobCycle: null, // 在职时间：数组 [ "2020-04-23T16:00:00.000Z", "2020-05-21T15:59:59.000Z" ]
          workContent: null
        },
        projectExperience: {
          id: null,
          resumeId: null,
          projectName: null,
          projectRole: null,
          projectLink: null,
          projectCycle: null, // 项目周期：数组 [ "2020-04-23T16:00:00.000Z", "2020-05-21T15:59:59.000Z" ]
          projectDesc: null
        },
        educationExperience: {
          id: null,
          resumeId: null,
          schoolName: null,
          certificate: null,
          major: null,
          studyCycle: null, // 教育周期：数组 [ "2020-04-23T16:00:00.000Z", "2020-05-21T15:59:59.000Z" ]
          experienceInSchool: null
        },
        qualification: null,
        socialHomePage: null,
        volunteerExperience: {
          id: null,
          resumeId: null,
          projectName: null,
          serviceDuration: null,
          serviceCycle: null, // 志愿服务周期：数组 [ "2020-04-23T16:00:00.000Z", "2020-05-21T15:59:59.000Z" ]
          projectDesc: null
        }
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
    uploadResumeSuccess(res, file) {},
    uploadResumeError(err, file) {},
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
    beforeUploadIdPhoto(file) {},
    uploadIdPhotoSuccess(res, file) {},
    uploadIdPhotoError(err, file) {},

    // 编辑基本信息
    editBaseInfo() {
      this.dialogBaseInfo = false;
      // do update
      console.log(this.resumeInfo.baseInfo);
    },
    // 编辑个人优势
    editPersonalAdvantage() {
      this.dialogPersonalAdvantage = false;
      // do update
      console.log(this.resumeInfo.baseInfo);
    },
    // 打开期望职位弹窗
    openDialogExpectedPosition(type, expectedPosition) {
      if (type === "add") {
        if (this.isEditExpectedPosition) {
          this.resumeInfo.expectedPosition = this.clearObj(
            this.resumeInfo.expectedPosition
          );
          this.isEditExpectedPosition = false;
        }
      } else if (type === "edit") {
        this.resumeInfo.expectedPosition = this.copyObj(expectedPosition);
        this.isEditExpectedPosition = true;
      } else {
        return;
      }
      this.dialogExpectedPosition = true;
    },
    // 增加 or 修改 期望职位
    editExpectedPosition() {
      this.dialogExpectedPosition = false;
      if (this.isEditExpectedPosition) {
        // do update
        console.log(this.resumeInfo.expectedPosition);
      } else {
        // do add
        console.log(this.resumeInfo.expectedPosition);
      }
    },
    // 删除期望职位
    delExpectedPosition(id) {
      // do delete
      console.log(id);
    },
    // 打开实习经历弹窗
    openDialogInternshipExperience(type, internshipExperience) {
      if (type === "add") {
        if (this.isEditInternshipExperience) {
          this.resumeInfo.internshipExperience = this.clearObj(
            this.resumeInfo.internshipExperience
          );
          this.isEditInternshipExperience = false;
        }
      } else if (type === "edit") {
        this.resumeInfo.internshipExperience = this.copyObj(
          internshipExperience
        );
        let onJobCycle = [
          this.resumeInfo.internshipExperience.jobStart,
          this.resumeInfo.internshipExperience.jobEnd
        ];
        this.$set(
          this.resumeInfo.internshipExperience,
          "onJobCycle",
          onJobCycle
        );
        this.isEditInternshipExperience = true;
      } else {
        return;
      }
      this.dialogInternshipExperience = true;
    },
    // 增加 or 修改 实习经历
    editInternshipExperience() {
      this.dialogInternshipExperience = false;
      let ie = this.resumeInfo.internshipExperience;
      ie.jobStart = ie.onJobCycle[0];
      ie.jobEnd = ie.onJobCycle[1];
      if (this.isEditInternshipExperience) {
        // do update
        console.log(ie);
      } else {
        ie.resumeId = this.resume.id;
        // do add
        console.log(ie);
      }
    },
    // 删除实习经历
    delInternshipExperience(id) {
      // do delete
      console.log(id);
    },
    // 打开项目经历弹窗
    openDialogProjectExperience(type, projectExperience) {
      if (type === "add") {
        if (this.isEditProjectExperience) {
          this.resumeInfo.projectExperience = this.clearObj(
            this.resumeInfo.projectExperience
          );
          this.isEditProjectExperience = false;
        }
      } else if (type === "edit") {
        this.resumeInfo.projectExperience = this.copyObj(projectExperience);
        let projectCycle = [
          this.resumeInfo.projectExperience.projectStart,
          this.resumeInfo.projectExperience.projectEnd
        ];
        this.$set(
          this.resumeInfo.projectExperience,
          "projectCycle",
          projectCycle
        );
        this.isEditProjectExperience = true;
      } else {
        return;
      }
      this.dialogProjectExperience = true;
    },
    // 增加 or 修改 项目经历
    editProjectExperience() {
      this.dialogProjectExperience = false;
      let pe = this.resumeInfo.projectExperience;
      pe.projectStart = pe.projectCycle[0];
      pe.projectEnd = pe.projectCycle[1];
      if (this.isEditProjectExperience) {
        // do update
        console.log(pe);
      } else {
        pe.resumeId = this.resume.id;
        // do add
        console.log(pe);
      }
    },
    // 删除项目经历
    delProjectExperience(id) {
      // do delete
      console.log(id);
    },
    // 打开教育经历弹窗
    opendDialogEducationExperience(type, educationExperience) {
      if (type === "add") {
        if (this.isEditEducationExperience) {
          this.resumeInfo.educationExperience = this.clearObj(
            this.resumeInfo.educationExperience
          );
          this.isEditEducationExperience = false;
        }
      } else if (type === "edit") {
        this.resumeInfo.educationExperience = this.copyObj(educationExperience);
        let studyCycle = [
          this.resumeInfo.educationExperience.studyStart,
          this.resumeInfo.educationExperience.studyEnd
        ];
        this.$set(
          this.resumeInfo.educationExperience,
          "studyCycle",
          studyCycle
        );
        this.isEditEducationExperience = true;
      } else {
        return;
      }
      this.dialogEducationExperience = true;
    },
    // 增加 or 修改 教育经历
    editEducationExperience() {
      this.dialogEducationExperience = false;
      let ee = this.resumeInfo.educationExperience;
      ee.studyStart = ee.studyCycle[0];
      ee.studyEnd = ee.studyCycle[1];
      if (this.isEditEducationExperience) {
        // do update
        console.log(ee);
      } else {
        ee.resumeId = this.resume.id;
        // do add
        console.log(ee);
      }
    },
    // 删除教育经历
    delEducationExperience(id) {
      // do delete
      console.log(id);
    },
    // 增加资格证书
    addQualification() {
      this.dialogQualification = false;
      // do add
      console.log(this.resumeInfo.qualification);
    },
    // 删除资格证书
    delQualification(qualification) {
      // do delete
      console.log(qualification);
      this.resume.qualificationList.splice(
        this.resume.qualificationList.indexOf(qualification),
        1
      );
    },
    // 增加社交主页
    addSocialHomePage() {
      this.dialogSocialHomePage = false;
      // do add
      console.log(this.resumeInfo.socialHomePage);
    },
    // 删除社交主页
    delSocialHomePage(id) {
      // do delete
      console.log(id);
    },
    // 打开志愿经历弹窗
    opendDialogVolunteerExperience(type, volunteerExperience) {
      if (type === "add") {
        if (this.isEditVolunteerExperience) {
          this.resumeInfo.volunteerExperience = this.clearObj(
            this.resumeInfo.volunteerExperience
          );
          this.isEditVolunteerExperience = false;
        }
      } else if (type === "edit") {
        this.resumeInfo.volunteerExperience = this.copyObj(volunteerExperience);
        let serviceCycle = [
          this.resumeInfo.volunteerExperience.serviceStart,
          this.resumeInfo.volunteerExperience.serviceEnd
        ];
        this.$set(
          this.resumeInfo.volunteerExperience,
          "serviceCycle",
          serviceCycle
        );
        this.isEditVolunteerExperience = true;
      } else {
        return;
      }
      this.dialogVolunteerExperience = true;
    },
    // 增加 or 修改 志愿经历
    editVolunteerExperience() {
      this.dialogVolunteerExperience = false;
      let ve = this.resumeInfo.volunteerExperience;
      ve.serviceStart = ve.serviceCycle[0];
      ve.serviceEnd = ve.serviceCycle[1];
      if (this.isEditVolunteerExperience) {
        // do update
        console.log(ve);
      } else {
        ve.resumeId = this.resume.id;
        // do add
        console.log(ve);
      }
    },
    // 删除志愿经历
    delVolunteerExperience(id) {
      // do delete
      console.log(id);
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