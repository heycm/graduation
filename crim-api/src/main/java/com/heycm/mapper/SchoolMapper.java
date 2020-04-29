package com.heycm.mapper;

import com.heycm.dto.SchoolCodeDTO;
import com.heycm.model.School;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-28
 */
public interface SchoolMapper extends BaseMapper<School> {

    List<SchoolCodeDTO> getCode();
}
