package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.*;
import com.heycm.model.*;
import com.heycm.param.Param;
import com.heycm.service.*;
import com.heycm.query.ResumeQuery;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2020-04-26
 */
@Slf4j
@Api(tags = "19 - 简历控制器 Resume")
@Transactional
@RestController
@RequestMapping("/api/v1/resume")
public class ResumeController {
    @Autowired
    public IResumeService resumeService;
    @Autowired
    public IUserService userService;
    @Autowired
    public IExpectedPositionService expectedPositionService;
    @Autowired
    public IInternshipExperienceService internshipExperienceService;
    @Autowired
    public IProjectExperienceService projectExperienceService;
    @Autowired
    public IEducationExperienceService educationExperienceService;
    @Autowired
    public IQualificationService qualificationService;
    @Autowired
    public IHomePageService homePageService;
    @Autowired
    public IVolunteerExperienceService volunteerExperienceService;
    @Autowired
    public IStudentService studentService;

    @Autowired
    public SubjectUtil subjectUtil;

    @ApiOperation(value = "1 - 学生获取个人简历信息", notes = "学生获取个人简历信息")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("student")
    @GetMapping("/info")
    public ResponseMessage info() {
        User user = userService.getById(subjectUtil.getCurrentUserId());
        if (user.getPid() != null) {
            user = userService.getById(user.getPid());
        }
        ResumeInfoDTO resumeInfoDTO = resumeService.getResumeInfoDTO(user.getId());
        return Result.ok(resumeInfoDTO);
    }

    @ApiOperation(value = "2 - 获取学生简历信息", notes = "获取学生简历信息")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"company", "school", "school-child"})
    @GetMapping("/{studentId}")
    public ResponseMessage get(@PathVariable("studentId") Integer studentId) {
        if (studentId == null) {
            return Result.error("参数不能为空");
        }
        ResumeInfoDTO resumeInfo = new ResumeInfoDTO();
        Student student = studentService.getById(studentId);
        if (student == null) {
            return Result.ok(resumeInfo);
        }
        ResumeInfoDTO resumeInfoDTO = resumeService.getResumeInfoDTO(student.getUserId());
        return Result.ok(resumeInfoDTO);
    }


    @ApiOperation(value = "3 - 编辑简历基本信息", notes = "编辑简历基本信息")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("student")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Resume resume) {
        if (resume == null || StringUtils.isBlank(resume.getName()) || resume.getSex() == null
                || resume.getBirth() == null || resume.getClassLevel() == null || resume.getJobStatus() == null || StringUtils.isBlank(resume.getPhone())) {
            return Result.error( "参数不能为空");
        }
        User user = userService.getById(subjectUtil.getCurrentUserId());
        if (user.getPid() != null){
            user = userService.getById(user.getPid());
        }
        resume.setUserId(user.getId());
        resumeService.saveOrUpdate(resume);
        return Result.ok();
    }

    /**
     * 根据ID删除对象信息
     *
     * @param id 对象id
     * @return ResponseMessage
     */
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("1000", "参数为NUll");
        }
        resumeService.removeById(id);
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
        resumeService.removeByIds(param.getIds());
        return Result.ok();
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Resume> entityList = resumeService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<ResumeQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Resume> page = new Page<Resume>(param.getPage(), param.getRows());
        IPage iPage = resumeService.page(page, null);
        return Result.ok(iPage);
    }
}
