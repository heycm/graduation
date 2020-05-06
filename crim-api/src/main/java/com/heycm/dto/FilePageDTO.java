package com.heycm.dto;

import lombok.Data;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-03 18:21
 */
@Data
public class FilePageDTO {

    private Integer id;
    private Integer userId;
    private String fileName;
    private String fileSuffix;
    private Integer fileType;
    private Integer fileSize;
    private String fileUrl;
    private Integer status;
    private Integer targetId;
    private String jobFairTitle;


}
