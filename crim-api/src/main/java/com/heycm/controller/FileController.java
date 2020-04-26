package com.heycm.controller;

import com.heycm.param.Param;
import com.heycm.service.IFileService;
import com.heycm.model.File;
import com.heycm.query.FileQuery;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@RestController
@RequestMapping("/api/v1/file")
public class FileController {
    @Autowired
    public IFileService fileService;

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
