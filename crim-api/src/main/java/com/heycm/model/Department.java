package com.heycm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("crim_department")
@ApiModel(value="Department对象", description="")
public class Department extends BaseModel<Department> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "院系id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "校区id：type表campus子节点id")
    private Integer campusId;

    @ApiModelProperty(value = "院系名称")
    private String departmentName;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
