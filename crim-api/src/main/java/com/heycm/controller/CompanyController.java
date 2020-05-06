package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.CompanyInfoDTO;
import com.heycm.dto.JobVacancyDTO;
import com.heycm.model.File;
import com.heycm.model.JobVacancy;
import com.heycm.param.Param;
import com.heycm.service.ICompanyService;
import com.heycm.model.Company;
import com.heycm.query.CompanyQuery;
import com.heycm.service.IFileService;
import com.heycm.service.IJobVacancyService;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.date.DateUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.checkerframework.checker.units.qual.A;
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
@RestController
@Api(tags = "13 - 企业控制器 Company")
@Transactional
@RequestMapping("/api/v1/company")
public class CompanyController {
    @Autowired
    public ICompanyService companyService;
    @Autowired
    public IFileService fileService;
    @Autowired
    public IJobVacancyService jobVacancyService;
    @Autowired
    public SubjectUtil subjectUtil;


    @ApiOperation(value = "1 - 保存、修改", notes = "保存、修改")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("company")
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Company company) {
        if (company == null || StringUtils.isEmpty(company.getCompanyName()) || StringUtils.isEmpty(company.getLegalMan())
                || StringUtils.isEmpty(company.getRegisteredCapital()) || StringUtils.isEmpty(company.getBusinessStatus())
                || company.getRegisteredDate() == null || StringUtils.isEmpty(company.getCompanyType()) || StringUtils.isEmpty(company.getCreditCode())
                || StringUtils.isEmpty(company.getLinkMan()) || StringUtils.isEmpty(company.getLinkPhone())) {
            return Result.error("参数不能是空");
        }
        Integer currentUserId = subjectUtil.getCurrentUserId();
        company.setUserId(currentUserId);
        // company.setStatus(0);
        companyService.saveOrUpdate(company);
        return Result.ok(company);
    }

    @ApiOperation(value = "2 - 获取企业基本信息", notes = "获取企业基本信息")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"company", "school", "school-child", "student"})
    @GetMapping("/info")
    public ResponseMessage info() {
        Integer currentUserId = subjectUtil.getCurrentUserId();
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, currentUserId));
        CompanyInfoDTO dto = new CompanyInfoDTO();
        if (company != null) {
            dto.setId(company.getId());
            dto.setCompanyName(company.getCompanyName());
            dto.setLinkMan(company.getLinkMan());
            dto.setLinkPhone(company.getLinkPhone());
            dto.setLegalMan(company.getLegalMan());
            dto.setRegisteredCapital(company.getRegisteredCapital());
            dto.setRegisteredDate(company.getRegisteredDate());
            dto.setRegisteredDateString(DateUtil.getStringYMD(company.getRegisteredDate()));
            dto.setRegisteredPlace(company.getRegisteredPlace());
            dto.setCompanyType(company.getCompanyType());
            dto.setBusinessStatus(company.getBusinessStatus());
            dto.setBusinessScope(company.getBusinessScope());
            dto.setCompanyAddress(company.getCompanyAddress());
            dto.setCreditCode(company.getCreditCode());
            dto.setCompanyDesc(company.getCompanyDesc());
            dto.setStatus(company.getStatus());
        }
        File license = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, currentUserId)
                .eq(File::getFileType, 1));
        if (license != null) {
            dto.setLicenseUrl(license.getFileUrl());
        }
        File logo = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, currentUserId).eq(File::getFileType, 0));
        if (logo != null) {
            dto.setLogoUrl(logo.getFileUrl());
        }
        return Result.ok(dto);
    }

    @ApiOperation(value = "3 - 获取在招职位列表", notes = "获取在招职位列表")
    @ApiOperationSupport(order = 3)
    @RequiresRoles(logical = Logical.OR, value = {"company", "school", "school-child", "student"})
    @GetMapping("/position")
    public ResponseMessage positionList() {
        Integer currentUserId = subjectUtil.getCurrentUserId();
        Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, currentUserId));
        if (company == null) {
            return Result.error("该用户无企业信息");
        }
        ArrayList<JobVacancyDTO> jobVacancyDTOS = new ArrayList<>();
        List<JobVacancy> list = jobVacancyService.list(new QueryWrapper<JobVacancy>().lambda().eq(JobVacancy::getCompanyId, company.getId()));
        for (JobVacancy jobVacancy : list) {
            String[] split = jobVacancy.getSkill().split(",");
            JobVacancyDTO dto = new JobVacancyDTO();
            dto.setId(jobVacancy.getId());
            dto.setCompanyId(jobVacancy.getCompanyId());
            dto.setJobName(jobVacancy.getJobName());
            dto.setWorkPlace(jobVacancy.getWorkPlace());
            dto.setRegularPay(jobVacancy.getRegularPay());
            dto.setExperience(jobVacancy.getExperience());
            dto.setCertificate(jobVacancy.getCertificate());
            dto.setSkill(jobVacancy.getSkill());
            dto.setSkills(split);
            dto.setJobDesc(jobVacancy.getJobDesc());
            dto.setWelfare(jobVacancy.getWelfare());
            dto.setStatus(jobVacancy.getStatus());
            jobVacancyDTOS.add(dto);
        }
        return Result.ok(jobVacancyDTOS);
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
        companyService.removeById(id);
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
        companyService.removeByIds(param.getIds());
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
        Company entity = companyService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Company> entityList = companyService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<CompanyQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Company> page = new Page<Company>(param.getPage(), param.getRows());
        IPage iPage = companyService.page(page, null);
        return Result.ok(iPage);
    }
}
