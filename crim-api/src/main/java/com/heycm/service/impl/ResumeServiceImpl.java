package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heycm.dto.*;
import com.heycm.model.*;
import com.heycm.mapper.ResumeMapper;
import com.heycm.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements IResumeService {
    @Autowired
    public IExpectedPositionService expectedPositionService;
    @Autowired
    public IInternshipExperienceService internshipExperienceService;
    @Autowired
    public IProjectExperienceService projectExperienceService;
    @Autowired
    public IEducationExperienceService educationExperienceService;
    @Autowired
    public IQualificationService qualificationService;
    @Autowired
    public IHomePageService homePageService;
    @Autowired
    public IVolunteerExperienceService volunteerExperienceService;
    @Autowired
    public IFileService fileService;

    @Override
    public ResumeInfoDTO getResumeInfoDTO(Integer userId) {
        ResumeInfoDTO resumeInfo = new ResumeInfoDTO();
        Resume resume = getOne(new QueryWrapper<Resume>().lambda().eq(Resume::getUserId, userId));
        if (resume == null) {
            return resumeInfo;
        }
        resumeInfo.setId(resume.getId());
        resumeInfo.setUserId(resume.getUserId());
        resumeInfo.setName(resume.getName());
        resumeInfo.setSex(resume.getSex());
        resumeInfo.setBirth(DateUtil.getStringYMD(resume.getBirth()));
        resumeInfo.setClassLevel(resume.getClassLevel());
        resumeInfo.setJobStatus(resume.getJobStatus());
        resumeInfo.setPhone(resume.getPhone());
        resumeInfo.setWeixin(resume.getWeixin());
        resumeInfo.setEmail(resume.getEmail());
        resumeInfo.setAdvantage(resume.getAdvantage());

        // 证件照
        File idPhoto = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, userId).eq(File::getFileType, 3));
        if (idPhoto != null) {
            resumeInfo.setIdPhotoUrl(idPhoto.getFileUrl());
        }

        List<ExpectedPositionDTO> expectedPositionList = new ArrayList<>();
        List<InternshipExceptionDTO> internshipExceptionList = new ArrayList<>();
        List<ProjectExperienceDTO> projectExperienceList = new ArrayList<>();
        List<EducationExperienceDTO> educationExperienceList = new ArrayList<>();
        List<QualificationDTO> qualificationList = new ArrayList<>();
        List<HomePageDTO> homePageList = new ArrayList<>();
        List<VolunteerExperienceDTO> volunteerExperienceList = new ArrayList<>();

        // 期望职位
        List<ExpectedPosition> expectedPositions = expectedPositionService.list(new QueryWrapper<ExpectedPosition>().lambda().eq(ExpectedPosition::getResumeId, resume.getId()));
        for (ExpectedPosition expectedPosition : expectedPositions) {
            ExpectedPositionDTO expectedPositionDTO = new ExpectedPositionDTO();
            expectedPositionDTO.setId(expectedPosition.getId());
            expectedPositionDTO.setResumeId(expectedPosition.getResumeId());
            expectedPositionDTO.setPositionName(expectedPosition.getPositionName());
            expectedPositionDTO.setExpectedPay(expectedPosition.getExpectedPay());
            expectedPositionDTO.setExpectedCity(expectedPosition.getExpectedCity());
            expectedPositionDTO.setExpectedTrade(expectedPosition.getExpectedTrade());
            expectedPositionList.add(expectedPositionDTO);
        }
        // 实习经历
        List<InternshipExperience> internshipExperiences = internshipExperienceService.list(new QueryWrapper<InternshipExperience>().lambda().eq(InternshipExperience::getResumeId, resume.getId()));
        for (InternshipExperience internshipExperience : internshipExperiences) {
            InternshipExceptionDTO internshipExceptionDTO = new InternshipExceptionDTO();
            internshipExceptionDTO.setId(internshipExperience.getId());
            internshipExceptionDTO.setResumeId(internshipExperience.getResumeId());
            internshipExceptionDTO.setCompanyName(internshipExperience.getCompanyName());
            internshipExceptionDTO.setCompanyTrade(internshipExperience.getCompanyTrade());
            internshipExceptionDTO.setAffiliatedDept(internshipExperience.getAffiliatedDept());
            internshipExceptionDTO.setPositionName(internshipExperience.getPositionName());
            internshipExceptionDTO.setJobStart(DateUtil.getStringYMD(internshipExperience.getJobStart()));
            internshipExceptionDTO.setJobEnd(DateUtil.getStringYMD(internshipExperience.getJobEnd()));
            internshipExceptionDTO.setWorkContent(internshipExperience.getWorkContent());
            internshipExceptionList.add(internshipExceptionDTO);
        }
        // 项目经历
        List<ProjectExperience> projectExperiences = projectExperienceService.list(new QueryWrapper<ProjectExperience>().lambda().eq(ProjectExperience::getResumeId, resume.getId()));
        for (ProjectExperience projectExperience : projectExperiences) {
            ProjectExperienceDTO projectExperienceDTO = new ProjectExperienceDTO();
            projectExperienceDTO.setId(projectExperience.getId());
            projectExperienceDTO.setResumeId(projectExperience.getResumeId());
            projectExperienceDTO.setProjectName(projectExperience.getProjectName());
            projectExperienceDTO.setProjectRole(projectExperience.getProjectRole());
            projectExperienceDTO.setProjectLink(projectExperience.getProjectLink());
            projectExperienceDTO.setProjectStart(DateUtil.getStringYMD(projectExperience.getProjectStart()));
            projectExperienceDTO.setProjectEnd(DateUtil.getStringYMD(projectExperience.getProjectEnd()));
            projectExperienceDTO.setProjectDesc(projectExperience.getProjectDesc());
            projectExperienceList.add(projectExperienceDTO);
        }
        // 教育经历
        List<EducationExperience> educationExperiences = educationExperienceService.list(new QueryWrapper<EducationExperience>().lambda().eq(EducationExperience::getResumeId, resume.getId()));
        for (EducationExperience educationExperience : educationExperiences) {
            EducationExperienceDTO educationExperienceDTO = new EducationExperienceDTO();
            educationExperienceDTO.setId(educationExperience.getId());
            educationExperienceDTO.setRemuseId(educationExperience.getResumeId());
            educationExperienceDTO.setSchoolName(educationExperience.getSchoolName());
            educationExperienceDTO.setCertificate(educationExperience.getCertificate());
            educationExperienceDTO.setMajor(educationExperience.getMajor());
            educationExperienceDTO.setStudyStart(DateUtil.getStringYMD(educationExperience.getStudyStart()));
            educationExperienceDTO.setStudyEnd(DateUtil.getStringYMD(educationExperience.getStudyEnd()));
            educationExperienceDTO.setSchoolExperience(educationExperience.getSchoolExperience());
            educationExperienceList.add(educationExperienceDTO);
        }
        // 资格证书
        List<Qualification> qualifications = qualificationService.list(new QueryWrapper<Qualification>().lambda().eq(Qualification::getResumeId, resume.getId()));
        for (Qualification qualification : qualifications) {
            QualificationDTO qualificationDTO = new QualificationDTO();
            qualificationDTO.setId(qualification.getId());
            qualificationDTO.setResumeId(qualification.getResumeId());
            qualificationDTO.setQualificationName(qualification.getQualificationName());
            qualificationList.add(qualificationDTO);
        }
        // 社交主页
        List<HomePage> homePages = homePageService.list(new QueryWrapper<HomePage>().lambda().eq(HomePage::getResumeId, resume.getId()));
        for (HomePage homePage : homePages) {
            HomePageDTO homePageDTO = new HomePageDTO();
            homePageDTO.setId(homePage.getId());
            homePageDTO.setResumeId(homePage.getResumeId());
            homePageDTO.setHomePageUrl(homePage.getHomePageUrl());
            homePageList.add(homePageDTO);
        }
        // 志愿服务经历
        List<VolunteerExperience> volunteerExperiences = volunteerExperienceService.list(new QueryWrapper<VolunteerExperience>().lambda().eq(VolunteerExperience::getResumeId, resume.getId()));
        for (VolunteerExperience volunteerExperience : volunteerExperiences) {
            VolunteerExperienceDTO volunteerExperienceDTO = new VolunteerExperienceDTO();
            volunteerExperienceDTO.setId(volunteerExperience.getId());
            volunteerExperienceDTO.setResumeId(volunteerExperience.getResumeId());
            volunteerExperienceDTO.setProjectName(volunteerExperience.getProjectName());
            volunteerExperienceDTO.setServiceDuration(volunteerExperience.getServiceDuration());
            volunteerExperienceDTO.setProjectStart(DateUtil.getStringYMD(volunteerExperience.getProjectStart()));
            volunteerExperienceDTO.setProjectEnd(DateUtil.getStringYMD(volunteerExperience.getProjectEnd()));
            volunteerExperienceDTO.setProjectDesc(volunteerExperience.getProjectDesc());
            volunteerExperienceList.add(volunteerExperienceDTO);
        }
        resumeInfo.setExpectedPositionList(expectedPositionList);
        resumeInfo.setInternshipExceptionList(internshipExceptionList);
        resumeInfo.setProjectExperienceList(projectExperienceList);
        resumeInfo.setEducationExperienceList(educationExperienceList);
        resumeInfo.setQualificationList(qualificationList);
        resumeInfo.setHomePageList(homePageList);
        resumeInfo.setVolunteerExperienceList(volunteerExperienceList);
        return resumeInfo;
    }
}
