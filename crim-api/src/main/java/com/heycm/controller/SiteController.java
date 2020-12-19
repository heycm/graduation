package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.SideDTO;
import com.heycm.param.Param;
import com.heycm.service.ISiteService;
import com.heycm.model.Site;
import com.heycm.query.SiteQuery;
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
@Api(tags = "10 - 场地控制器 Site")
@Transactional
@RestController
@RequestMapping("/api/v1/site")
public class SiteController {
    @Autowired
    public ISiteService siteService;

    @ApiOperation(value = "1 - 增加或更新", notes = "增加或更新")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Site site) {
        if (site == null || site.getBuildingId() == null || StringUtils.isEmpty(site.getSiteName())) {
            return Result.error("参数不能为空");
        }
        site.setStatus(0);
        siteService.saveOrUpdate(site);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 分页查询数据", notes = "分页查询数据")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("school")
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<SiteQuery> param) {
        if (param == null) {
            param = new Param<SiteQuery>();
        }
        // 1.设置默认当前页是第 1 页
        if (param.getPage() == null) {
            param.setPage(1);
        }
        // 2.设置默认每页 5 条数据
        if (param.getRows() == null) {
            param.setRows(5);
        }
        SiteQuery siteQuery = param.getData();
        QueryWrapper<SiteQuery> qw = new QueryWrapper<>();
        if (siteQuery != null) {
            qw.eq(siteQuery.getCampusId() != null, "t1.campus_id", siteQuery.getCampusId())
                    .eq(siteQuery.getBuildingId() != null, "t1.building_id", siteQuery.getBuildingId())
                    .like(StringUtils.isNotEmpty(siteQuery.getSiteName()), "t1.site_name", siteQuery.getSiteName())
                    .eq(siteQuery.getSiteStatus() != null, "t1.site_status", siteQuery.getSiteStatus());
        }
        Page<SideDTO> page = new Page<SideDTO>(param.getPage(), param.getRows());
        IPage<SideDTO> iPage = siteService.pageList(page, qw);
        return Result.ok(iPage);
    }

    @ApiOperation(value = "3 - 根据ID删除对象信息", notes = "根据ID删除对象信息")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("参数不能为");
        }
        siteService.removeById(id);
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
        siteService.removeByIds(param.getIds());
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
        Site entity = siteService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Site> entityList = siteService.list();
        return Result.ok(entityList);
    }


}
