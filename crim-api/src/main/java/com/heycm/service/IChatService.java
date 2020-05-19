package com.heycm.service;

import com.heycm.dto.ChatDTO;
import com.heycm.model.Chat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.utils.response.ResponseMessage;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface IChatService extends IService<Chat> {

    ResponseMessage send(Chat chat) throws IOException;

    void sendNotReceived(Integer userId) throws IOException;
}
