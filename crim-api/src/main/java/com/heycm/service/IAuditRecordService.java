package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.AuidtJobFairDTO;
import com.heycm.model.AuditRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.query.AuditRecordQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-04
 */
public interface IAuditRecordService extends IService<AuditRecord> {

    IPage<AuidtJobFairDTO> applyJobFairAuditRecords(Page<AuidtJobFairDTO> page, QueryWrapper<AuditRecordQuery> qw);
}
