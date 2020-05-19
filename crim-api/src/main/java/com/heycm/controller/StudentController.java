package com.heycm.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.StudentInfoDTO;
import com.heycm.dto.StudentPageDTO;
import com.heycm.model.*;
import com.heycm.param.Param;
import com.heycm.service.*;
import com.heycm.query.StudentQuery;
import com.heycm.utils.PasswordUtils;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.Tools;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    public IGradeClassService gradeClassService;
    @Autowired
    public IProfessionService professionService;
    @Autowired
    public IDepartmentService departmentService;
    @Autowired
    public ITypeService typeService;
    @Autowired
    public IFileService fileService;

    @Autowired
    public SubjectUtil subjectUtil;

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

    @ApiOperation(value = "4 - 修改学员信息", notes = "修改学员信息")
    @ApiOperationSupport(order = 4)
    @RequiresRoles(logical = Logical.OR, value = {"school"})
    @PostMapping("/update")
    public ResponseMessage update(@RequestBody Student student) {
        if (student == null || student.getId() == null || student.getUserId() == null) {
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

    @ApiOperation(value = "6 - 获取学生基本信息", notes = "获取学生基本信息")
    @ApiOperationSupport(order = 6)
    @RequiresRoles("student")
    @GetMapping("/info")
    public ResponseMessage baseInfo() {
        // 1.获取当前登录用户的学生信息
        Integer userId = subjectUtil.getCurrentUserId();
        User currentUser = userService.getById(userId);
        // 若当前登录用户是学生的子账户，则设置学生的用户id为父账户的id
        if (currentUser.getPid() != null) {
            userId = currentUser.getPid();
        }
        Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, userId));
        // 2.创建学生基本信息对象，并设置相关值
        StudentInfoDTO dto = new StudentInfoDTO();
        dto.setId(student.getId());
        dto.setUserId(student.getUserId());
        dto.setGradeClassId(student.getGradeClassId());
        dto.setStudentName(student.getStudentName());
        dto.setStudentNumber(student.getStudentNumber());
        dto.setStudentSex(student.getStudentSex());
        dto.setStudentBirth(DateUtil.getStringYMD(student.getStudentBirth()));
        dto.setStudentPhone(student.getStudentPhone());
        dto.setAllowPhoneLogin(student.getAllowPhoneLogin());
        dto.setStudentEmail(student.getStudentEmail());
        // 3.获取学生所在班级
        GradeClass gradeClass = gradeClassService.getById(student.getGradeClassId());
        // 4.获取班级归属年级
        Type yearLevel = typeService.getById(gradeClass.getYearLevelId());
        dto.setYearLevel(yearLevel.getTypeName());
        // 5.获取班级归属专业
        Profession profession = professionService.getById(gradeClass.getProfessionId());
        dto.setProfessionName(profession.getProfessionName());
        // 6.获取专业归属学院
        Department department = departmentService.getById(profession.getDepartmentId());
        dto.setDeptName(department.getDepartmentName());
        // 7.获取用户头像地址
        File file = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, student.getUserId()).eq(File::getFileType, 0));
        dto.setLogoUrl(file.getFileUrl());
        return Result.ok(dto);
    }

    @ApiOperation(value = "7 - 改变是否使用手机登录状态", notes = "改变是否使用手机登录状态")
    @ApiOperationSupport(order = 7)
    @RequiresRoles("student")
    @GetMapping("/changeAllowPhoneLogin")
    public ResponseMessage changeAllowPhoneLogin() {
        // 1.获取当前登录用户信息
        User currentUser = userService.getById(subjectUtil.getCurrentUserId());
        // 当前学生用户有父账户，说明当前用户是学生的手机号码账户，无权限进行此操作
        if (currentUser.getPid() != null) {
            throw new UnauthorizedException();
        }
        // 2.获取当前登录用户的学生信息
        Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, currentUser.getId()));
        // 3.拿到学生设置的手机号 和 是否使用手机号登录的状态
        String phone = student.getStudentPhone();
        // 4.过滤未设置手机号的情况
        if (StringUtils.isBlank(phone)) {
            return Result.error("未设置手机号");
        }
        // 5.过滤手机号格式不正确的情况
        if (!Tools.checkPhone(phone)) {
            return Result.error("手机号格式不正确");
        }
        // 6.查找该手机号是否已经注册为用户
        User phoneUser = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, phone));
        // 7.若该手机号未注册
        if (phoneUser == null) {
            // 7-1.创建新账户
            phoneUser = new User();
            // 7-2.设置用户名为手机号
            phoneUser.setUsername(phone);
            // 7-3.设置盐值为当前登录用户盐值
            phoneUser.setSalt(currentUser.getSalt());
            // 7-4.设置密码为当前登录用户密码
            phoneUser.setPassword(currentUser.getPassword());
            // 7-5.设置用户类型为当前登录用户类型
            phoneUser.setType(currentUser.getType());
            // 7-6.设置父账户为当前登录账户
            phoneUser.setPid(currentUser.getId());
            // 状态：0 正常
            phoneUser.setStatus(0);
            // 7-7.保存账户
            userService.save(phoneUser);
            // 7-8.为新账户创建角色容器
            ArrayList<UserRole> phoneUserRoles = new ArrayList<>();
            // 7-9.查找当前用户的角色列表
            List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, currentUser.getId()));
            // 7-10.将当前用户的角色列表装入新账户角色容器
            for (UserRole userRole : userRoles) {
                UserRole ur = new UserRole();
                ur.setUserId(phoneUser.getId());
                ur.setRoleId(userRole.getRoleId());
                phoneUserRoles.add(ur);
            }
            // 7-11.为新账户创建与当前登录账户相同角色列表
            userRoleService.saveBatch(phoneUserRoles);
            // 7-12.设置学生是否使用手机登录状态为 0(是)
            student.setAllowPhoneLogin(0);
            // 7-13.保存
            studentService.updateById(student);
            return Result.ok();
        }

        // 8.若该手机号已注册
        // 10.手机号被注册
        if (phoneUser.getPid() == null || !phoneUser.getPid().equals(currentUser.getId())) {
            return Result.error("手机号已被注册");
        }
        // 11.移除此手机号用户
        userService.removeById(phoneUser);
        // 12.移除此手机号用户的角色
        userRoleService.remove(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, phoneUser.getId()));
        // 13.设置学生是否使用手机登录状态为 1(否)
        student.setAllowPhoneLogin(1);
        studentService.updateById(student);
        return Result.ok();
    }


    @ApiOperation(value = "8 - 学员更新手机、邮箱", notes = "学员更新手机、邮箱")
    @ApiOperationSupport(order = 8)
    @RequiresRoles("student")
    @PostMapping("/own/update")
    public ResponseMessage stuUpdate(@RequestBody Student param) {
        if (param == null || param.getId() == null || param.getUserId() == null) {
            return Result.error("参数不能为空");
        }
        // 1.根据ID获取学生信息
        Student oldStu = studentService.getById(param.getId());
        // 2.未开启手机登录的情况
        if (!oldStu.getAllowPhoneLogin().equals(0)){
            studentService.updateById(param);
            return Result.ok();
        }
        // 3.已经开启手机登录，但入参无手机号（删除手机号）
        if (StringUtils.isBlank(param.getStudentPhone())){
            // 3-1.移除子账户
            userService.remove(new QueryWrapper<User>().lambda().eq(User::getUsername, oldStu.getStudentPhone()));
            // 3-2.设置手机登录为关闭
            param.setAllowPhoneLogin(1);
            studentService.updateById(param);
            return Result.ok();
        }
        // 4.已经开启手机登录，且入参有手机号码
        // 5.新手机号码与旧手机号码相同
        if (param.getStudentPhone().equals(oldStu.getStudentPhone())){
            studentService.updateById(param);
            return Result.ok();
        }
        // 6.新手机号码与旧手机号码不同
        // 7.使用新手机号码查找用户
        User newPhoneUser = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, param.getStudentPhone()));
        // 8.如果新手机号码已存在用户，说明已被别人注册
        if (newPhoneUser != null){
            return Result.error("手机号已被注册");
        }
        // 9.新手机账户不存在，查找旧手机账户，设置账户名为新手机号码
        User oldPhoneUser = userService.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, oldStu.getStudentPhone()));
        oldPhoneUser.setUsername(param.getStudentPhone());
        userService.updateById(oldPhoneUser);
        // 10.更新学生信息
        studentService.updateById(param);
        return Result.ok();
    }

    @ApiOperation(value = "9 - 获取学员的账户ID", notes = "获取学员的账户ID")
    @ApiOperationSupport(order = 9)
    @RequiresRoles(logical = Logical.OR, value = {"company"})
    @GetMapping("/user/{id}")
    public ResponseMessage getUserId(@PathVariable("id") Long id){
        if (id == null){
            return Result.error("参数不能为空");
        }
        Student student = studentService.getById(id);
        return Result.ok(student.getUserId());
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
