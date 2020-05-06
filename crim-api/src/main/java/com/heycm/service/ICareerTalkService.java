package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.CareerTalkDTO;
import com.heycm.model.CareerTalk;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.query.CareerTalkQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface ICareerTalkService extends IService<CareerTalk> {

    IPage<CareerTalkDTO> pageList(Page<CareerTalkDTO> page, QueryWrapper<CareerTalkQuery> qw);
}
