package com.heycm.dto;

import com.heycm.model.Permission;
import com.heycm.model.Role;
import com.heycm.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Description 用户角色权限关系
 * @Author heycm@qq.com
 * @Date 2020-04-27 16:13
 */
@Data
@ApiModel(value = "UserRolePermissionDTO对象", description = "用户角色权限关系")
public class UserRolePermissionDTO {

    @ApiModelProperty(value = "用户对象")
    private User user;

    @ApiModelProperty(value = "用户对象的角色列表")
    private List<Role> roles;

    @ApiModelProperty(value = "角色与权限的关系表")
    private Map<Integer, List<Permission>> permissionMap;

}
