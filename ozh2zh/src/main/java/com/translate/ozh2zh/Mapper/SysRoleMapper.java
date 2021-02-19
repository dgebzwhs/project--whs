package com.translate.ozh2zh.Mapper;

import com.translate.ozh2zh.Pojo.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whs
 * @since 2020-05-19
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> getRolesByUid(@Param("uesr_id") Integer uesr_id);

    List<SysRole> getRoles(String roleName);

    Integer gaiRole(@Param("roleDec")String roleDec,@Param("userName") String userName,@Param("operator") String operator);
}
