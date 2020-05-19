package com.heycm.dto;

import lombok.Data;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-08 21:29
 */
@Data
public class InterviewDTO {

    private Integer id;
    private Integer companyId;
    private Integer careerTalkId;
    private Integer studentId;
    private String jobName;
    private String regularPay;
    private String workPlace;
    private String welfare;
    private Integer isLastChoice;
    private Integer status;

    private String companyName;

}
