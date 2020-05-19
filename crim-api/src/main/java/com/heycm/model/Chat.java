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
@TableName("crim_chat")
@ApiModel(value="Chat对象", description="")
public class Chat extends BaseModel<Chat> {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "消息id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "消息类型：0 文本 1 文件")
    private Integer type;

    @ApiModelProperty(value = "发送者id")
    private Integer fromId;

    @ApiModelProperty(value = "接受者id")
    private Integer toId;

    @ApiModelProperty(value = "消息内容（文本内容/文件链接）")
    private String content;

    @ApiModelProperty(value = "错误代码")
    private Integer errCode;

    @ApiModelProperty(value = "错误描述")
    private String errMsg;

    @ApiModelProperty(value = "发送时间")
    private Date sendTime;

    @ApiModelProperty(value = "消息状态：0 已发送 1 未发送")
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
