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
@TableName("crim_forbidden_record")
@ApiModel(value="ForbiddenRecord对象", description="")
public class ForbiddenRecord extends BaseModel<ForbiddenRecord> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "禁用记录id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "被禁账户")
    private Integer forbiddenId;

    @ApiModelProperty(value = "禁用原因")
    private String reason;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "逻辑删除：0 未删除 1 已删除")
    private Integer isDelete;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
