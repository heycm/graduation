package com.heycm.query;

import lombok.Data;

/**
 * @author heycm@qq.com
 * @since 2020-05-04
 */
@Data
public class AuditRecordQuery {
    private Integer yearId;
    private Integer quarter;
    private String title;
    private Integer auditRes;

    private String companyName;
    private String creditCode;
}
