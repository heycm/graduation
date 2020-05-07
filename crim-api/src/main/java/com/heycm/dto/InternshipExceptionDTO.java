package com.heycm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-07 20:14
 */
@Data
public class InternshipExceptionDTO {
    private Integer id;
    private Integer resumeId;
    private String companyName;
    private String companyTrade;
    private String affiliatedDept;
    private String positionName;
    @JSONField(format = "yyyy-MM")
    private String jobStart;
    @JSONField(format = "yyyy-MM")
    private String jobEnd;
    private String workContent;
}
