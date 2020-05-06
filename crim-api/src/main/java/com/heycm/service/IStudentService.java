package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.StudentPageDTO;
import com.heycm.model.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.query.StudentQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-04
 */
public interface IStudentService extends IService<Student> {

    IPage<StudentPageDTO> studentPage(Page<StudentPageDTO> page, QueryWrapper<StudentQuery> qw);

}
