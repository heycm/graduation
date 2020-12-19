package com.heycm.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.JobFairCompanyDTO;
import com.heycm.dto.JobFairPageDTO;
import com.heycm.model.*;
import com.heycm.param.Param;
import com.heycm.query.JobFairQuery;
import com.heycm.service.ICompanyService;
import com.heycm.service.IJobFairCompanyService;
import com.heycm.query.JobFairCompanyQuery;
import com.heycm.service.ISiteService;
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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Slf4j
@Api(tags = "16 - 招聘会与企业控制器 JobFairCompany")
@Transactional
@RestController
@RequestMapping("/api/v1/jobFairCompany")
public class JobFairCompanyController {
    @Autowired
    public IJobFairCompanyService jobFairCompanyService;
    @Autowired
    public ICompanyService companyService;
    @Autowired
    public ISiteService siteService;

    @Autowired
    public SubjectUtil subjectUtil;


    @ApiOperation(value = "1 - 分页查询企业参与的招聘会", notes = "分页查询企业参与的招聘会")
    @ApiOperationSupport(order = 1)
    @RequiresRoles(logical = Logical.OR, value = {"company"})
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<JobFairCompanyQuery> param) {
        long l = System.currentTimeMillis();
        log.info("[分页查询企业参与的招聘会][入参:{}][结束]", JSON.toJSONString(param));
        if (param == null) {
            param = new Param<JobFairCompanyQuery>();
        }
        // 1.设置默认当前页是第 1 页
        if (param.getPage() == null) {
            param.setPage(1);
        }
        // 2.设置默认每页 5 条数据
        if (param.getRows() == null) {
            param.setRows(5);
        }
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, subjectUtil.getCurrentUserId()));
        QueryWrapper<JobFairCompanyDTO> qw = new QueryWrapper<>();
        qw.eq("t1.company_id", company.getId());
        JobFairCompanyQuery data = param.getData();
        if (data != null) {
            qw.eq(data.getYearId() != null, "t1.year_id", data.getYearId())
                    .eq(data.getQuarter() != null, "t1.quarter", data.getQuarter())
                    .eq(StringUtils.isNotEmpty(data.getTitle()), "t1.job_fair_title", data.getTitle());
        }
        Page<JobFairCompanyDTO> page = new Page<>(param.getPage(), param.getRows());
        IPage<JobFairCompanyDTO> iPage = jobFairCompanyService.pageList(page, qw);
        for (JobFairCompanyDTO record : iPage.getRecords()) {
            record.setStartTimeString(DateUtil.getStringYMD(record.getStartTime()));
            record.setEndTimeString(DateUtil.getStringYMD(record.getEndTime()));
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
        log.info("[分页查询企业参与的招聘会][耗时:{}ms][查询结果:{}][结束]", (System.currentTimeMillis() - l), JSON.toJSONString(iPage));
        return Result.ok(iPage);
    }


    @ApiOperation(value = "2 - 学校查询参与企业列表", notes = "学校查询参与企业列表")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"school"})
    @PostMapping("/company/list")
    public ResponseMessage joinCompanyList(@RequestBody Param<JobFairCompanyQuery> param) {
        long l = System.currentTimeMillis();
        log.info("[学校查询参与企业列表][入参:{}][结束]", JSON.toJSONString(param));
        if (param == null) {
            param = new Param<JobFairCompanyQuery>();
        }
        // 1.设置默认当前页是第 1 页
        if (param.getPage() == null) {
            param.setPage(1);
        }
        // 2.设置默认每页 5 条数据
        if (param.getRows() == null) {
            param.setRows(5);
        }
        QueryWrapper<JobFairCompanyDTO> qw = new QueryWrapper<>();
        JobFairCompanyQuery data = param.getData();
        if (data != null) {
            qw.eq(data.getYearId() != null, "t1.year_id", data.getYearId())
                    .eq(data.getQuarter() != null, "t1.quarter", data.getQuarter())
                    .eq(data.getCampusId() != null, "t1.campus_id", data.getCampusId())
                    .like(StringUtils.isNotBlank(data.getTitle()), "t1.job_fair_title", data.getTitle())
                    .like(StringUtils.isNotBlank(data.getCompanyName()), "t1.company_name", data.getCompanyName());
        }
        Page<JobFairCompanyDTO> page = new Page<>(param.getPage(), param.getRows());
        IPage<JobFairCompanyDTO> iPage = jobFairCompanyService.joinCompanyList(page, qw);

        log.info("[学校查询参与企业列表][耗时:{}ms][查询结果:{}][结束]", (System.currentTimeMillis() - l), JSON.toJSONString(iPage));
        return Result.ok(iPage);
    }

    @ApiOperation(value = "3 - 分配场地", notes = "分配场地")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @GetMapping("/site/{id}/{siteId}")
    public ResponseMessage allocateSite(@PathVariable("id") Integer id, @PathVariable("siteId") Integer siteId) {
        if (id == null || siteId == null) {
            return Result.error("参数不能为空");
        }
        JobFairCompany byId = jobFairCompanyService.getById(id);
        if (byId == null) {
            return Result.error("无记录");
        }
        Site site = siteService.getById(siteId);
        if (site == null) {
            return Result.error("无场地");
        }
        if (!site.getStatus().equals(0)) {
            return Result.error("场地已被占用");
        }
        // 记录原来已经分配的场地id
        Integer oldSiteId = byId.getSiteId();
        // 如果已经分配，改变原场地状态
        if (oldSiteId != null) {
            Site oldSite = siteService.getById(oldSiteId);
            oldSite.setStatus(0);
            siteService.updateById(oldSite);
        }
        // 设置分配场地ID
        byId.setSiteId(siteId);
        // 设置分配状态
        byId.setStatus(0);
        jobFairCompanyService.updateById(byId);
        // 改变场地信息状态
        site.setStatus(1);
        siteService.updateById(site);
        return Result.ok();
    }


    /**
     * 保存、修改 【区分id即可】
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<JobFairCompany> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        jobFairCompanyService.saveOrUpdate(param.getData());
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
        jobFairCompanyService.removeById(id);
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
        jobFairCompanyService.removeByIds(param.getIds());
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
        JobFairCompany entity = jobFairCompanyService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<JobFairCompany> entityList = jobFairCompanyService.list();
        return Result.ok(entityList);
    }
}
