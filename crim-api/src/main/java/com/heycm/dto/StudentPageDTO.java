package com.heycm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-04 11:21
 */
@Data
public class StudentPageDTO {

    private Integer professionId;
    private String professionName;

    private Integer yearId;
    private String yearName;

    private Integer classId;
    private String className;

    private Integer studentId;
    private Integer userId;
    private String studentNumber;
    private String studentName;
    private Integer studentSex;
    private Date studentBirth;
    private String birthString;

}
