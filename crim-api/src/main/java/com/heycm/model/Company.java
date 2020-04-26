package com.heycm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.heycm.model.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("crim_company")
@ApiModel(value="Company对象", description="")
public class Company extends BaseModel<Company> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "公司id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "logo文件id")
    private Integer logoId;

    @ApiModelProperty(value = "联系人")
    private String linkMan;

    @ApiModelProperty(value = "联系电话")
    private String linkPhone;

    @ApiModelProperty(value = "法人代表")
    private String legalMan;

    @ApiModelProperty(value = "注册资本")
    private String registeredCapital;

    @ApiModelProperty(value = "成立日期")
    private Date registeredDate;

    @ApiModelProperty(value = "注册地址")
    private String registeredPlace;

    @ApiModelProperty(value = "经营状态")
    private String businessStatus;

    @ApiModelProperty(value = "公司类型")
    private String companyType;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "社会统一信用代码")
    private String creditCode;

    @ApiModelProperty(value = "经营范围")
    private String businessScope;

    @ApiModelProperty(value = "公司简介")
    private String companyDesc;

    @ApiModelProperty(value = "状态：-1 黑名单 0 审核通过 1 未审核 2 审核中 3 审核不通过 4 审核信息过期")
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
