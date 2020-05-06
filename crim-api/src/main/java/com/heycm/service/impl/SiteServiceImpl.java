package com.heycm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.SideDTO;
import com.heycm.model.Site;
import com.heycm.mapper.SiteMapper;
import com.heycm.query.SiteQuery;
import com.heycm.service.ISiteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-30
 */
@Service
public class SiteServiceImpl extends ServiceImpl<SiteMapper, Site> implements ISiteService {

    @Resource
    SiteMapper siteMapper;

    @Override
    public IPage<SideDTO> pageList(Page<SideDTO> page, QueryWrapper<SiteQuery> qw) {
        return siteMapper.pageList(page, qw);
    }
}
