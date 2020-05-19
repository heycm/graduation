package com.heycm.service;

import com.heycm.model.Interview;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-08
 */
public interface IInterviewService extends IService<Interview> {

    void setIsLastChoiceZero(Integer studentId);
}
