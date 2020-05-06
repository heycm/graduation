package com.heycm.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-04 19:03
 */
@Data
public class CompanyInfoDTO {

    private Integer id;

    private String companyName;

    private String linkMan;

    private String linkPhone;

    private String legalMan;

    private String registeredCapital;

    private Date registeredDate;
    private String registeredDateString;

    private String registeredPlace;

    private String businessStatus;

    private String companyType;

    private String companyAddress;

    private String creditCode;

    private String businessScope;

    private String companyDesc;

    private Integer status;

    private String licenseUrl;

    private String logoUrl;
}
