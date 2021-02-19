package com.translate.ozh2zh.Pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
* <p>
    *
    * </p>
*
* @author whs
* @since 2020-05-19
*/
@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
public class SysRole implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

            /**
            * id
            */
    private Integer id;

            /**
            * 角色名
            */
    private String name;

            /**
            * 转态 0 关闭 1 开启
            */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private String description;

    private String Authority;

    @Override
    public String getAuthority() {
        return  "ROLE_" + name;
    }
}
