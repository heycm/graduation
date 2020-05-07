package com.heycm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-07 20:18
 */
@Data
public class EducationExperienceDTO {
    private Integer id;
    private Integer remuseId;
    private String schoolName;
    private Integer certificate;
    private String major;
    @JSONField(format = "yyyy-MM")
    private String studyStart;
    @JSONField(format = "yyyy-MM")
    private String studyEnd;
    private String schoolExperience;
}
