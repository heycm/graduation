package com.heycm.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-07 0:24
 */
@Data
public class StudentInfoDTO {

    private Integer id;
    private Integer userId;
    private Integer gradeClassId;
    private String studentNumber;
    private String studentName;
    private Integer studentSex;
    private String studentBirth;
    private String studentPhone;
    private String studentEmail;
    private Integer allowPhoneLogin;

    private String deptName;
    private String professionName;
    private String yearLevel;
    private String logoUrl;

}
