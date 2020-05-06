package com.heycm.controller;

import com.alibaba.fastjson.JSON;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.SchoolCodeDTO;
import com.heycm.param.Param;
import com.heycm.service.IGradeClassService;
import com.heycm.model.GradeClass;
import com.heycm.query.GradeClassQuery;
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

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Slf4j
@Api(tags = "08 - 班级控制器 GradeClass")
@Transactional
@RestController
@RequestMapping("/api/v1/gradeClass")
public class GradeClassController {
    @Autowired
    public IGradeClassService gradeClassService;

    @ApiOperation(value = "1 - 增加或更新", notes = "增加或更新")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody GradeClass gradeClass) {
        long l = System.currentTimeMillis();
        if (gradeClass == null) {
            return Result.error("参数不能为空");
        }
        if (gradeClass.getProfessionId() == null) {
            return Result.error("请选择专业");
        }
        if (gradeClass.getYearLevelId() == null) {
            return Result.error("请选择年级");
        }
        if (StringUtils.isEmpty(gradeClass.getGradeClassName())) {
            return Result.error("请输入班级名称");
        }
        log.info("[增加班级][入参:{}][结束]", JSON.toJSONString(gradeClass));
        gradeClassService.saveOrUpdate(gradeClass);
        log.info("[增加班级][班级信息:{}][耗时:{}ms][结束]", JSON.toJSONString(gradeClass), (System.currentTimeMillis() - l));
        return Result.ok();
    }

    @ApiOperation(value = "2 - 查看所有的信息", notes = "查看所有的信息")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("school")
    @GetMapping("/list")
    public ResponseMessage list() {
        List<GradeClass> entityList = gradeClassService.list();
        return Result.ok(entityList);
    }

    /**
     * 根据ID删除对象信息
     * @param id 对象id
     * @return ResponseMessage
     */
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("1000", "参数为NUll");
        }
            gradeClassService.removeById(id);
        return Result.ok();
    }

    /**
     * 根据IDs批量删除
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/batchDel")
    public ResponseMessage delete(@RequestBody Param param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
            gradeClassService.removeByIds(param.getIds());
        return Result.ok();
    }

    /**
     * 根据ID获取对象信息
     * @param id 对象id
     * @return ResponseMessage
     */
    @GetMapping("/{id}")
    public ResponseMessage get(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("1000", "参数为NUll");
        }
        GradeClass entity = gradeClassService.getById(id);
        return Result.ok(entity);
    }





    /**
    * 分页查询数据：
    * @param param 查询对象
    * @return  ResponseMessage
    */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<GradeClassQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<GradeClass> page = new Page<GradeClass>(param.getPage(), param.getRows());
        IPage iPage = gradeClassService.page(page, null);
        return Result.ok(iPage);
    }
}
