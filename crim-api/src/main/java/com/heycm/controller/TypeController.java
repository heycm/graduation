package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.CampusDTO;
import com.heycm.dto.YearDTO;
import com.heycm.param.Param;
import com.heycm.service.ITypeService;
import com.heycm.model.Type;
import com.heycm.query.TypeQuery;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "07 - 列表控制器 Type")
@Transactional
@RestController
@RequestMapping("/api/v1/type")
public class TypeController {
    @Autowired
    public ITypeService typeService;

    @ApiOperation(value = "1 - 查看年级列表", notes = "查看年级列表")
    @ApiOperationSupport(order = 1)
    @RequiresRoles(logical = Logical.OR, value = {"school", "company"})
    @GetMapping("/year/list")
    public ResponseMessage getYearList() {
        ArrayList<YearDTO> list = new ArrayList<>();
        LambdaQueryWrapper<Type> eq = new QueryWrapper<Type>().lambda().eq(Type::getPid, 5).orderByDesc(Type::getTypeName);
        List<Type> typeList = typeService.list(eq);
        for (Type type : typeList) {
            YearDTO yearDTO = new YearDTO();
            yearDTO.setId(type.getId());
            yearDTO.setYearName(type.getTypeName());
            list.add(yearDTO);
        }
        return Result.ok(list);
    }

    @ApiOperation(value = "2 - 查看校区列表", notes = "查看校区列表")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"school", "company"})
    @GetMapping("/campus/list")
    public ResponseMessage getCampusList() {
        ArrayList<CampusDTO> list = new ArrayList<>();
        LambdaQueryWrapper<Type> eq = new QueryWrapper<Type>().lambda().eq(Type::getPid, 1).orderByDesc(Type::getTypeName);
        List<Type> typeList = typeService.list(eq);
        for (Type type : typeList) {
            CampusDTO campusDTO = new CampusDTO();
            campusDTO.setId(type.getId());
            campusDTO.setCampusName(type.getTypeName());
            list.add(campusDTO);
        }
        return Result.ok(list);
    }













    /**
     * 保存、修改 【区分id即可】
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<Type> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
            typeService.saveOrUpdate(param.getData());
        return Result.ok();
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
            typeService.removeById(id);
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
            typeService.removeByIds(param.getIds());
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
        Type entity = typeService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Type> entityList = typeService.list();
        return Result.ok(entityList);
    }


    /**
    * 分页查询数据：
    * @param param 查询对象
    * @return  ResponseMessage
    */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<TypeQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Type> page = new Page<Type>(param.getPage(), param.getRows());
        IPage iPage = typeService.page(page, null);
        return Result.ok(iPage);
    }
}
