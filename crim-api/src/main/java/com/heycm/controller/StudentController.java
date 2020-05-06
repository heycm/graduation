package com.heycm.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.StudentPageDTO;
import com.heycm.model.StudentExcel;
import com.heycm.model.User;
import com.heycm.model.UserRole;
import com.heycm.param.Param;
import com.heycm.service.IStudentService;
import com.heycm.model.Student;
import com.heycm.query.StudentQuery;
import com.heycm.service.IUserRoleService;
import com.heycm.service.IUserService;
import com.heycm.utils.PasswordUtils;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author heycm@qq.com
 * @since 2020-05-04
 */
@Slf4j
@Api(tags = "12 - 学生控制器 Student")
@Transactional
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    public IStudentService studentService;
    @Autowired
    public IUserService userService;
    @Autowired
    public IUserRoleService userRoleService;

    @ApiOperation(value = "1 - 导入学生列表", notes = "导入学生列表")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/import")
    public ResponseMessage importStudents(MultipartFile file) throws Exception {
        if (file == null) {
            Result.error("上传文件为空");
        }
        // 1.设置导入参数
        ImportParams params = new ImportParams();
        // 1-1.标题一行
        params.setTitleRows(1);
        // 1-2.列名称一行
        params.setHeadRows(1);
        // 2.读数据
        List<StudentExcel> excel = ExcelImportUtil.importExcel(file.getInputStream(), StudentExcel.class, params);
        if (excel.size() == 0) {
            Result.error("上传文件无数据");
        }
        // 3.遍历读到的数据（学生），为每一条数据建立一个用户
        for (int i = 0; i < excel.size(); i++) {
            StudentExcel studentExcel = excel.get(i);
            // 检查是否重复录入
            User one = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, studentExcel.getNumber()));
            if (one != null) {
                StringBuilder msg = new StringBuilder();
                msg.append("学号：").append(one.getUsername()).append("已存在，请检查！")
                        .append("成功录入").append(i).append("条数据，录入失败").append(excel.size() - i).append("条数据。");
                return Result.error(msg.toString());
            }
            // 建立一个用户
            User user = new User();
            // 类型：学生
            user.setType(1);
            // 用户名：学号
            user.setUsername(studentExcel.getNumber());
            String salt = PasswordUtils.generateSalt();
            String encrypt = PasswordUtils.encrypt(studentExcel.getNumber(), salt);
            // 盐
            user.setSalt(salt);
            // 初始密码：学号
            user.setPassword(encrypt);
            // 状态：正常
            user.setStatus(0);
            // 4.保存用户，得到用户id
            userService.save(user);
            // 5.分配角色
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(2);
            userRoleService.save(userRole);
            // 6.创建学生信息
            Student student = new Student();
            student.setUserId(user.getId());
            student.setGradeClassId(studentExcel.getClassCode());
            student.setStudentNumber(studentExcel.getNumber());
            student.setStudentName(studentExcel.getName());
            student.setStudentSex(studentExcel.getSex());
            student.setStudentBirth(studentExcel.getBirth());
            // 7.保存学生信息
            studentService.save(student);
        }
        return Result.ok();
    }


    @ApiOperation(value = "2 - 分页查询数据", notes = "分页查询数据")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("school")
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<StudentQuery> param) {
        if (param == null) {
            param = new Param<StudentQuery>();
        }
        if (param.getPage() == null) {
            param.setPage(1);
        }
        if (param.getRows() == null) {
            param.setRows(5);
        }
        QueryWrapper<StudentQuery> qw = new QueryWrapper<>();
        StudentQuery data = param.getData();
        if (data != null) {
            qw.eq(data.getProId() != null, "t1.profession_id", data.getProId())
                    .eq(data.getYearId() != null, "t1.year_id", data.getYearId())
                    .eq(data.getClassId() != null, "t1.class_id", data.getClassId())
                    .eq(StringUtils.isNotEmpty(data.getNumber()), "t1.student_number", data.getNumber())
                    .like(StringUtils.isNotEmpty(data.getName()), "t1.student_name", data.getName());
        }
        Page<StudentPageDTO> page = new Page<StudentPageDTO>(param.getPage(), param.getRows());
        IPage<StudentPageDTO> iPage = studentService.studentPage(page, qw);
        for (StudentPageDTO record : iPage.getRecords()) {
            record.setBirthString(DateUtil.getStringYMD(record.getStudentBirth()));
        }
        return Result.ok(iPage);
    }


    @ApiOperation(value = "3 - 保存并创建用户", notes = "保存并创建用户")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Student student) {
        if (student == null || student.getGradeClassId() == null || student.getStudentSex() == null ||
                student.getStudentBirth() == null || StringUtils.isEmpty(student.getStudentNumber()) ||
                StringUtils.isEmpty(student.getStudentName())) {
            return Result.error("参数不能为空");
        }
        User one = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, student.getStudentNumber()));
        if (one != null) {
            return Result.error("账户已存在");
        }
        String salt = PasswordUtils.generateSalt();
        String encrypt = PasswordUtils.encrypt(student.getStudentNumber(), salt);
        // 创建新账户
        User user = new User();
        user.setUsername(student.getStudentNumber());
        user.setSalt(salt);
        user.setPassword(encrypt);
        user.setType(1);
        user.setStatus(0);
        // 保存账户
        userService.save(user);
        // 分配账户角色
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(2);
        userRoleService.save(userRole);
        // 设置学生的账户id
        student.setUserId(user.getId());
        // 保存学生信息
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @ApiOperation(value = "4 - 修改", notes = "修改")
    @ApiOperationSupport(order = 4)
    @RequiresRoles("school")
    @PostMapping("/update")
    public ResponseMessage update(@RequestBody Student student) {
        if (student == null || student.getId() == null) {
            return Result.error("参数不能为空");
        }
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @ApiOperation(value = "5 - 根据ID删除对象信息", notes = "根据ID删除对象信息")
    @ApiOperationSupport(order = 5)
    @RequiresRoles("school")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Integer id) {
        if (id == null) {
            return Result.error("参数不能为空");
        }
        studentService.removeById(id);
        return Result.ok();
    }

    /**
     * 根据IDs批量删除
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/batchDel")
    public ResponseMessage delete(@RequestBody Param param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        studentService.removeByIds(param.getIds());
        return Result.ok();
    }

    /**
     * 根据ID获取对象信息
     *
     * @param id 对象id
     * @return ResponseMessage
     */
    @GetMapping("/{id}")
    public ResponseMessage get(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("1000", "参数为NUll");
        }
        Student entity = studentService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Student> entityList = studentService.list();
        return Result.ok(entityList);
    }


}
