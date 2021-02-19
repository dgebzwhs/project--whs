package com.translate.ozh2zh.Service;

import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whs
 * @since 2020-05-19
 */
public interface SysUserService extends IService<SysUser> {

    SysUser currentUser();

    Integer register(String userName, String passWord);

    PageInfo<SysUser> getList(Integer pageNum, Integer pageSize);

    List<String> getRoles(String roleName);

    Integer gaiRole(String roleDec, String userName);

    Integer delate(String userName);
}
