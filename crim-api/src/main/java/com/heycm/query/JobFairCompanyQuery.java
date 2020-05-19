package com.heycm.query;

import lombok.Data;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Data
public class JobFairCompanyQuery {
    private Integer yearId;
    private Integer quarter;
    private String title;

    private String companyName;
    private Integer campusId;
}
