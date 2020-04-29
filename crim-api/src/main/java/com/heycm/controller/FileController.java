package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.param.Param;
import com.heycm.service.IFileService;
import com.heycm.model.File;
import com.heycm.query.FileQuery;
import com.heycm.utils.OSSUtil;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author heycm@qq.com
 * @since 2020-04-29
 */
@Api(tags = "4 - 文件控制器 File")
@Transactional
@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    @Autowired
    public IFileService fileService;
    @Autowired
    OSSUtil ossUtil;
    @Autowired
    SubjectUtil subjectUtil;


    @ApiOperation(value = "学校上传Logo", notes = "学校上传Logo")
    @ApiOperationSupport(order = 1)
    @RequiresRoles("school")
    @PostMapping("/sch/logo")
    public ResponseMessage schUploadLogo(MultipartFile file) throws IOException {
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
        long size = file.getSize();
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
        if (one != null){
            // 7.如果已经有logo文件，将文件id设置给新文件
            newFile.setId(one.getId());
            // 8.去oss查找是否存在该文件，若存在则删除
            boolean b = ossUtil.foundImg(one.getFileUuid());
            if (b){
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
     * 根据IDs批量删除
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/batchDel")
    public ResponseMessage delete(@RequestBody Param param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
            fileService.removeByIds(param.getIds());
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
    * @param param 查询对象
    * @return  ResponseMessage
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
