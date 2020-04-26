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
@TableName("crim_job_fair_company")
@ApiModel(value="JobFairCompany对象", description="")
public class JobFairCompany extends BaseModel<JobFairCompany> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "招聘会/企业 关联表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "招聘会id")
    private Integer jobFairId;

    @ApiModelProperty(value = "企业id")
    private Integer companyId;

    @ApiModelProperty(value = "校区id：type表campus子节点id")
    private Integer campusId;

    @ApiModelProperty(value = "分配场地id")
    private Integer siteId;

    @ApiModelProperty(value = "状态")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
