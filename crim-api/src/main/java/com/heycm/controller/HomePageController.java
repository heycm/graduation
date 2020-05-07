package com.heycm.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.param.Param;
import com.heycm.service.IHomePageService;
import com.heycm.model.HomePage;
import com.heycm.query.HomePageQuery;
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
@Api(tags = "25 - 社交主页控制器 HomePage")
@Transactional
@RestController
@RequestMapping("/api/v1/homePage")
public class HomePageController {
    @Autowired
    public IHomePageService homePageService;

    @ApiOperation(value = "1 - 增加、更新社交主页", notes = "增加、更新社交主页")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("student")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody HomePage homePage) {
        if (homePage == null) {
            return Result.error("参数不能为空");
        }
        homePageService.saveOrUpdate(homePage);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 增加社交主页", notes = "增加社交主页")
    @ApiOperationSupport(order = 2)
    @RequiresRoles("student")
    @PostMapping("/add/{resumeId}")
    public ResponseMessage add(@PathVariable("resumeId") Integer resumeId, String homePage) {
        if (resumeId == null || StringUtils.isBlank(homePage)) {
            return Result.error("参数不能为空");
        }
        HomePage page = new HomePage();
        page.setResumeId(resumeId);
        page.setHomePageUrl(homePage);
        homePageService.saveOrUpdate(page);
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
        homePageService.removeById(id);
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
        homePageService.removeByIds(param.getIds());
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
        HomePage entity = homePageService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<HomePage> entityList = homePageService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<HomePageQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<HomePage> page = new Page<HomePage>(param.getPage(), param.getRows());
        IPage iPage = homePageService.page(page, null);
        return Result.ok(iPage);
    }
}
