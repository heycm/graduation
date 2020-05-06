package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.StudentPageDTO;
import com.heycm.model.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heycm.query.StudentQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-04
 */
public interface StudentMapper extends BaseMapper<Student> {

    IPage<StudentPageDTO> studentPage(Page<StudentPageDTO> page,  @Param(Constants.WRAPPER) QueryWrapper<StudentQuery> qw);
}
