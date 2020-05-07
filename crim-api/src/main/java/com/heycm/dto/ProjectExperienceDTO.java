package com.heycm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-07 20:17
 */
@Data
public class ProjectExperienceDTO {
    private Integer id;
    private Integer resumeId;
    private String projectName;
    private String projectRole;
    private String projectLink;
    @JSONField(format = "yyyy-MM")
    private String projectStart;
    @JSONField(format = "yyyy-MM")
    private String projectEnd;
    private String projectDesc;
}
