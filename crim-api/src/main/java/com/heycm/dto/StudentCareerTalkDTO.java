package com.heycm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description 学生查询招聘会宣讲会
 * @Author heycm@qq.com
 * @Date 2020-05-07 0:24
 */
@Data
public class StudentCareerTalkDTO {

    // 年份ID
    private Integer yearId;
    // 年份名
    private String yearName;

    // 招聘会ID
    private Integer jobFairId;
    // 招聘会标题
    private String jobFairTitle;
    // 招聘会季度
    private Integer quarter;

    // 校区名称
    private String campusName;
    // 场地名称
    private String siteName;
    // 场地描述
    private String siteDesc;

    // 企业ID
    private Integer companyId;
    // 企业名称
    private String companyName;

    // 宣讲会ID
    private Integer id;
    // 宣讲会标题
    private String careerTalkTitle;
    // 宣讲会开始时间
    private Date startTime;
    // 宣讲会结束时间
    private Date endTime;

    private String timeCycle;

    // 关注ID
    private Integer starId;
    // 学生ID
    private Integer starStudentId;

    // 宣讲会与学生关系表ID
    private Integer careerTalkStudentId;
    // 宣讲会与学生关系表学生ID
    private Integer ctsStudentId;

}
