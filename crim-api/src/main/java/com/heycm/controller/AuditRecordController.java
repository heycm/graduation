package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.AuidtJobFairDTO;
import com.heycm.dto.WaitAuditDTO;
import com.heycm.model.*;
import com.heycm.param.Param;
import com.heycm.query.JobFairQuery;
import com.heycm.service.*;
import com.heycm.query.AuditRecordQuery;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.util.Builder;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-05-04
 */
@RestController
@Api(tags = "14 - 审核记录控制器 AuditRecord")
@Transactional
@RequestMapping("/api/v1/auditRecord")
public class AuditRecordController {
    @Autowired
    public IAuditRecordService auditRecordService;
    @Autowired
    public IUserService userService;
    @Autowired
    public ICompanyService companyService;
    @Autowired
    public IJobFairCompanyService jobFairCompanyService;
    @Autowired
    public IFileService fileService;
    @Autowired
    public SubjectUtil subjectUtil;

    @ApiOperation(value = "1 - 注册审核", notes = "注册审核")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("company")
    @GetMapping("/register")
    public ResponseMessage register() {
        Integer currentUserId = subjectUtil.getCurrentUserId();

        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, currentUserId));
        company.setStatus(1);
        companyService.updateById(company);

        AuditRecord auditRecord = auditRecordService.getOne(new QueryWrapper<AuditRecord>().lambda().eq(AuditRecord::getUserId, currentUserId)
                .eq(AuditRecord::getAuditType, 0).eq(AuditRecord::getStatus, 1));
        if (auditRecord != null) {
            return Result.ok("正在审核中，请耐心等待");
        }
        auditRecord = new AuditRecord();
        auditRecord.setUserId(currentUserId);
        auditRecord.setAuditType(0);
        auditRecord.setApplyTime(new Date());
        auditRecord.setStatus(1);
        auditRecordService.save(auditRecord);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 通过注册审核", notes = "通过注册审核")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("school")
    @PostMapping("/register/pass/{id}")
    public ResponseMessage passRegister(@PathVariable("id") Integer id, String auditFeedBack) {

        if (id == null) {
            return Result.error("参数不能为空");
        }
        AuditRecord byId = auditRecordService.getById(id);
        if (byId == null) {
            return Result.error("无该审核记录");
        }
        if (byId.getStatus().equals(0)) {
            return Result.ok("该记录已审核完成");
        }
        byId.setAuditRes(0);
        byId.setStatus(0);
        byId.setAuditFeedback(auditFeedBack);
        byId.setAuditUser(subjectUtil.getCurrentUserId());
        byId.setAuditTime(new Date());
        auditRecordService.updateById(byId);

        User user = userService.getById(byId.getUserId());
        user.setStatus(0);
        userService.updateById(user);
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, user.getId()));
        company.setStatus(0);
        companyService.updateById(company);
        return Result.ok();
    }

    @ApiOperation(value = "3 - 拒绝注册审核", notes = "拒绝注册审核")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @PostMapping("/register/deny/{id}")
    public ResponseMessage denyRegister(@PathVariable("id") Integer id, String auditFeedBack) {
        if (id == null || StringUtils.isBlank(auditFeedBack)) {
            return Result.error("参数不能为空");
        }
        AuditRecord byId = auditRecordService.getById(id);
        if (byId == null) {
            return Result.error("无该审核记录");
        }
        if (byId.getStatus().equals(0)) {
            return Result.ok("该记录已审核完成");
        }
        byId.setAuditRes(1);
        byId.setStatus(0);
        byId.setAuditFeedback(auditFeedBack);
        byId.setAuditUser(subjectUtil.getCurrentUserId());
        byId.setAuditTime(new Date());
        auditRecordService.updateById(byId);
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, byId.getUserId()));
        company.setStatus(2);
        companyService.updateById(company);
        return Result.ok();
    }


    @ApiOperation(value = "4 - 申请入会审核记录", notes = "申请入会审核记录")
    @ApiOperationSupport(order = 4)
    @RequiresRoles("company")
    @PostMapping("/applyJobFairAuditRecords")
    public ResponseMessage applyJobFairAuditRecords(@RequestBody Param<AuditRecordQuery> param) {

        if (param == null) {
            param = new Param<AuditRecordQuery>();
        }
        if (param.getPage() == null) {
            param.setPage(1);
        }
        if (param.getRows() == null) {
            param.setRows(5);
        }
        QueryWrapper<AuditRecordQuery> qw = new QueryWrapper<>();
        qw.eq("t1.user_id", subjectUtil.getCurrentUserId());
        AuditRecordQuery data = param.getData();
        if (data != null) {
            qw.eq(data.getYearId() != null, "t1.year_id", data.getYearId())
                    .eq(data.getQuarter() != null, "t1.quarter", data.getQuarter())
                    .eq(data.getAuditRes() != null, "t1.audit_res", data.getAuditRes())
                    .like(StringUtils.isNotEmpty(data.getTitle()), "t1.title", data.getTitle());
        }
        Page<AuidtJobFairDTO> page = new Page<>(param.getPage(), param.getRows());
        IPage<AuidtJobFairDTO> iPage = auditRecordService.applyJobFairAuditRecords(page, qw);
        for (AuidtJobFairDTO record : iPage.getRecords()) {
            record.setApplyTimeString(DateUtil.dateToStr(record.getApplyTime()));
            if (record.getAuditTime() != null) {
                record.setAuditTimeString(DateUtil.dateToStr(record.getAuditTime()));
            }
        }
        return Result.ok(iPage);
    }


    @ApiOperation(value = "5 - 增加申请入会审核", notes = "增加申请入会审核")
    @ApiOperationSupport(order = 5)
    @RequiresRoles("company")
    @GetMapping("/joinJobFair/{jobFairId}/{campusId}")
    public ResponseMessage joinJobFair(@PathVariable("jobFairId") Integer jobFairId, @PathVariable("campusId") Integer campusId) {
        if (jobFairId == null || campusId == null) {
            return Result.error("参数不能为空");
        }
        Integer currentUserId = subjectUtil.getCurrentUserId();

        AuditRecord auditRecord = auditRecordService.getOne(new QueryWrapper<AuditRecord>().lambda().eq(AuditRecord::getUserId, currentUserId)
                .eq(AuditRecord::getAuditType, 1).eq(AuditRecord::getJobFairId, jobFairId).eq(AuditRecord::getCampusId, campusId)
                .eq(AuditRecord::getStatus, 1));
        if (auditRecord != null) {
            return Result.ok("正在审核中，请耐心等待");
        }
        auditRecord = new AuditRecord();
        auditRecord.setUserId(currentUserId);
        auditRecord.setAuditType(1);
        auditRecord.setJobFairId(jobFairId);
        auditRecord.setCampusId(campusId);
        auditRecord.setApplyTime(new Date());
        auditRecord.setStatus(1);

        auditRecordService.save(auditRecord);
        return Result.ok("申请成功，等待审核");
    }

    @ApiOperation(value = "6 - 通过申请入会审核", notes = "通过申请入会审核")
    @ApiOperationSupport(order = 6)
    @RequiresRoles("school")
    @PostMapping("/joinJobFair/pass/{id}")
    public ResponseMessage passJoinJobFair(@PathVariable("id") Integer id, String auditFeedBack) {
        if (id == null) {
            return Result.error("参数不能为空");
        }
        AuditRecord byId = auditRecordService.getById(id);
        if (byId == null) {
            return Result.error("无该审核记录");
        }
        if (byId.getStatus().equals(0)) {
            return Result.ok("该记录已审核完成");
        }
        User user = userService.getById(byId.getUserId());
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, user.getId()));
        JobFairCompany jobFairCompany = new JobFairCompany();
        jobFairCompany.setJobFairId(byId.getJobFairId());
        jobFairCompany.setCampusId(byId.getCampusId());
        jobFairCompany.setCompanyId(company.getId());
        jobFairCompany.setStatus(1);
        jobFairCompanyService.save(jobFairCompany);
        byId.setAuditRes(0);
        byId.setAuditTime(new Date());
        byId.setAuditFeedback(auditFeedBack);
        byId.setAuditUser(subjectUtil.getCurrentUserId());
        byId.setStatus(0);
        auditRecordService.updateById(byId);
        return Result.ok();
    }

    @ApiOperation(value = "7 - 拒绝申请入会审核", notes = "拒绝申请入会审核")
    @ApiOperationSupport(order = 7)
    @RequiresRoles("school")
    @PostMapping("/joinJobFair/deny/{id}")
    public ResponseMessage denyJoinJobFair(@PathVariable("id") Integer id, String auditFeedBack) {
        if (id == null || StringUtils.isBlank(auditFeedBack)) {
            return Result.error("参数不能为空");
        }
        AuditRecord byId = auditRecordService.getById(id);
        if (byId == null) {
            return Result.error("无该审核记录");
        }
        if (byId.getStatus().equals(0)) {
            return Result.ok("该记录已审核完成");
        }
        byId.setAuditRes(1);
        byId.setAuditTime(new Date());
        byId.setAuditFeedback(auditFeedBack);
        byId.setAuditUser(subjectUtil.getCurrentUserId());
        byId.setStatus(0);
        auditRecordService.updateById(byId);
        return Result.ok();
    }

    @ApiOperation(value = "7 - 查询待审核列表", notes = "查询待审核列表")
    @ApiOperationSupport(order = 7)
    @RequiresRoles("school")
    @PostMapping("/wait")
    public ResponseMessage waitAuditList(@RequestBody Param<AuditRecordQuery> param) {
        if (param == null) {
            param = new Param<AuditRecordQuery>();
        }
        if (param.getPage() == null) {
            param.setPage(1);
        }
        if (param.getRows() == null) {
            param.setRows(5);
        }
        IPage<WaitAuditDTO> page = new Page<>(param.getPage(), param.getRows());
        QueryWrapper<AuditRecordQuery> qw = new QueryWrapper<>();
        AuditRecordQuery query = param.getData();
        if (query != null) {
            qw.eq(StringUtils.isNotBlank(query.getCreditCode()), "t1.credit_code", query.getCreditCode())
                    .like(StringUtils.isNotBlank(query.getCompanyName()), "t1.company_name", query.getCompanyName());
        }
        IPage<WaitAuditDTO> iPage = auditRecordService.getWaitAuditList(page, qw);
        for (WaitAuditDTO dto : iPage.getRecords()) {
            File logo = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, dto.getUserId()).eq(File::getFileType, 0));
            if(logo!=null){
                dto.setLogoUrl(logo.getFileUrl());
            }
            List<File> license = fileService.list(new QueryWrapper<File>().lambda().eq(File::getUserId, dto.getUserId()).eq(File::getFileType, 1));
            dto.setLicense(license);
        }
        return Result.ok(iPage);
    }


    /**
     * 保存、修改 【区分id即可】
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<AuditRecord> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        auditRecordService.saveOrUpdate(param.getData());
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
        auditRecordService.removeById(id);
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
        auditRecordService.removeByIds(param.getIds());
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
        AuditRecord entity = auditRecordService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<AuditRecord> entityList = auditRecordService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<AuditRecordQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<AuditRecord> page = new Page<AuditRecord>(param.getPage(), param.getRows());
        IPage iPage = auditRecordService.page(page, null);
        return Result.ok(iPage);
    }
}
