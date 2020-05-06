package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.SideDTO;
import com.heycm.model.Site;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.query.SiteQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-30
 */
public interface ISiteService extends IService<Site> {

    IPage<SideDTO> pageList(Page<SideDTO> page, QueryWrapper<SiteQuery> qw);
}
