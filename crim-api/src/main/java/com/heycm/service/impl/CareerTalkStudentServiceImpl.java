package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.CareerTalkStudentDTO;
import com.heycm.model.CareerTalkStudent;
import com.heycm.mapper.CareerTalkStudentMapper;
import com.heycm.query.CareerTalkStudentQuery;
import com.heycm.service.ICareerTalkStudentService;
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
public class CareerTalkStudentServiceImpl extends ServiceImpl<CareerTalkStudentMapper, CareerTalkStudent> implements ICareerTalkStudentService {

    @Resource
    CareerTalkStudentMapper careerTalkStudentMapper;

    @Override
    public IPage<CareerTalkStudentDTO> pageList(Page<CareerTalkStudentDTO> page, QueryWrapper<CareerTalkStudentQuery> qw) {
        return careerTalkStudentMapper.pageList(page, qw);
    }
}
