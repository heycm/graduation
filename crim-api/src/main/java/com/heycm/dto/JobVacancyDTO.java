package com.heycm.dto;

import lombok.Data;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-05 1:36
 */
@Data
public class JobVacancyDTO {

    private Integer id;

    private Integer companyId;

    private String jobName;

    private String workPlace;

    private String regularPay;

    private String experience;

    private String certificate;

    private String skill;

    private String[] skills;

    private String jobDesc;

    private String welfare;

    private Integer status;
}
