package com.heycm.service.impl;

import com.heycm.model.Role;
import com.heycm.mapper.RoleMapper;
import com.heycm.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
