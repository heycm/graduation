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
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("crim_building")
@ApiModel(value="Building对象", description="")
public class Building extends BaseModel<Building> {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "建筑id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "校区id")
    private Integer campusId;

    @ApiModelProperty(value = "建筑名称")
    private String buildingName;

    @ApiModelProperty(value = "建筑描述")
    private String buildingDesc;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
