package com.heycm.dto;

import lombok.Data;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-29 15:40
 */
@Data
public class SchoolUserDTO {

    private Integer id;
    private String username;
    private boolean isMain;
    private String roleName;

}
