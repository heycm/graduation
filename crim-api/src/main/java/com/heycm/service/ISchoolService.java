package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.SchoolCodeDTO;
import com.heycm.model.School;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.utils.response.ResponseMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-28
 */
public interface ISchoolService extends IService<School> {

    ResponseMessage getCode();

    IPage<SchoolCodeDTO> getCodeByQW(Page<SchoolCodeDTO> page, QueryWrapper<SchoolCodeDTO> qw);
}
