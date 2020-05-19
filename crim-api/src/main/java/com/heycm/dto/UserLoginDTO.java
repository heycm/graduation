package com.heycm.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-28 0:55
 */
@Data
public class UserLoginDTO {

    private Integer isAudit;
    private List<String> roles;
    private String token;
    private Integer id;


}
