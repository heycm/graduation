package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.param.Param;
import com.heycm.service.IJobVacancyService;
import com.heycm.model.JobVacancy;
import com.heycm.query.JobVacancyQuery;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RestController
@Api(tags = "15 - 招聘职位控制器 JobVacancy")
@Transactional
@RequestMapping("/api/v1/jobVacancy")
public class JobVacancyController {
    @Autowired
    public IJobVacancyService jobVacancyService;


    @ApiOperation(value = "1 - 保存、修改", notes = "保存、修改")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("company")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody JobVacancy jobVacancy) {
        if (jobVacancy == null || jobVacancy.getCompanyId() == null || StringUtils.isEmpty(jobVacancy.getJobName())
                || StringUtils.isEmpty(jobVacancy.getWorkPlace()) || StringUtils.isEmpty(jobVacancy.getRegularPay())
                || StringUtils.isEmpty(jobVacancy.getExperience()) || StringUtils.isEmpty(jobVacancy.getCertificate())) {
            return Result.error("参数不能为空");
        }
        jobVacancyService.saveOrUpdate(jobVacancy);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 根据企业ID查看招聘职位", notes = "根据企业ID查看招聘职位")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"student"})
    @GetMapping("/list/{companyId}")
    public ResponseMessage jobVacancyList(@PathVariable("companyId") Integer companyId) {
        if (companyId == null) {
            return Result.error("参数不能为空");
        }
        List<JobVacancy> entityList = jobVacancyService.list(new QueryWrapper<JobVacancy>().lambda().eq(JobVacancy::getCompanyId, companyId));
        return Result.ok(entityList);
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
        jobVacancyService.removeById(id);
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
        jobVacancyService.removeByIds(param.getIds());
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
        JobVacancy entity = jobVacancyService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<JobVacancy> entityList = jobVacancyService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<JobVacancyQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<JobVacancy> page = new Page<JobVacancy>(param.getPage(), param.getRows());
        IPage iPage = jobVacancyService.page(page, null);
        return Result.ok(iPage);
    }
}
