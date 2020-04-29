package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.SchoolInfoDTO;
import com.heycm.model.File;
import com.heycm.param.Param;
import com.heycm.service.IFileService;
import com.heycm.service.ISchoolService;
import com.heycm.model.School;
import com.heycm.query.SchoolQuery;
import com.heycm.utils.JwtUtil;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-28
 */
@Api(tags = "3 - 学校控制器 School")
@Transactional
@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {
    @Autowired
    public ISchoolService schoolService;
    @Autowired
    public IFileService fileService;
    @Autowired
    SubjectUtil subjectUtil;
    @Autowired
    JwtUtil jwtUtil;

    /**
     * 保存、修改 【区分id即可】
     *
     * @param school school
     * @return ResponseMessage
     */
    @ApiOperation(value = "1 - 增加或更新", notes = "增加或更新")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody School school) {
        if (school == null) {
            return Result.error("参数为NUll");
        }
        schoolService.saveOrUpdate(school);
        return Result.ok();
    }

    @ApiOperation(value = "2 - 获取学校信息", notes = "获取学校信息")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"school", "school-child"})
    @GetMapping("/info")
    public ResponseMessage getSchoolInfo() {
        SchoolInfoDTO schoolInfoDTO = new SchoolInfoDTO();
        School school = schoolService.getById(1);
        Integer userId = subjectUtil.getCurrentUserId();
        LambdaQueryWrapper<File> eq = new QueryWrapper<File>().lambda().eq(File::getUserId, userId).eq(File::getFileType, 0);
        File one = fileService.getOne(eq);
        if (one != null){
            schoolInfoDTO.setLogoUrl(one.getFileUrl());
        }
        schoolInfoDTO.setSchoolAddress(school.getSchoolAddress());
        schoolInfoDTO.setSchoolDesc(school.getSchoolDesc());
        schoolInfoDTO.setSchoolName(school.getSchoolName());
        schoolInfoDTO.setSchoolPostalCode(school.getSchoolPostalCode());
        return Result.ok(schoolInfoDTO);
    }

    @ApiOperation(value = "3 - 更新学校信息", notes = "更新学校信息")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @PostMapping("/info")
    public ResponseMessage updateSchoolInfo(@RequestBody School school) {
        if (school == null || StringUtils.isEmpty(school.getSchoolName())
                || StringUtils.isEmpty(school.getSchoolAddress()) || StringUtils.isEmpty(school.getSchoolPostalCode())) {
            return Result.error("参数不能为空");
        }
        school.setId(1);
        schoolService.saveOrUpdate(school);
        return Result.ok();
    }


    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable("id") Long id) {
        if (id == null) {
            return Result.error("1000", "参数为NUll");
        }
        schoolService.removeById(id);
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
        schoolService.removeByIds(param.getIds());
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
        School entity = schoolService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<School> entityList = schoolService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<SchoolQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<School> page = new Page<School>(param.getPage(), param.getRows());
        IPage iPage = schoolService.page(page, null);
        return Result.ok(iPage);
    }
}
