package com.heycm.service;

import com.heycm.model.School;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.utils.response.ResponseMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-28
 */
public interface ISchoolService extends IService<School> {

    ResponseMessage getCode();
}
