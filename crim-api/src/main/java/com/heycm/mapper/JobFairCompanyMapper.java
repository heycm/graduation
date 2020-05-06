package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.JobFairCompanyDTO;
import com.heycm.model.JobFairCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface JobFairCompanyMapper extends BaseMapper<JobFairCompany> {


    IPage<JobFairCompanyDTO> pageList(Page<JobFairCompanyDTO> page, @Param(Constants.WRAPPER) QueryWrapper<JobFairCompanyDTO> qw);
}
