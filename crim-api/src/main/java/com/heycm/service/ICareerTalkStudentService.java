package com.heycm.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heycm.dto.CareerTalkStudentDTO;
import com.heycm.model.CareerTalkStudent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heycm.query.CareerTalkStudentQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface ICareerTalkStudentService extends IService<CareerTalkStudent> {

    IPage<CareerTalkStudentDTO> pageList(Page<CareerTalkStudentDTO> page, QueryWrapper<CareerTalkStudentQuery> qw);
}
