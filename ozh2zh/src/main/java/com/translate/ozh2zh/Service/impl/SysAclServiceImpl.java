package com.translate.ozh2zh.Service.impl;

import com.translate.ozh2zh.Pojo.SysAcl;
import com.translate.ozh2zh.Mapper.SysAclMapper;
import com.translate.ozh2zh.Service.SysAclService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whs
 * @since 2020-05-19
 */
@Service
public class SysAclServiceImpl extends ServiceImpl<SysAclMapper, SysAcl> implements SysAclService {

    @Override
    public boolean saveBatch(Collection<SysAcl> entityList) {
        return false;
    }
}
