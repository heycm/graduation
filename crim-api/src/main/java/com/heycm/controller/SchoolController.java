package com.heycm.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.SchoolCodeDTO;
import com.heycm.dto.SchoolInfoDTO;
import com.heycm.dto.SchoolUserDTO;
import com.heycm.dto.TreeDTO;
import com.heycm.model.*;
import com.heycm.param.Param;
import com.heycm.service.*;
import com.heycm.query.SchoolQuery;
import com.heycm.utils.JwtUtil;
import com.heycm.utils.PasswordUtils;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-28
 */
@Slf4j
@Api(tags = "03 - 学校控制器 School")
@Transactional
@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {
    @Autowired
    public ISchoolService schoolService;
    @Autowired
    public IFileService fileService;
    @Autowired
    public IUserService userService;
    @Autowired
    public IUserRoleService userRoleService;
    @Autowired
    public IRoleService roleService;
    @Autowired
    public ITypeService typeService;
    @Autowired
    public IDepartmentService departmentService;
    @Autowired
    public IProfessionService professionService;

    @Autowired
    public SubjectUtil subjectUtil;
    @Autowired
    public JwtUtil jwtUtil;

    /**
     * 保存、修改 【区分id即可】
     *
     * @param school school
     * @return ResponseMessage
     */
    @ApiOperation(value = "1 - 增加或更新", notes = "增加或更新")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody School school) {
        if (school == null) {
            return Result.error("参数为NUll");
        }
        schoolService.saveOrUpdate(school);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 获取学校信息", notes = "获取学校信息")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"school", "school-child"})
    @GetMapping("/info")
    public ResponseMessage getSchoolInfo() {
        SchoolInfoDTO schoolInfoDTO = new SchoolInfoDTO();
        School school = schoolService.getById(1);
        Integer userId = subjectUtil.getCurrentUserId();
        LambdaQueryWrapper<File> eq = new QueryWrapper<File>().lambda().eq(File::getUserId, userId).eq(File::getFileType, 0);
        File one = fileService.getOne(eq);
        if (one != null) {
            schoolInfoDTO.setLogoUrl(one.getFileUrl());
        }
        schoolInfoDTO.setSchoolAddress(school.getSchoolAddress());
        schoolInfoDTO.setSchoolDesc(school.getSchoolDesc());
        schoolInfoDTO.setSchoolName(school.getSchoolName());
        schoolInfoDTO.setSchoolPostalCode(school.getSchoolPostalCode());
        return Result.ok(schoolInfoDTO);
    }

    @ApiOperation(value = "3 - 更新学校信息", notes = "更新学校信息")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @PostMapping("/info")
    public ResponseMessage updateSchoolInfo(@RequestBody School school) {
        if (school == null || StringUtils.isEmpty(school.getSchoolName())
                || StringUtils.isEmpty(school.getSchoolAddress()) || StringUtils.isEmpty(school.getSchoolPostalCode())) {
            return Result.error("参数不能为空");
        }
        school.setId(1);
        schoolService.saveOrUpdate(school);
        return Result.ok();
    }

    @ApiOperation(value = "4 - 增加账户设置", notes = "增加账户设置")
    @ApiOperationSupport(order = 4)
    @RequiresRoles("school")
    @PostMapping("/addChild")
    public ResponseMessage addChild(@RequestBody SchoolQuery schoolQuery) {
        if (schoolQuery == null || StringUtils.isEmpty(schoolQuery.getUsername())
                || StringUtils.isEmpty(schoolQuery.getPassword()) || schoolQuery.getRoleId() == null) {
            return Result.error("参数不能为空");
        }
        Integer currentUserId = subjectUtil.getCurrentUserId();
        String salt = PasswordUtils.generateSalt();
        String encrypt = PasswordUtils.encrypt(schoolQuery.getPassword(), salt);
        User child = new User();
        child.setPid(currentUserId);
        child.setUsername(schoolQuery.getUsername());
        child.setPassword(encrypt);
        child.setSalt(salt);
        child.setType(0);
        child.setStatus(0);
        userService.save(child);
        UserRole userRole = new UserRole();
        userRole.setRoleId(schoolQuery.getRoleId());
        userRole.setUserId(child.getId());
        userRoleService.save(userRole);
        return Result.ok();
    }

    @ApiOperation(value = "5 - 获取账户和子账户列表", notes = "获取账户和子账户列表")
    @ApiOperationSupport(order = 5)
    @RequiresRoles("school")
    @GetMapping("/userList")
    public ResponseMessage userList() {
        Integer currentUserId = subjectUtil.getCurrentUserId();
        ArrayList<SchoolUserDTO> schoolUserDTOS = new ArrayList<>();
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda().eq(User::getType, 0);
        List<User> list = userService.list(eq);
        for (User user : list) {
            SchoolUserDTO dto = new SchoolUserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setMain(false);
            if (user.getId().equals(currentUserId)) {
                dto.setMain(true);
            }
            schoolUserDTOS.add(dto);
        }
        for (SchoolUserDTO userListDTO : schoolUserDTOS) {
            LambdaQueryWrapper<UserRole> eq1 = new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userListDTO.getId());
            UserRole one = userRoleService.getOne(eq1);
            Role role = roleService.getById(one.getRoleId());
            userListDTO.setRoleName(role.getRoleName());
        }
        return Result.ok(schoolUserDTOS);
    }

    @ApiOperation(value = "6 - 获取学院专业设置", notes = "获取学院专业设置")
    @ApiOperationSupport(order = 6)
    @RequiresRoles("school")
    @GetMapping("/dept")
    public ResponseMessage dept() {
        // 1.校区类型pid为1，找到校区list
        List<Type> campus = typeService.list(new QueryWrapper<Type>().lambda().eq(Type::getPid, 1));
        // 2.创建树根
        ArrayList<TreeDTO> treeRoot = new ArrayList<>();
        // 3.遍历校区list
        for (Type type : campus) {
            // 4.创建校区节点
            TreeDTO campusNode = new TreeDTO();
            // 5.设置校区节点id
            campusNode.setId(type.getId());
            // 6.设置校区节点名称
            campusNode.setLabel(type.getTypeName());
            // 7.创建校区节点的children容器
            ArrayList<TreeDTO> campusNodeChildren = new ArrayList<>();
            // 8.根据校区id，找到学院list
            List<Department> departmentList = departmentService.list(new QueryWrapper<Department>().lambda().eq(Department::getCampusId, type.getId()));
            for (Department department : departmentList) {
                // 9.创建学院节点
                TreeDTO deptNode = new TreeDTO();
                // 10.设置学院节点id
                deptNode.setId(department.getId());
                // 11.设置学院节点名称
                deptNode.setLabel(department.getDepartmentName());
                // 12.创建学院节点children容器
                ArrayList<TreeDTO> deptNodeChildren = new ArrayList<>();
                // 13.根据学院od，找到专业list
                List<Profession> professionList = professionService.list(new QueryWrapper<Profession>().lambda().eq(Profession::getDepartmentId, department.getId()));
                for (Profession profession : professionList) {
                    // 14.创建专业节点，设置节点id和名称
                    TreeDTO proNode = new TreeDTO();
                    proNode.setId(profession.getId());
                    proNode.setLabel(profession.getProfessionName());
                    // 15.专业节点加入学院children列表
                    deptNodeChildren.add(proNode);
                }
                // 16.设置学院children
                deptNode.setChildren(deptNodeChildren);
                // 17.学院节点加入校区children列表
                campusNodeChildren.add(deptNode);
            }
            // 18.设置校区children
            campusNode.setChildren(campusNodeChildren);
            // 19.校区加入树根
            treeRoot.add(campusNode);
        }
        // 20.返回树根
        return Result.ok(treeRoot);
    }

    @ApiOperation(value = "7 - 获取学院代码", notes = "获取学院代码")
    @ApiOperationSupport(order = 7)
    @RequiresRoles("school")
    @GetMapping("/code")
    public ResponseMessage code() {
        return schoolService.getCode();
    }

    @ApiOperation(value = "8 - 按条件获取学院代码", notes = "按条件获取学院代码，分页")
    @ApiOperationSupport(order = 8)
    @RequiresRoles("school")
    @PostMapping("/code")
    public ResponseMessage code(@RequestBody Param<SchoolCodeDTO> param) {
        long l = System.currentTimeMillis();
        log.info("[按条件获取学院代码][入参:{}][结束]", JSON.toJSONString(param));
        if (param == null) {
            param = new Param<SchoolCodeDTO>();
        }
        // 1.设置默认当前页是第 1 页
        if (param.getPage() == null) {
            param.setPage(1);
        }
        // 2.设置默认每页 5 条数据
        if (param.getRows() == null) {
            param.setRows(5);
        }
        // 3.条件构造器构造查询条件
        QueryWrapper<SchoolCodeDTO> qw = new QueryWrapper<SchoolCodeDTO>();
        if (param.getData() != null) {
            SchoolCodeDTO paramData = param.getData();
            // 3-1.如果部门ID不是空，构造条件 dept_id = 部门ID
            qw.eq(paramData.getDeptId() != null, "tab.dept_id", paramData.getDeptId())
                    // 3-2.如果专业ID不是空，构造条件 AND pro_id = 专业ID
                    .eq(paramData.getProId() != null, "tab.pro_id", paramData.getProId())
                    // 3-3.如歌年级ID不是空，构造条件 AND year_id = 年级ID
                    .eq(paramData.getYearId() != null, "tab.year_id", paramData.getYearId())
                    // 3-4.如果班级名称不是空或空字符串，构造条件 AND class_name like '%班级名称%'
                    .like(StringUtils.isNotEmpty(paramData.getClassName()), "tab.class_name", paramData.getClassName());
        }
        // 4.分页构造器，传入当前页和每页条数
        Page<SchoolCodeDTO> page = new Page<>(param.getPage(), param.getRows());
        // 5.查询
        IPage<SchoolCodeDTO> iPage = schoolService.getCodeByQW(page, qw);
        log.info("[按条件获取学院代码][查询结果:{}][耗时:{}ms][结束]", JSON.toJSONString(iPage), (System.currentTimeMillis() - l));
        return Result.ok(iPage);
    }


    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("1000", "参数为NUll");
        }
        schoolService.removeById(id);
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
        schoolService.removeByIds(param.getIds());
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
        School entity = schoolService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<School> entityList = schoolService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<SchoolQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<School> page = new Page<School>(param.getPage(), param.getRows());
        IPage iPage = schoolService.page(page, null);
        return Result.ok(iPage);
    }
}
