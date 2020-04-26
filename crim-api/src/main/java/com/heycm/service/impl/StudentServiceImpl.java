package com.heycm.service.impl;

import com.heycm.model.Student;
import com.heycm.mapper.StudentMapper;
import com.heycm.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
