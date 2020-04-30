package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.SchoolCodeDTO;
import com.heycm.model.School;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-28
 */
public interface SchoolMapper extends BaseMapper<School> {

    List<SchoolCodeDTO> getCode();

    IPage<SchoolCodeDTO> getCodeByQW(Page<SchoolCodeDTO> page, @Param(Constants.WRAPPER) QueryWrapper<SchoolCodeDTO> qw);
}
