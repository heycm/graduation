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

    private List<String> roles;
    private String token;

}
