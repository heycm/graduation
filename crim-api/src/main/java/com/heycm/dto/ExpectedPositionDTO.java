package com.heycm.dto;

import lombok.Data;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-05-07 20:13
 */
@Data
public class ExpectedPositionDTO {
    private Integer id;
    private Integer resumeId;
    private String positionName;
    private String expectedPay;
    private String expectedTrade;
    private String expectedCity;
}
