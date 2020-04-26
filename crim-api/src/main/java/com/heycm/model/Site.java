package com.heycm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.heycm.model.base.BaseModel;
import java.io.Serializable;
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
@TableName("crim_site")
@ApiModel(value="Site对象", description="")
public class Site extends BaseModel<Site> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "场地id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "学校id")
    private Integer schoolId;

    @ApiModelProperty(value = "校区id")
    private Integer campusId;

    @ApiModelProperty(value = "建筑/楼名称")
    private String buildingName;

    @ApiModelProperty(value = "场地名称")
    private String siteName;

    @ApiModelProperty(value = "场地描述")
    private String siteDesc;

    @ApiModelProperty(value = "状态：0 空闲 1 已占用")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
