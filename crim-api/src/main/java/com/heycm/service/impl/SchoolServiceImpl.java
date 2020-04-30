package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.SchoolCodeDTO;
import com.heycm.model.School;
import com.heycm.mapper.SchoolMapper;
import com.heycm.service.ISchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heycm.utils.response.ResponseMessage;
import com.heycm.utils.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-28
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

    @Resource
    SchoolMapper schoolMapper;

    @Override
    public ResponseMessage getCode() {

        List<SchoolCodeDTO> list = schoolMapper.getCode();

        return Result.ok(list);
    }

    @Override
    public IPage<SchoolCodeDTO> getCodeByQW(Page<SchoolCodeDTO> page, QueryWrapper<SchoolCodeDTO> qw) {
        return schoolMapper.getCodeByQW(page, qw);
    }
}
