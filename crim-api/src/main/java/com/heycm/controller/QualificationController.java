package com.heycm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.QualificationDTO;
import com.heycm.param.Param;
import com.heycm.service.IQualificationService;
import com.heycm.model.Qualification;
import com.heycm.query.QualificationQuery;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
@Api(tags = "24 - 资格证书控制器 Qualification")
@Transactional
@RestController
@RequestMapping("/api/v1/qualification")
public class QualificationController {
    @Autowired
    public IQualificationService qualificationService;

    @ApiOperation(value = "1 - 增加、更新资格证书", notes = "增加、更新资格证书")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("student")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Qualification qualification) {
        if (qualification == null) {
            return Result.error("参数不能为空");
        }
        qualificationService.saveOrUpdate(qualification);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 增加资格证书", notes = "增加资格证书")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("student")
    @PostMapping("/add/{resumeId}")
    public ResponseMessage add(@PathVariable("resumeId") Integer resumeId, String qualification) {
        if (StringUtils.isBlank(qualification) || resumeId == null) {
            return Result.error("参数不能为空");
        }
        ArrayList<Qualification> qualifications = new ArrayList<>();
        String[] split = qualification.split(",");
        for (String s : split) {
            Qualification q = new Qualification();
            q.setResumeId(resumeId);
            q.setQualificationName(s);
            qualifications.add(q);
        }
        qualificationService.saveBatch(qualifications);
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
        qualificationService.removeById(id);
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
        qualificationService.removeByIds(param.getIds());
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
        Qualification entity = qualificationService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Qualification> entityList = qualificationService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<QualificationQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Qualification> page = new Page<Qualification>(param.getPage(), param.getRows());
        IPage iPage = qualificationService.page(page, null);
        return Result.ok(iPage);
    }
}
