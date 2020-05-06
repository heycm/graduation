package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.CareerTalkDTO;
import com.heycm.model.Company;
import com.heycm.param.Param;
import com.heycm.service.ICareerTalkService;
import com.heycm.model.CareerTalk;
import com.heycm.query.CareerTalkQuery;
import com.heycm.service.ICompanyService;
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

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Slf4j
@Api(tags = "17 - 宣讲会控制器 CareerTalk")
@Transactional
@RestController
@RequestMapping("/api/v1/careerTalk")
public class CareerTalkController {
    @Autowired
    public ICareerTalkService careerTalkService;
    @Autowired
    public ICompanyService companyService;
    @Autowired
    public SubjectUtil subjectUtil;

    @ApiOperation(value = "1 - 分页查询企业的宣讲会", notes = "分页查询企业的宣讲会")
    @ApiOperationSupport(order = 1)
    @RequiresRoles(logical = Logical.OR, value = {"company"})
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<CareerTalkQuery> param) {
        if (param == null) {
            param = new Param<CareerTalkQuery>();
        }
        if (param.getPage() == null) {
            param.setPage(1);
        }
        if (param.getRows() == null) {
            param.setRows(5);
        }
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, subjectUtil.getCurrentUserId()));
        QueryWrapper<CareerTalkQuery> qw = new QueryWrapper<>();
        qw.eq("t1.company_id", company.getId());

        CareerTalkQuery data = param.getData();
        if (data != null) {
            qw.eq(data.getYearId() != null, "t1.year_id", data.getYearId())
                    .eq(data.getQuarter() != null, "t1.quarter", data.getQuarter())
                    .like(StringUtils.isNotBlank(data.getJobFairTitle()), "t1.job_fair_title", data.getJobFairTitle())
                    .like(StringUtils.isNotBlank(data.getCareerTalkTitle()), "t1.career_talk_title", data.getCareerTalkTitle());
        }
        qw.orderByDesc("t1.start_time");
        Page<CareerTalkDTO> page = new Page<CareerTalkDTO>(param.getPage(), param.getRows());
        IPage<CareerTalkDTO> iPage = careerTalkService.pageList(page, qw);
        for (CareerTalkDTO record : iPage.getRecords()) {
            if (record.getStartTime() != null && record.getEndTime() != null) {
                record.setStartTimeString(DateUtil.getStringYMDHMS(record.getStartTime()));
                record.setEndTimeString(DateUtil.getStringYMDHMS(record.getEndTime()));
                long now = System.currentTimeMillis();
                long start = record.getStartTime().getTime();
                long end = record.getEndTime().getTime();
                if (now < start) {
                    record.setProgress("未开始");
                } else if (now > end) {
                    record.setProgress("已结束");
                } else {
                    record.setProgress("进行中");
                }
            }
        }
        return Result.ok(iPage);
    }

    @ApiOperation(value = "2 - 保存、修改宣讲会", notes = "保存、修改宣讲会")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("company")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody CareerTalk careerTalk) {
        if (careerTalk == null || careerTalk.getJobFairCompanyId() == null || careerTalk.getStartTime() == null
                || careerTalk.getEndTime() == null || StringUtils.isBlank(careerTalk.getCareerTalkTitle())) {
            return Result.error("参数不能为空");
        }
        careerTalkService.saveOrUpdate(careerTalk);
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
        careerTalkService.removeById(id);
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
        careerTalkService.removeByIds(param.getIds());
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
        CareerTalk entity = careerTalkService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<CareerTalk> entityList = careerTalkService.list();
        return Result.ok(entityList);
    }
}
