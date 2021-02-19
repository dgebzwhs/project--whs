package com.translate.ozh2zh.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Mapper.SysRoleMapper;

import com.translate.ozh2zh.Pojo.SysRole;
import com.translate.ozh2zh.Pojo.SysUser;
import com.translate.ozh2zh.Mapper.SysUserMapper;
import com.translate.ozh2zh.Service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.translate.ozh2zh.Uitls.MD5Utils;
import com.translate.ozh2zh.cache.SysCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whs
 * @since 2020-05-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService, UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // 通过用户名查询具体用户信息
        SysUser query = new SysUser();
        query.setUsername(userName);
        SysUser user = sysUserMapper.selectOne(new QueryWrapper<>(query));

        List<GrantedAuthority> grantedAuthorityList=new ArrayList<>();

        if (user==null){
            throw new UsernameNotFoundException("帐号不存在！");
        }else {
            List<SysRole> userRelationRole = sysRoleMapper.getRolesByUid(user.getId());
            if(userRelationRole.get(0) != null){
                user.setAuthorities(userRelationRole);
            }
//            else{
//                throw new ServiceException("请先为用户分配权限");
//            }


            // 缓存？？？
            SysCache.sysUserThreadLocal.set(user);
        }
        return user;
    }

    @Override
    public SysUser currentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetails userDetails = null;
        if(context != null){
            userDetails = (UserDetails)context.getAuthentication().getPrincipal();
        }
//        else{
//            throw new ServiceException("无法获取用户信息，请查看是否登录");
//        }
        return (SysUser) userDetails;
    }

    @Override
    public Integer register(String userName, String passWord) {

        try{
            this.loadUserByUsername(userName);
            return 2;
        }catch (UsernameNotFoundException e){

        }

        SysUser user = new SysUser();
        String salt = MD5Utils.getSalt();
        passWord = MD5Utils.getSaltMd5AndSha(passWord, salt);
        user.setUsername(userName);
        user.setPassword(passWord);
        user.setSlat(salt);

        Integer result = sysUserMapper.add(userName,passWord,salt);
        Integer result2 = sysUserMapper.addRole(userName);

        return result+result2==2?1:0;
    }

    @Override
    public PageInfo<SysUser> getList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> users = sysUserMapper.list();
        return new PageInfo<>(users);
    }

    @Override
    public List<String> getRoles(String roleName) {
        List<SysRole> roles = sysRoleMapper.getRoles(roleName);
        List<String> roleNames=new ArrayList<>();
        for(SysRole role:roles){
            roleNames.add(role.getDescription());
        }
        return roleNames;
    }

    @Override
    public Integer gaiRole(String roleDec, String userName) {
        String operator = currentUser().getUsername();
        Integer result = sysRoleMapper.gaiRole(roleDec,userName,operator);
        return result;
    }

    @Override
    public Integer delate(String userName) {
        Integer result=sysUserMapper.delete(userName);
        return result;
    }
}
