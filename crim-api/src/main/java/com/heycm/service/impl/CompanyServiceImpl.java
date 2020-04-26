package com.heycm.service.impl;

import com.heycm.model.Company;
import com.heycm.mapper.CompanyMapper;
import com.heycm.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heycm@qq.com
 * @since 2020-04-26
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
