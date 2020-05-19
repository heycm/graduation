package com.heycm.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.heycm.model.File;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-09 23:01
 */
@Data
public class WaitAuditDTO {

    private Integer id;

    private Integer auditType;
    private Integer userId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String applyTime;

    private String companyName;
    private String creditCode;
    private String linkMan;
    private String linkPhone;
    private String logoUrl;

    private List<File> license;

}
