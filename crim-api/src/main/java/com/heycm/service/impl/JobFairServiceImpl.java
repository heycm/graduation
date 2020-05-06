package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.JobFairPageDTO;
import com.heycm.model.JobFair;
import com.heycm.mapper.JobFairMapper;
import com.heycm.query.JobFairQuery;
import com.heycm.service.IJobFairService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Service
public class JobFairServiceImpl extends ServiceImpl<JobFairMapper, JobFair> implements IJobFairService {

    @Resource
    JobFairMapper jobFairMapper;

    @Override
    public IPage<JobFairPageDTO> pageList(Page<JobFair> page, QueryWrapper<JobFairQuery> qw) {
        return jobFairMapper.pageList(page, qw);
    }

    @Override
    public List<JobFairPageDTO> applyList() {
        return jobFairMapper.applyList();
    }
}
