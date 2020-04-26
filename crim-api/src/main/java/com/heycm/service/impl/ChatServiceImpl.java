package com.heycm.service.impl;

import com.heycm.model.Chat;
import com.heycm.mapper.ChatMapper;
import com.heycm.service.IChatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements IChatService {

}
