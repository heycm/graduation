package com.heycm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.model.Department;
import com.heycm.param.Param;
import com.heycm.query.DepartmentQuery;
import com.heycm.service.IProfessionService;
import com.heycm.model.Profession;
import com.heycm.query.ProfessionQuery;
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

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Api(tags = "06 - 专业控制器 Profession")
@Transactional
@RestController
@RequestMapping("/api/v1/profession")
public class ProfessionController {
    @Autowired
    public IProfessionService professionService;



    @ApiOperation(value = "1 - 增加专业", notes = "增加专业")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody ProfessionQuery query) {
        if (query == null || StringUtils.isEmpty(query.getAddNodeName()) || query.getDeptId() == null) {
            return Result.error("参数为不能为空");
        }
        Profession profession = new Profession();
        profession.setDepartmentId(query.getDeptId());
        profession.setProfessionName(query.getAddNodeName());
        professionService.save(profession);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 删除专业", notes = "删除专业")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("school")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("参数为不能为空");
        }
        professionService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "3 - 查看所有的专业", notes = "查看所有的专业")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Profession> entityList = professionService.list();
        return Result.ok(entityList);
    }










    /**
     * 保存、修改 【区分id即可】
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<Profession> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
            professionService.saveOrUpdate(param.getData());
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
            professionService.removeByIds(param.getIds());
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
        Profession entity = professionService.getById(id);
        return Result.ok(entity);
    }





    /**
    * 分页查询数据：
    * @param param 查询对象
    * @return  ResponseMessage
    */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<ProfessionQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Profession> page = new Page<Profession>(param.getPage(), param.getRows());
        IPage iPage = professionService.page(page, null);
        return Result.ok(iPage);
    }
}
