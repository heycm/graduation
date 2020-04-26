package com.heycm.model.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author heycm@qq.com
 * @Date 2020-04-26 15:30
 */
@Data
@ApiModel(value="BaseModel对象", description="")
public class BaseModel<T> extends Model {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "创建人id", example = "")
    @TableField(fill = FieldFill.INSERT)
    private Integer createUser;

    @ApiModelProperty(value = "创建时间", example = "")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "最新修改人id", example = "")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer modifyUser;

    @ApiModelProperty(value = "最新修改时间", example = "")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;

    @ApiModelProperty(value = "逻辑删除：0 未删除 1 已删除", example = "")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;
}
