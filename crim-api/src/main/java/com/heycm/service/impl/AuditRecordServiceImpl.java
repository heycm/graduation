package com.heycm.service.impl;

import com.heycm.model.AuditRecord;
import com.heycm.mapper.AuditRecordMapper;
import com.heycm.service.IAuditRecordService;
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
public class AuditRecordServiceImpl extends ServiceImpl<AuditRecordMapper, AuditRecord> implements IAuditRecordService {

}
