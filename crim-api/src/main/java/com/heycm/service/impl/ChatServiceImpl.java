package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.heycm.dto.ChatDTO;
import com.heycm.enums.CommEnum;
import com.heycm.model.*;
import com.heycm.mapper.ChatMapper;
import com.heycm.server.WebSocketServer;
import com.heycm.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

    @Autowired
    public IUserService userService;
    @Autowired
    public ICompanyService companyService;
    @Autowired
    public IStudentService studentService;
    @Autowired
    public IFileService fileService;

    @Resource
    ChatMapper chatMapper;

    @Autowired
    private WebSocketServer webSocketServer;

    @Override
    public ResponseMessage send(Chat chat) throws IOException {
        // 1.设置发送时间
        chat.setSendTime(new Date());
        // 2.获取dto
        ChatDTO chatDTO = getChatDTO(chat);
        // 11.发送消息
        ResponseMessage res = webSocketServer.sendOne(chatDTO);
        // 12.默认接受者在线，发送成功
        chat.setStatus(0);
        // 13.如果接受者不在线
        String notOnline = CommEnum.WS_NOT_ONLINE.getCode();
        if (res.getResCode().equals(notOnline)) {
            chat.setErrCode(Integer.valueOf(notOnline));
            chat.setErrMsg(CommEnum.WS_NOT_ONLINE.getMsg());
            chat.setStatus(1);
        }
        // 14.消息存盘
        save(chat);
        return res;
    }

    @Override
    public void sendNotReceived(Integer userId) throws IOException {
        // 查询未接收的消息
        List<Chat> list = list(new QueryWrapper<Chat>().lambda().eq(Chat::getToId, userId).eq(Chat::getStatus, 1));
        if (list == null || list.size() == 0) {
            return;
        }
        // 更新未发送消息状态为已发送
        for (Chat chat : list) {
            ChatDTO chatDTO = getChatDTO(chat);
            // 发送消息
            ResponseMessage res = webSocketServer.sendOne(chatDTO);
            chat.setStatus(0);
            // 13.发送过程如果接受者掉线了
            String notOnline = CommEnum.WS_NOT_ONLINE.getCode();
            if (res.getResCode().equals(notOnline)) {
                chat.setErrCode(Integer.valueOf(notOnline));
                chat.setErrMsg(CommEnum.WS_NOT_ONLINE.getMsg());
                chat.setStatus(1);
                break;
            }
        }
        updateBatchById(list);
    }


    private ChatDTO getChatDTO(Chat chat){
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(chat.getId());
        chatDTO.setType(chat.getType());
        chatDTO.setFromId(chat.getFromId());
        chatDTO.setToId(chat.getToId());
        chatDTO.setContent(chat.getContent());
        chatDTO.setSendTime(chat.getSendTime());
        // 3.找到发送者用户
        User from = userService.getById(chat.getFromId());
        if (from.getPid() != null) {
            chat.setFromId(from.getPid());
            chatDTO.setFromId(from.getPid());
        }
        // 4.发送者是学生
        if (from.getType().equals(1)) {
            Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, chat.getFromId()));
            chatDTO.setFromName(student.getStudentName());
        }
        // 5.发送者是企业
        if (from.getType().equals(2)) {
            Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId,chat.getFromId()));
            chatDTO.setFromName(company.getCompanyName());
        }
        // 6.获取发送者头像
        File fromPhoto = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, chat.getFromId()).eq(File::getFileType, 0));
        chatDTO.setFromPhotoUrl(fromPhoto.getFileUrl());
        // 7.找到发送者用户
        User to = userService.getById(chat.getToId());
        if (to.getPid() != null) {
            chat.setToId(to.getPid());
            chatDTO.setToId(to.getPid());
        }
        // 8.接收者是学生
        if (to.getType().equals(1)) {
            Student student = studentService.getOne(new QueryWrapper<Student>().lambda().eq(Student::getUserId, chat.getToId()));
            chatDTO.setToName(student.getStudentName());
        }
        // 9.接收者是企业
        if (to.getType().equals(2)) {
            Company company = companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getUserId,chat.getToId()));
            chatDTO.setToName(company.getCompanyName());
        }
        // 10.获取发送者头像
        File toPhoto = fileService.getOne(new QueryWrapper<File>().lambda().eq(File::getUserId, chat.getToId()).eq(File::getFileType, 0));
        chatDTO.setToPhotoUrl(toPhoto.getFileUrl());
        return chatDTO;
    }
}
