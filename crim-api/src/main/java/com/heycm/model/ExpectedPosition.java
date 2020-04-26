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
@TableName("crim_expected_position")
@ApiModel(value="ExpectedPosition对象", description="")
public class ExpectedPosition extends BaseModel<ExpectedPosition> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "期望职位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "简历id")
    private Integer resumeId;

    @ApiModelProperty(value = "期望职位，如：UI设计师...")
    private String positionName;

    @ApiModelProperty(value = "期望薪资，如：10-12K")
    private String expectedPay;

    @ApiModelProperty(value = "期望行业，如：金融/互联网/制造业...")
    private String expectedTrade;

    @ApiModelProperty(value = "期望城市，如：北京/广州/武汉...")
    private String expectedCity;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
