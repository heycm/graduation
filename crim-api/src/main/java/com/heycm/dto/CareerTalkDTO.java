package com.heycm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-06 14:21
 */
@Data
public class CareerTalkDTO {

    private Integer id;
    private String careerTalkTitle;
    private String careerTalkDesc;
    private Date startTime;
    private Date endTime;


    private String startTimeString;

    private String endTimeString;


    private String progress;

    private Integer jobFairCompanyId;
    private Integer yearId;
    private Integer quarter;
    private Integer jobFairId;
    private Integer companyId;
    private Integer campusId;
    private String jobFairTitle;
    private String campusName;
    private String yearName;

    @JSONField(format = "yyyy-MM-dd")
    private String jobFairStartTime;
    @JSONField(format = "yyyy-MM-dd")
    private String jobFairEndTime;

}
