package com.heycm.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-03 21:44
 */
@Data
public class StudentExcel implements Serializable {

    @Excel(name = "班级代码", isImportField = "true")
    private Integer classCode;

    @Excel(name = "学生学号", isImportField = "true")
    private String number;

    @Excel(name = "学生姓名", isImportField = "true")
    private String name;

    @Excel(name = "学生性别", isImportField = "true", replace = {"男_0", "女_1"})
    private Integer sex;

    @Excel(name = "出生年月", isImportField = "true")
    private Date birth;

}
