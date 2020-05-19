package com.heycm.mapper;

import com.heycm.model.Interview;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-05-08
 */
public interface InterviewMapper extends BaseMapper<Interview> {

    void setIsLastChoiceZero(@Param("studentId") Integer studentId);
}
