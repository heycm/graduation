package com.heycm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.heycm.dto.ChatDTO;
import com.heycm.model.*;
import com.heycm.param.Param;
import com.heycm.service.*;
import com.heycm.query.ChatQuery;
import com.heycm.utils.SubjectUtil;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@RestController
@Api(tags = "28 - 聊天控制器 Chat")
@Slf4j
@Transactional
@RequestMapping("/api/v1/chat")
public class ChatController {
    @Autowired
    public IChatService chatService;
    @Autowired
    public IUserService userService;
    @Autowired
    public IFileService fileService;
    @Autowired
    public ICompanyService companyService;
    @Autowired
    public IStudentService studentService;

    @Autowired
    public SubjectUtil subjectUtil;


    @ApiOperation(value = "1 - 聊天", notes = "聊天")
    @ApiOperationSupport(order = 1)
    @RequiresRoles(logical = Logical.OR, value = {"company", "student", "school"})
    @PostMapping("/sendOne")
    public ResponseMessage sendOne(@RequestBody Chat chat) throws IOException {
        if (chat == null || chat.getType() == null || chat.getFromId() == null || chat.getToId() == null ||
                StringUtils.isBlank(chat.getContent())) {
            return Result.error("参数不能为空");
        }
        return chatService.send(chat);
    }

    @ApiOperation(value = "2 - 获取聊天对象信息", notes = "获取聊天对象信息")
    @ApiOperationSupport(order = 2)
    @RequiresRoles(logical = Logical.OR, value = {"student", "company"})
    @GetMapping("/obj/{userId}")
    public ResponseMessage getChatDTO(@PathVariable("userId") Long userId) {
        if (userId == null) {
            return Result.error("参数不能为空");
        }
        // 对方
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setFromId(user.getId());
        if (user.getType().equals(1)) {
            Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, user.getId()));
            chatDTO.setFromName(student.getStudentName());
        }
        if (user.getType().equals(2)) {
            Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, user.getId()));
            chatDTO.setFromName(company.getCompanyName());
        }
        File toFile = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, user.getId()).eq(File::getFileType, 0));
        chatDTO.setFromPhotoUrl(toFile.getFileUrl());
        // 我
        User me = userService.getById(subjectUtil.getCurrentUserId());
        Integer id = me.getId();
        if (me.getPid() != null) {
            id = me.getPid();
        }
        chatDTO.setToId(id);
        if (me.getType().equals(1)) {
            Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, id));
            chatDTO.setToName(student.getStudentName());
        }
        if (me.getType().equals(2)) {
            Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId, id));
            chatDTO.setToName(company.getCompanyName());
        }
        File fromFile = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, id).eq(File::getFileType, 0));
        chatDTO.setToPhotoUrl(fromFile.getFileUrl());
        return Result.ok(chatDTO);
    }


    /**
     * 保存、修改 【区分id即可】
     *
     * @param param param
     * @return ResponseMessage
     */
    @PostMapping("/save")
    public ResponseMessage save(@RequestBody Param<Chat> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        chatService.saveOrUpdate(param.getData());
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
        chatService.removeById(id);
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
        chatService.removeByIds(param.getIds());
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
        Chat entity = chatService.getById(id);
        return Result.ok(entity);
    }


    //查看所有的信息
    @GetMapping("/list")
    public ResponseMessage list() {
        List<Chat> entityList = chatService.list();
        return Result.ok(entityList);
    }


    /**
     * 分页查询数据：
     *
     * @param param 查询对象
     * @return ResponseMessage
     */
    @PostMapping("/pageList")
    public ResponseMessage pageList(@RequestBody Param<ChatQuery> param) {
        if (param == null) {
            return Result.error("1000", "参数为NUll");
        }
        Page<Chat> page = new Page<Chat>(param.getPage(), param.getRows());
        IPage iPage = chatService.page(page, null);
        return Result.ok(iPage);
    }
}
