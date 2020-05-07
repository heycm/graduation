package com.heycm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.heycm.model.*;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-07 20:04
 */
@Data
public class ResumeInfoDTO {

    private Integer id;
    private Integer userId;
    private String name;
    private Integer sex;
    @JSONField(format = "yyyy-MM")
    private String birth;
    private Integer classLevel;
    private Integer jobStatus;
    private String phone;
    private String weixin;
    private String email;
    private String advantage;

    private String idPhotoUrl;

    List<ExpectedPositionDTO> expectedPositionList;
    List<InternshipExceptionDTO> internshipExceptionList;
    List<ProjectExperienceDTO> projectExperienceList;
    List<EducationExperienceDTO> educationExperienceList;
    List<QualificationDTO> qualificationList;
    List<HomePageDTO> homePageList;
    List<VolunteerExperienceDTO> volunteerExperienceList;


}
