package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.JobFairPageDTO;
import com.heycm.model.File;
import com.heycm.model.Mount;
import com.heycm.param.Param;
import com.heycm.service.IFileService;
import com.heycm.service.IJobFairService;
import com.heycm.model.JobFair;
import com.heycm.query.JobFairQuery;
import com.heycm.service.IMountService;
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
@Api(tags = "11 - 招聘会控制器 JobFair")
@Transactional
@RestController
@RequestMapping("/api/v1/jobFair")
public class JobFairController {
    @Autowired
    public IJobFairService jobFairService;
    @Autowired
    public IMountService mountService;
    @Autowired
    public IFileService fileService;


    @ApiOperation(value = "1 - 编辑保存招聘会", notes = "编辑保存招聘会")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody JobFair jobFair) {
        if (jobFair == null || jobFair.getYearLevelId() == null || jobFair.getQuarter() == null ||
                StringUtils.isEmpty(jobFair.getJobFairTitle()) || StringUtils.isEmpty(jobFair.getJobFairContent()) ||
                jobFair.getStartTime() == null || jobFair.getEndTime() == null) {
            return Result.error("参数不能为空");
        }
        jobFair.setStatus(0);
        jobFairService.saveOrUpdate(jobFair);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 编辑发布招聘会", notes = "编辑发布招聘会")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("school")
    @PostMapping("/publish")
    public ResponseMessage publish(@RequestBody JobFair jobFair) {
        if (jobFair == null || jobFair.getYearLevelId() == null || jobFair.getQuarter() == null ||
                StringUtils.isEmpty(jobFair.getJobFairTitle()) || StringUtils.isEmpty(jobFair.getJobFairContent()) ||
                jobFair.getStartTime() == null || jobFair.getEndTime() == null) {
            return Result.error("参数不能为空");
        }
        jobFair.setStatus(1);
        jobFairService.saveOrUpdate(jobFair);
        return Result.ok();
    }

    @ApiOperation(value = "3 - 分页查询招聘会", notes = "分页查询招聘会")
    @ApiOperationSupport(order = 3)
    @RequiresRoles(logical = Logical.OR, value = {"school", "company"})
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<JobFairQuery> param) {
        if (param == null) {
            param = new Param<JobFairQuery>();
        }
        // 1.设置默认当前页是第 1 页
        if (param.getPage() == null) {
            param.setPage(1);
        }
        // 2.设置默认每页 5 条数据
        if (param.getRows() == null) {
            param.setRows(5);
        }
        JobFairQuery paramData = param.getData();
        QueryWrapper<JobFairQuery> qw = new QueryWrapper<>();
        if (paramData != null) {
            qw.eq(paramData.getYearId() != null, "t1.year_id", paramData.getYearId())
                    .eq(paramData.getQuarter() != null, "t1.quarter", paramData.getQuarter())
                    .like(StringUtils.isNotEmpty(paramData.getTitle()), "t1.title", paramData.getTitle());
        }
        Page<JobFair> page = new Page<JobFair>(param.getPage(), param.getRows());
        IPage<JobFairPageDTO> iPage = jobFairService.pageList(page, qw);
        List<JobFairPageDTO> records = iPage.getRecords();
        for (JobFairPageDTO record : records) {
            record.setStartTimeString(DateUtil.dateToStr(record.getStartTime()));
            record.setEndTimeString(DateUtil.dateToStr(record.getEndTime()));
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
            ArrayList<File> files = new ArrayList<>();
            List<Mount> mountList = mountService.list(new QueryWrapper<Mount>().lambda().eq(Mount::getTargetId, record.getId()).eq(Mount::getTargetType, 0));
            for (Mount mount : mountList) {
                File file = fileService.getById(mount.getFileId());
                files.add(file);
            }
            record.setFiles(files);
        }
        return Result.ok(iPage);
    }

    @ApiOperation(value = "4 - 查询可申请入会列表", notes = "查询可申请入会列表")
    @ApiOperationSupport(order = 4)
    @RequiresRoles(logical = Logical.OR, value = {"school", "company"})
    @GetMapping("/applyList")
    public ResponseMessage applyList() {
        List<JobFairPageDTO> list = jobFairService.applyList();
        for (JobFairPageDTO dto : list) {
            dto.setStartTimeString(DateUtil.getStringYMD(dto.getStartTime()));
            dto.setEndTimeString(DateUtil.getStringYMD(dto.getEndTime()));

            long now = System.currentTimeMillis();
            long start = dto.getStartTime().getTime();
            long end = dto.getEndTime().getTime();
            if (now < start) {
                dto.setProgress("未开始");
            } else if (now > end) {
                dto.setProgress("已结束");
            } else {
                dto.setProgress("进行中");
            }
            ArrayList<File> files = new ArrayList<>();
            List<Mount> mountList = mountService.list(new QueryWrapper<Mount>().lambda().eq(Mount::getTargetId, dto.getId()).eq(Mount::getTargetType, 0));
            for (Mount mount : mountList) {
                File file = fileService.getById(mount.getFileId());
                files.add(file);
            }
            dto.setFiles(files);
        }
        return Result.ok(list);
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
        jobFairService.removeById(id);
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
        jobFairService.removeByIds(param.getIds());
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
        JobFair entity = jobFairService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<JobFair> entityList = jobFairService.list();
        return Result.ok(entityList);
    }


}
