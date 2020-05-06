package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.FilePageDTO;
import com.heycm.model.File;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-29
 */
public interface IFileService extends IService<File> {

    IPage<FilePageDTO> schFilePage(Page<FilePageDTO> page, QueryWrapper<FilePageDTO> qw);
}
