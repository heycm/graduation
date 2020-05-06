package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.JobFairPageDTO;
import com.heycm.model.JobFair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heycm.query.JobFairQuery;
import com.heycm.utils.response.ResponseMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface JobFairMapper extends BaseMapper<JobFair> {

    IPage<JobFairPageDTO> pageList(Page<JobFair> page, @Param(Constants.WRAPPER) QueryWrapper<JobFairQuery> qw);

    List<JobFairPageDTO> applyList();
}
