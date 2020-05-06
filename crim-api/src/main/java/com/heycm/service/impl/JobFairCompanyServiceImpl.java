package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.JobFairCompanyDTO;
import com.heycm.mapper.JobFairMapper;
import com.heycm.model.JobFairCompany;
import com.heycm.mapper.JobFairCompanyMapper;
import com.heycm.service.IJobFairCompanyService;
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
public class JobFairCompanyServiceImpl extends ServiceImpl<JobFairCompanyMapper, JobFairCompany> implements IJobFairCompanyService {

    @Resource
    JobFairCompanyMapper jobFairCompanyMapper;

    @Override
    public IPage<JobFairCompanyDTO> pageList(Page<JobFairCompanyDTO> page, QueryWrapper<JobFairCompanyDTO> qw) {
        return jobFairCompanyMapper.pageList(page, qw);
    }
}
