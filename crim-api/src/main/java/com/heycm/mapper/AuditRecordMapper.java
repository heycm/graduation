package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.AuidtJobFairDTO;
import com.heycm.dto.WaitAuditDTO;
import com.heycm.model.AuditRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heycm.query.AuditRecordQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-04
 */
public interface AuditRecordMapper extends BaseMapper<AuditRecord> {

    IPage<AuidtJobFairDTO> applyJobFairAuditRecords(Page<AuidtJobFairDTO> page, @Param(Constants.WRAPPER) QueryWrapper<AuditRecordQuery> qw);

    IPage<WaitAuditDTO> getWaitAuditList(IPage<WaitAuditDTO> page, @Param(Constants.WRAPPER) QueryWrapper<AuditRecordQuery> qw);
}
