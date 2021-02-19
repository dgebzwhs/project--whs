package com.translate.ozh2zh.Uitls;


import com.alibaba.fastjson.JSON;
import com.translate.ozh2zh.Pojo.SysUser;
import com.translate.ozh2zh.constant.TokenConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


/**
 * @author zyj
 */
public class TokenUtil {

    /**
     * Describe: 创建 Json Web Token
     * Param: UserDetails
     * Token: Token
     * */
    public static String createAccessToken(SysUser sysUser){

        return Jwts.builder()
                .setId(sysUser.getId().toString())
                .setSubject(sysUser.getUsername())
                .setIssuedAt(new Date())
                .setIssuer("jmys")
                .claim("authorities", JSON.toJSONString(sysUser.getAuthorities()))
                .setExpiration(new Date(System.currentTimeMillis() + TokenConstant.EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET)
                .compact();

    }
}
