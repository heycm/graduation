package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.CareerTalkStudentDTO;
import com.heycm.model.*;
import com.heycm.param.Param;
import com.heycm.query.CareerTalkQuery;
import com.heycm.service.*;
import com.heycm.query.CareerTalkStudentQuery;
import com.heycm.utils.SubjectUtil;
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

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Slf4j
@Api(tags = "18 - 宣讲会学生控制器 CareerTalkStudent")
@Transactional
@RestController
@RequestMapping("/api/v1/careerTalkStudent")
public class CareerTalkStudentController {
    @Autowired
    public ICareerTalkStudentService careerTalkStudentService;
    @Autowired
    public ICompanyService companyService;
    @Autowired
    public IStudentService studentService;
    @Autowired
    public IUserService userService;
    @Autowired
    public IJobVacancyService jobVacancyService;
    @Autowired
    public IInterviewService interviewService;
    @Autowired
    public SubjectUtil subjectUtil;

    @ApiOperation(value = "1 - 分页查询参加宣讲会的学生列表", notes = "分页查询参加宣讲会的学生列表")
    @ApiOperationSupport(order = 1)
    @RequiresRoles(logical = Logical.OR, value = {"company"})
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<CareerTalkStudentQuery> param) {
        if (param == null) {
            param = new Param<CareerTalkStudentQuery>();
        }
        if (param.getPage() == null) {
            param.setPage(1);
        }
        if (param.getRows() == null) {
            param.setRows(5);
        }
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, subjectUtil.getCurrentUserId()));
        QueryWrapper<CareerTalkStudentQuery> qw = new QueryWrapper<>();
        qw.eq("t1.company_id", company.getId());
        CareerTalkStudentQuery data = param.getData();
        if (data != null) {
            qw.eq(data.getYearId() != null, "t1.year_id", data.getYearId())
                    .eq(data.getQuarter() != null, "t1.quarter", data.getQuarter())
                    .like(StringUtils.isNotBlank(data.getJobFairTitle()), "t1.job_fair_title", data.getJobFairTitle())
                    .like(StringUtils.isNotBlank(data.getCareerTalkTitle()), "t1.career_talk_title", data.getCareerTalkTitle());
        }
        Page<CareerTalkStudentDTO> page = new Page<CareerTalkStudentDTO>(param.getPage(), param.getRows());
        IPage<CareerTalkStudentDTO> iPage = careerTalkStudentService.pageList(page, qw);
        return Result.ok(iPage);
    }


    @ApiOperation(value = "2 - 删除参加宣讲会的学生记录", notes = "删除参加宣讲会的学生记录")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"student"})
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("参数不能为空");
        }
        CareerTalkStudent careerTalkStudent = careerTalkStudentService.getById(id);
        if (careerTalkStudent == null){
            return Result.ok();
        }
        // 移除宣讲会与学生关系表记录
        careerTalkStudentService.removeById(id);
        // 移除面试表记录
        interviewService.remove(new QueryWrapper<Interview>().lambda().eq(Interview::getCareerTalkId, careerTalkStudent.getCareerTalkId()).eq(Interview::getStudentId, careerTalkStudent.getStudentId()));
        return Result.ok();
    }


    @ApiOperation(value = "3 - 学生加入宣讲会", notes = "学生加入宣讲会")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("student")
    @PostMapping("/join")
    public ResponseMessage join(@RequestBody CareerTalkStudentQuery query) {
        if (query == null || query.getCareerTalkId() == null || query.getCompanyId() == null || query.getJobVacancyId() == null) {
            return Result.error("参数不能为空");
        }
        JobVacancy jobVacancy = jobVacancyService.getById(query.getJobVacancyId());
        if (jobVacancy==null){
            return Result.error("职位不存在");
        }

        User user = userService.getById(subjectUtil.getCurrentUserId());
        Integer userId = user.getId();
        if (user.getPid()!=null){
            userId = user.getPid();
        }
        Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, userId));

        CareerTalkStudent careerTalkStudent = new CareerTalkStudent();
        Interview interview = new Interview();

        careerTalkStudent.setCareerTalkId(query.getCareerTalkId());
        careerTalkStudent.setStudentId(student.getId());

        interview.setCareerTalkId(query.getCareerTalkId());
        interview.setCompanyId(query.getCompanyId());
        interview.setStudentId(student.getId());
        interview.setIsLastChoice(0);
        interview.setStatus(0);
        interview.setJobName(jobVacancy.getJobName());
        interview.setWorkPlace(jobVacancy.getWorkPlace());
        interview.setRegularPay(jobVacancy.getRegularPay());
        interview.setWelfare(jobVacancy.getWelfare());

        careerTalkStudentService.save(careerTalkStudent);
        interviewService.save(interview);

        return Result.ok();
    }


    /**
     * 保存、修改 【区分id即可】
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<CareerTalkStudent> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        careerTalkStudentService.saveOrUpdate(param.getData());
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
        careerTalkStudentService.removeByIds(param.getIds());
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
        CareerTalkStudent entity = careerTalkStudentService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<CareerTalkStudent> entityList = careerTalkStudentService.list();
        return Result.ok(entityList);
    }


}
