package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.FilePageDTO;
import com.heycm.model.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-29
 */
public interface FileMapper extends BaseMapper<File> {

    IPage<FilePageDTO> schFilePage(Page<FilePageDTO> page,  @Param(Constants.WRAPPER) QueryWrapper<FilePageDTO> qw);

}
