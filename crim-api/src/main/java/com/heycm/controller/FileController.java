package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.FilePageDTO;
import com.heycm.model.User;
import com.heycm.param.Param;
import com.heycm.service.IFileService;
import com.heycm.model.File;
import com.heycm.query.FileQuery;
import com.heycm.service.IUserService;
import com.heycm.utils.OSSUtil;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author heycm@qq.com
 * @since 2020-04-29
 */
@Api(tags = "05 - 文件控制器 File")
@Transactional
@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    @Autowired
    public IFileService fileService;
    @Autowired
    public IUserService userService;
    @Autowired
    OSSUtil ossUtil;
    @Autowired
    SubjectUtil subjectUtil;


    @ApiOperation(value = "1 - 上传Logo/头像", notes = "上传Logo/头像")
    @ApiOperationSupport(order = 1)
    @RequiresRoles(logical = Logical.OR, value = {"school", "company"})
    @PostMapping("/logo")
    public ResponseMessage uploadLogo(MultipartFile file) throws IOException {
        if (file == null) {
            Result.error("上传文件为空");
        }
        // 1.获取文件名和后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("." + suffix));
        // 2.生成UUID，并加后缀名
        String uuid = UUID.randomUUID().toString() + "." + suffix;
        // 3.获取文件大小
        long size = file.getSize() / 1024;
        // 4.创建一个新文件，设置UUID、名称、后缀、大小、类型（0代表logo或头像）
        File newFile = new File();
        newFile.setFileName(fileName);
        newFile.setFileUuid(uuid);
        newFile.setFileSuffix(suffix);
        newFile.setFileSize((int) size);
        newFile.setFileType(0);
        // 5.获取当前用户id，并设置到新文件
        Integer userId = subjectUtil.getCurrentUserId();
        newFile.setUserId(userId);
        // 6.查看当前用户是否已经有logo文件
        LambdaQueryWrapper<File> eq = new QueryWrapper<File>().lambda().eq(File::getUserId, userId).eq(File::getFileType, 0);
        File one = fileService.getOne(eq);
        if (one != null) {
            // 7.如果已经有logo文件，将文件id设置给新文件
            newFile.setId(one.getId());
            // 8.去oss查找是否存在该文件，若存在则删除
            boolean b = ossUtil.foundImg(one.getFileUuid());
            if (b) {
                ossUtil.deleteImg(one.getFileUuid());
            }
        }
        // 9.上传图片到oss，获得url，写入新文件
        String url = ossUtil.uploadImg2OSS(file.getInputStream(), uuid);
        newFile.setFileUrl(url);
        // 10.存盘
        fileService.saveOrUpdate(newFile);
        return Result.ok(newFile);
    }

    @ApiOperation(value = "2 - 多文件上传", notes = "多文件上传")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"school", "student", "company"})
    @PostMapping("/upload")
    public ResponseMessage uploadFile(MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            Result.error("上传文件为空");
        }
        ArrayList<File> list = new ArrayList<>();
        Integer userId = subjectUtil.getCurrentUserId();
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("." + suffix));
            String uuid = UUID.randomUUID().toString() + "." + suffix;
            long size = file.getSize() / 1024;
            File newFile = new File();
            newFile.setFileName(fileName);
            newFile.setFileUuid(uuid);
            newFile.setFileSuffix(suffix);
            newFile.setFileSize((int) size);
            newFile.setFileType(2);
            newFile.setStatus(1);
            newFile.setUserId(userId);
            // 上传oss
            String url = ossUtil.uploadFile2OSS(file.getInputStream(), uuid);
            newFile.setFileUrl(url);
            list.add(newFile);
        }
        fileService.saveBatch(list);
        return Result.ok();
    }

    @ApiOperation(value = "3 - 学校查询附件列标", notes = "学校查询附件列标")
    @ApiOperationSupport(order = 3)
    @RequiresRoles("school")
    @PostMapping("/sch/page")
    public ResponseMessage schFilePage(@RequestBody Param<FileQuery> param) {
        if (param == null) {
            param = new Param<FileQuery>();
        }
        if (param.getPage() == null) {
            param.setPage(1);
        }
        if (param.getRows() == null) {
            param.setRows(5);
        }
        QueryWrapper<FilePageDTO> qw = new QueryWrapper<>();
        qw.eq("t1.user_id", subjectUtil.getCurrentUserId());
        FileQuery data = param.getData();
        if (data != null) {
            qw.eq(data.getStatus() != null, "t1.status", data.getStatus())
                    .like(StringUtils.isNotEmpty(data.getFileName()), "t1.file_name", data.getFileName());
        }
        Page<FilePageDTO> page = new Page<>(param.getPage(), param.getRows());
        IPage<FilePageDTO> iPage = fileService.schFilePage(page, qw);
        return Result.ok(iPage);
    }

    @ApiOperation(value = "4 - 根据IDs批量删除", notes = "根据IDs批量删除")
    @ApiOperationSupport(order = 4)
    @RequiresRoles(logical = Logical.OR, value = {"school", "company"})
    @PostMapping("/batchDel")
    public ResponseMessage delete(@RequestBody Param param) {
        if (param == null || param.getIds() == null || param.getIds().size() == 0) {
            return Result.error("参数不能为空");
        }
        fileService.removeByIds(param.getIds());
        return Result.ok();
    }

    @ApiOperation(value = "5 - 上传营业执照", notes = "上传营业执照")
    @ApiOperationSupport(order = 5)
    @RequiresRoles("company")
    @PostMapping("/license")
    public ResponseMessage uploadLicense(MultipartFile file) throws IOException {
        if (file == null) {
            Result.error("上传文件为空");
        }
        // 1.获取文件名和后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("." + suffix));
        // 2.生成UUID，并加后缀名
        String uuid = UUID.randomUUID().toString() + "." + suffix;
        // 3.获取文件大小
        long size = file.getSize() / 1024;
        // 4.创建一个新文件，设置UUID、名称、后缀、大小、类型（0代表logo或头像）
        File newFile = new File();
        newFile.setFileName(fileName);
        newFile.setFileUuid(uuid);
        newFile.setFileSuffix(suffix);
        newFile.setFileSize((int) size);
        newFile.setFileType(1);
        // 5.获取当前用户id，并设置到新文件
        Integer userId = subjectUtil.getCurrentUserId();
        newFile.setUserId(userId);
        LambdaQueryWrapper<File> eq = new QueryWrapper<File>().lambda().eq(File::getUserId, userId).eq(File::getFileType, 1);
        File one = fileService.getOne(eq);
        if (one != null) {
            newFile.setId(one.getId());
            boolean b = ossUtil.foundImg(one.getFileUuid());
            if (b) {
                ossUtil.deleteImg(one.getFileUuid());
            }
        }
        String url = ossUtil.uploadImg2OSS(file.getInputStream(), uuid);
        newFile.setFileUrl(url);
        fileService.saveOrUpdate(newFile);
        return Result.ok(newFile);
    }

    @ApiOperation(value = "6 - 学生上传简历证件照", notes = "学生上传简历证件照")
    @ApiOperationSupport(order = 6)
    @RequiresRoles("student")
    @PostMapping("/idPhoto")
    public ResponseMessage uploadIdPhoto(MultipartFile file) throws IOException {
        if (file == null) {
            Result.error("上传文件为空");
        }
        // 1.获取文件名和后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("." + suffix));
        // 2.生成UUID，并加后缀名
        String uuid = UUID.randomUUID().toString() + "." + suffix;
        // 3.获取文件大小
        long size = file.getSize() / 1024;
        // 4.创建一个新文件，设置UUID、名称、后缀、大小、类型（0代表logo或头像）
        File newFile = new File();
        newFile.setFileName(fileName);
        newFile.setFileUuid(uuid);
        newFile.setFileSuffix(suffix);
        newFile.setFileSize((int) size);
        newFile.setFileType(3);
        // 5.获取学生主账户id，并设置到新文件
        User user = userService.getById(subjectUtil.getCurrentUserId());
        if (user.getPid() != null){
            user = userService.getById(user.getPid());
        }
        newFile.setUserId(user.getId());
        // 6.查看当前用户是否已经有证件照
        LambdaQueryWrapper<File> eq = new QueryWrapper<File>().lambda().eq(File::getUserId, user.getId()).eq(File::getFileType, 3);
        File one = fileService.getOne(eq);
        if (one != null) {
            // 7.如果已经有logo文件，将文件id设置给新文件
            newFile.setId(one.getId());
            // 8.去oss查找是否存在该文件，若存在则删除
            boolean b = ossUtil.foundImg(one.getFileUuid());
            if (b) {
                ossUtil.deleteImg(one.getFileUuid());
            }
        }
        // 9.上传图片到oss，获得url，写入新文件
        String url = ossUtil.uploadImg2OSS(file.getInputStream(), uuid);
        newFile.setFileUrl(url);
        // 10.存盘
        fileService.saveOrUpdate(newFile);
        return Result.ok(newFile);
    }

    @ApiOperation(value = "7 - 学生上传头像", notes = "上传头像")
    @ApiOperationSupport(order = 7)
    @RequiresRoles("student")
    @PostMapping("/stu/logo")
    public ResponseMessage stuUploadLogo(MultipartFile file) throws IOException {
        if (file == null) {
            Result.error("上传文件为空");
        }
        // 1.获取文件名和后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("." + suffix));
        // 2.生成UUID，并加后缀名
        String uuid = UUID.randomUUID().toString() + "." + suffix;
        // 3.获取文件大小
        long size = file.getSize() / 1024;
        // 4.创建一个新文件，设置UUID、名称、后缀、大小、类型（0代表logo或头像）
        File newFile = new File();
        newFile.setFileName(fileName);
        newFile.setFileUuid(uuid);
        newFile.setFileSuffix(suffix);
        newFile.setFileSize((int) size);
        newFile.setFileType(0);
        // 5.获取当前用户id，并设置到新文件
        User user = userService.getById(subjectUtil.getCurrentUserId());
        if (user.getPid() != null){
            user = userService.getById(user.getPid());
        }
        newFile.setUserId(user.getId());
        // 6.查看当前用户是否已经有logo文件
        LambdaQueryWrapper<File> eq = new QueryWrapper<File>().lambda().eq(File::getUserId, user.getId()).eq(File::getFileType, 0);
        File one = fileService.getOne(eq);
        if (one != null) {
            // 7.如果已经有logo文件，将文件id设置给新文件
            newFile.setId(one.getId());
            // 8.去oss查找是否存在该文件，若存在则删除
            boolean b = ossUtil.foundImg(one.getFileUuid());
            if (b) {
                ossUtil.deleteImg(one.getFileUuid());
            }
        }
        // 9.上传图片到oss，获得url，写入新文件
        String url = ossUtil.uploadImg2OSS(file.getInputStream(), uuid);
        newFile.setFileUrl(url);
        // 10.存盘
        fileService.saveOrUpdate(newFile);
        return Result.ok(newFile);
    }









    /**
     * 保存、修改 【区分id即可】
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<File> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        fileService.saveOrUpdate(param.getData());
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
        fileService.removeById(id);
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
        File entity = fileService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<File> entityList = fileService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<FileQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<File> page = new Page<File>(param.getPage(), param.getRows());
        IPage iPage = fileService.page(page, null);
        return Result.ok(iPage);
    }
}
