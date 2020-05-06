package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.StudentPageDTO;
import com.heycm.model.Student;
import com.heycm.mapper.StudentMapper;
import com.heycm.query.StudentQuery;
import com.heycm.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-04
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Resource
    StudentMapper studentMapper;

    @Override
    public IPage<StudentPageDTO> studentPage(Page<StudentPageDTO> page, QueryWrapper<StudentQuery> qw) {
        return studentMapper.studentPage(page, qw);
    }
}
