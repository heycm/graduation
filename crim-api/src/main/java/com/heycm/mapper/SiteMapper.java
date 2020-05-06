package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.SideDTO;
import com.heycm.model.Site;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heycm.query.SiteQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-30
 */
public interface SiteMapper extends BaseMapper<Site> {

    IPage<SideDTO> pageList(Page<SideDTO> page, @Param(Constants.WRAPPER) QueryWrapper<SiteQuery> qw);

}
