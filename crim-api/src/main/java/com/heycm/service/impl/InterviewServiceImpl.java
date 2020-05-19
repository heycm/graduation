package com.heycm.service.impl;

import com.heycm.model.Interview;
import com.heycm.mapper.InterviewMapper;
import com.heycm.service.IInterviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-08
 */
@Service
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview> implements IInterviewService {

    @Resource
    InterviewMapper interviewMapper;

    @Override
    public void setIsLastChoiceZero(Integer studentId) {
        interviewMapper.setIsLastChoiceZero(studentId);
    }
}
