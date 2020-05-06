package com.heycm.dto;

import com.heycm.model.File;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-03 20:38
 */
@Data
public class JobFairPageDTO {
    private Integer id;
    private Integer yearId;
    private String yearName;
    private Integer quarter;
    private String title;
    private String content;
    private Date startTime;
    private String startTimeString;
    private Date endTime;
    private String endTimeString;
    private List<File> files;
    private Integer status;

    private String progress;

}
