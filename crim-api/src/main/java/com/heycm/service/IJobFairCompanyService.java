package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.JobFairCompanyDTO;
import com.heycm.model.JobFairCompany;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface IJobFairCompanyService extends IService<JobFairCompany> {

    IPage<JobFairCompanyDTO> pageList(Page<JobFairCompanyDTO> page, QueryWrapper<JobFairCompanyDTO> qw);
}
