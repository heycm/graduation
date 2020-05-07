package com.heycm.service;

import com.heycm.dto.ResumeInfoDTO;
import com.heycm.model.Resume;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
public interface IResumeService extends IService<Resume> {

    ResumeInfoDTO getResumeInfoDTO(Integer userId);

}
