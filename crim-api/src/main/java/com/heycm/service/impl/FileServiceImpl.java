package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.FilePageDTO;
import com.heycm.model.File;
import com.heycm.mapper.FileMapper;
import com.heycm.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-29
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Resource
    FileMapper fileMapper;

    @Override
    public IPage<FilePageDTO> schFilePage(Page<FilePageDTO> page, QueryWrapper<FilePageDTO> qw) {
        return fileMapper.schFilePage(page, qw);
    }
}
