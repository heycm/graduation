package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.InterviewDTO;
import com.heycm.model.Company;
import com.heycm.model.Student;
import com.heycm.model.User;
import com.heycm.param.Param;
import com.heycm.service.ICompanyService;
import com.heycm.service.IInterviewService;
import com.heycm.model.Interview;
import com.heycm.query.InterviewQuery;
import com.heycm.service.IStudentService;
import com.heycm.service.IUserService;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2020-05-08
 */
@Slf4j
@Api(tags = "27 - 面试控制器 Interview")
@Transactional
@RestController
@RequestMapping("/api/v1/interview")
public class InterviewController {
    @Autowired
    public IInterviewService interviewService;
    @Autowired
    public ICompanyService companyService;
    @Autowired
    public IUserService userService;
    @Autowired
    public IStudentService studentService;

    @Autowired
    public SubjectUtil subjectUtil;

    @ApiOperation(value = "1 - 学生查看面试列表", notes = "学生查看面试列表")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("student")
    @GetMapping("/list")
    public ResponseMessage list() {
        User user = userService.getById(subjectUtil.getCurrentUserId());
        Integer userId = user.getId();
        if (user.getPid() != null) {
            userId = user.getPid();
        }
        Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, userId));

        List<Interview> interviews = interviewService.list(new QueryWrapper<Interview>().lambda().eq(Interview::getStudentId, student.getId()));

        List<InterviewDTO> list = new ArrayList<>();

        for (Interview interview : interviews) {
            InterviewDTO dto = new InterviewDTO();
            dto.setId(interview.getId());
            dto.setCompanyId(interview.getCompanyId());
            dto.setCareerTalkId(interview.getCareerTalkId());
            dto.setStudentId(interview.getStudentId());
            dto.setJobName(interview.getJobName());
            dto.setRegularPay(interview.getRegularPay());
            dto.setWorkPlace(interview.getWorkPlace());
            dto.setWelfare(interview.getWelfare());
            dto.setIsLastChoice(interview.getIsLastChoice());
            dto.setStatus(interview.getStatus());

            Company company = companyService.getById(interview.getCompanyId());
            if (company != null) {
                dto.setCompanyName(company.getCompanyName());
            }

            list.add(dto);
        }

        return Result.ok(list);
    }

    @ApiOperation(value = "1 - 学生查看面试列表", notes = "学生查看面试列表")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("student")
    @GetMapping("/choice/{id}")
    public ResponseMessage finalChoiceChange(@PathVariable("id") Integer id) {
        if (id == null) {
            return Result.error("参数不能为空");
        }
        // 获取需要改变状态的记录
        Interview interview = interviewService.getById(id);
        if (interview == null) {
            return Result.error("面试记录不存在");
        }
        if (!interview.getStatus().equals(2)) {
            return Result.error("未被录用");
        }
        Integer choice = interview.getIsLastChoice().equals(0) ? 1 : 0;
        interview.setIsLastChoice(choice);
        // 设置该学生所有未删除的面试记录为0
        interviewService.setIsLastChoiceZero(interview.getStudentId());
        // 更新需要改变状态的记录
        interviewService.updateById(interview);
        return Result.ok();
    }


    /**
     * 保存、修改 【区分id即可】
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<Interview> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        interviewService.saveOrUpdate(param.getData());
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
        interviewService.removeById(id);
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
        interviewService.removeByIds(param.getIds());
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
        Interview entity = interviewService.getById(id);
        return Result.ok(entity);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<InterviewQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Interview> page = new Page<Interview>(param.getPage(), param.getRows());
        IPage iPage = interviewService.page(page, null);
        return Result.ok(iPage);
    }
}
