package com.heycm.dto;

import lombok.Data;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-02 21:44
 */
@Data
public class SideDTO {
    private Integer campusId;
    private String campusName;
    private Integer buildingId;
    private String buildingName;
    private Integer siteId;
    private String siteName;
    private String siteDesc;
    private Integer siteStatus;
}
