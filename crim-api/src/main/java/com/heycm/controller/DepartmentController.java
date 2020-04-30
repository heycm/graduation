package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.TreeDTO;
import com.heycm.model.Profession;
import com.heycm.model.Type;
import com.heycm.param.Param;
import com.heycm.service.IDepartmentService;
import com.heycm.model.Department;
import com.heycm.query.DepartmentQuery;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "4 - 学院控制器 Department")
@Transactional
@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    @Autowired
    public IDepartmentService departmentService;


    @ApiOperation(value = "1 - 增加学院", notes = "增加学院")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody DepartmentQuery query) {
        if (query == null || StringUtils.isEmpty(query.getAddNodeName()) || query.getCampusId() == null) {
            return Result.error("参数为不能为空");
        }
        Department department = new Department();
        department.setCampusId(query.getCampusId());
        department.setDepartmentName(query.getAddNodeName());
        departmentService.save(department);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 删除学院", notes = "删除学院")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("school")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("参数为不能为空");
        }
        departmentService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "3 - 查看所有的学院", notes = "查看所有的学院")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Department> entityList = departmentService.list();
        return Result.ok(entityList);
    }






    /**
     * 保存、修改 【区分id即可】
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<Department> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        departmentService.saveOrUpdate(param.getData());
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
        departmentService.removeByIds(param.getIds());
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
        Department entity = departmentService.getById(id);
        return Result.ok(entity);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<DepartmentQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Department> page = new Page<Department>(param.getPage(), param.getRows());
        IPage iPage = departmentService.page(page, null);
        return Result.ok(iPage);
    }
}
