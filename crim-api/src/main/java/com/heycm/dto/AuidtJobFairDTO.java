package com.heycm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-05 16:46
 */
@Data
public class AuidtJobFairDTO {

    private Integer yearId;
    private String yearName;
    private Integer jobFairId;
    private String jobFairTitle;
    private Integer campusId;
    private String campusName;
    private Integer quarter;
    private Integer auditId;
    private Integer userId;
    private Date applyTime;
    private String applyTimeString;
    private Integer auditRes;
    private Date auditTime;
    private String auditTimeString;
    private String auditFeedback;
    private Integer auditStatus;


}
