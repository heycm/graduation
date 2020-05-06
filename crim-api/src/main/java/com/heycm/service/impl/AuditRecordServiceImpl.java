package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.AuidtJobFairDTO;
import com.heycm.model.AuditRecord;
import com.heycm.mapper.AuditRecordMapper;
import com.heycm.query.AuditRecordQuery;
import com.heycm.service.IAuditRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-04
 */
@Service
public class AuditRecordServiceImpl extends ServiceImpl<AuditRecordMapper, AuditRecord> implements IAuditRecordService {

    @Resource
    AuditRecordMapper auditRecordMapper;

    @Override
    public IPage<AuidtJobFairDTO> applyJobFairAuditRecords(Page<AuidtJobFairDTO> page, QueryWrapper<AuditRecordQuery> qw) {
        return auditRecordMapper.applyJobFairAuditRecords(page, qw);
    }
}
