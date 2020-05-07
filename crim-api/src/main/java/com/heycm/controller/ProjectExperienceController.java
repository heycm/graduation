package com.heycm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.param.Param;
import com.heycm.service.IProjectExperienceService;
import com.heycm.model.ProjectExperience;
import com.heycm.query.ProjectExperienceQuery;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "22 - 项目经历控制器 ProjectExperience")
@Transactional
@RestController
@RequestMapping("/api/v1/projectExperience")
public class ProjectExperienceController {
    @Autowired
    public IProjectExperienceService projectExperienceService;

    @ApiOperation(value = "1 - 增加、更新项目经历", notes = "增加、更新项目经历")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("student")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody ProjectExperience projectExperience) {
        if (projectExperience == null) {
            return Result.error("参数不能为空");
        }
        projectExperienceService.saveOrUpdate(projectExperience);
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
        projectExperienceService.removeById(id);
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
        projectExperienceService.removeByIds(param.getIds());
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
        ProjectExperience entity = projectExperienceService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<ProjectExperience> entityList = projectExperienceService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<ProjectExperienceQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<ProjectExperience> page = new Page<ProjectExperience>(param.getPage(), param.getRows());
        IPage iPage = projectExperienceService.page(page, null);
        return Result.ok(iPage);
    }
}
