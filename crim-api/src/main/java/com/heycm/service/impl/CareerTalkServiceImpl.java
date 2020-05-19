package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.CareerTalkDTO;
import com.heycm.dto.StudentCareerTalkDTO;
import com.heycm.model.CareerTalk;
import com.heycm.mapper.CareerTalkMapper;
import com.heycm.query.CareerTalkQuery;
import com.heycm.service.ICareerTalkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Service
public class CareerTalkServiceImpl extends ServiceImpl<CareerTalkMapper, CareerTalk> implements ICareerTalkService {

    @Resource
    CareerTalkMapper careerTalkMapper;

    @Override
    public IPage<CareerTalkDTO> pageList(Page<CareerTalkDTO> page, QueryWrapper<CareerTalkQuery> qw) {
        return careerTalkMapper.pageList(page, qw);
    }

    @Override
    public IPage<StudentCareerTalkDTO> stuPageList(Page<StudentCareerTalkDTO> page, Integer studentId, QueryWrapper<CareerTalkQuery> qw) {
        return careerTalkMapper.stuPageList(page, studentId, qw);
    }
}
