package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.CareerTalkStudentDTO;
import com.heycm.model.CareerTalkStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heycm.query.CareerTalkStudentQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface CareerTalkStudentMapper extends BaseMapper<CareerTalkStudent> {

    IPage<CareerTalkStudentDTO> pageList(Page<CareerTalkStudentDTO> page, @Param(Constants.WRAPPER) QueryWrapper<CareerTalkStudentQuery> qw);
}
