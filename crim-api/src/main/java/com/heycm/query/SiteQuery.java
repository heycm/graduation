package com.heycm.query;

import lombok.Data;

/**
 * @author heycm@qq.com
 * @since 2020-04-30
 */
@Data
public class SiteQuery {
    private Integer campusId;
    private Integer buildingId;
    private String siteName;

    private Integer siteStatus;
}
