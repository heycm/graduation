package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.JobFairPageDTO;
import com.heycm.model.JobFair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.query.JobFairQuery;
import com.heycm.utils.response.ResponseMessage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface IJobFairService extends IService<JobFair> {

    IPage<JobFairPageDTO> pageList(Page<JobFair> page, QueryWrapper<JobFairQuery> qw);

    List<JobFairPageDTO> applyList();
}
