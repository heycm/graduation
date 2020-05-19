package com.heycm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-05 23:17
 */
@Data
public class JobFairCompanyDTO {

    private Integer id;
    private Integer yearId;
    private String yearName;
    private Integer companyId;
    private Integer jobFairId;
    private Integer quarter;
    private String jobFairTitle;

    // @JSONField(format = "yyyy-MM-dd")
    // private String startTime;
    // @JSONField(format = "yyyy-MM-dd")
    // private String endTime;
    private Date startTime;
    private String startTimeString;
    private Date endTime;
    private String endTimeString;

    private String progress;
    private Integer jobFairStatus;
    private Integer campusId;
    private String campusName;
    private Integer siteId;
    private String siteName;
    private String siteDesc;
    private Integer siteStatus;

    private String buildingName;
    private String companyName;

}
