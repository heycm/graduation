package com.heycm.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.CareerTalkDTO;
import com.heycm.model.CareerTalk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heycm.query.CareerTalkQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface CareerTalkMapper extends BaseMapper<CareerTalk> {

    IPage<CareerTalkDTO> pageList(Page<CareerTalkDTO> page, @Param(Constants.WRAPPER) QueryWrapper<CareerTalkQuery> qw);
}
