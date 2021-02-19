package com.translate.ozh2zh.Mapper;

import com.translate.ozh2zh.Pojo.SysRole;
import com.translate.ozh2zh.Pojo.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whs
 * @since 2020-05-19
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    @Insert("insert into sys_user (username,password,slat,create_time) values(#{userName},#{passWord},#{salt},now())")
    Integer add(String userName, String passWord, String salt);

    @Insert("INSERT INTO `sheji`.`sys_role_user`(`role_id`, `user_id`, `operator`) VALUES (3, (SELECT sys_user.id FROM sys_user WHERE sys_user.username = #{userName}), 'system')")
    Integer addRole(String userName);


    List<SysUser> list();

    @Delete("delete from `sys_user` where username=#{userName}")
    Integer delete(String userName);
}
