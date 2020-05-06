package com.heycm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.param.Param;
import com.heycm.service.IBuildingService;
import com.heycm.model.Building;
import com.heycm.query.BuildingQuery;
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
 * @since 2020-04-30
 */
@Slf4j
@Api(tags = "09 - 建筑控制器 Building")
@Transactional
@RestController
@RequestMapping("/api/v1/building")
public class BuildingController {
    @Autowired
    public IBuildingService buildingService;

    @ApiOperation(value = "1 - 增加或更新", notes = "增加或更新")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Building building) {
        if (building == null || building.getCampusId() == null || StringUtils.isEmpty(building.getBuildingName())) {
            return Result.error("参数不能为空");
        }
        buildingService.saveOrUpdate(building);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 查看所有的信息", notes = "查看所有的信息")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("school")
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Building> entityList = buildingService.list();
        return Result.ok(entityList);
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
        buildingService.removeById(id);
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
        buildingService.removeByIds(param.getIds());
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
        Building entity = buildingService.getById(id);
        return Result.ok(entity);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<BuildingQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Building> page = new Page<Building>(param.getPage(), param.getRows());
        IPage iPage = buildingService.page(page, null);
        return Result.ok(iPage);
    }
}
