package com.heycm.dto;

import lombok.Data;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-06 21:47
 */
@Data
public class CareerTalkStudentDTO {

    private Integer jobFairId;
    private Integer companyId;
    private Integer campusId;
    private Integer yearId;
    private Integer quarter;
    private String yearName;
    private String jobFairTitle;
    private String campusName;
    private String careerTalkTitle;
    // 宣讲会与学生关系表id
    private Integer id;
    private Integer studentId;
    private String studentName;
    private Integer studentSex;
    private String studentYearLevel;
    private Integer professionId;
    private String professionName;

}
