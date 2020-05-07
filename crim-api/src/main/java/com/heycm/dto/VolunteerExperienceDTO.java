package com.heycm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-07 20:21
 */
@Data
public class VolunteerExperienceDTO {
    private Integer id;
    private Integer resumeId;
    private String projectName;
    private Integer serviceDuration;
    @JSONField(format = "yyyy-MM")
    private String projectStart;
    @JSONField(format = "yyyy-MM")
    private String projectEnd;
    private String projectDesc;
}
