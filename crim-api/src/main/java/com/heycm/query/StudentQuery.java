package com.heycm.query;

import lombok.Data;

import java.util.Date;

/**
 * @author heycm@qq.com
 * @since 2020-05-04
 */
@Data
public class StudentQuery {

    private Integer id;
    private Integer proId;
    private Integer yearId;
    private Integer classId;
    private String number;
    private String name;
    private Integer sex;
    private Date birth;

}
