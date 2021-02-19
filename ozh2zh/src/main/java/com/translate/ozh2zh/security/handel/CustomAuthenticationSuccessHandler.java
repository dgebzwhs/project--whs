package com.translate.ozh2zh.security.handel;


import com.alibaba.fastjson.JSON;
import com.translate.ozh2zh.Pojo.SysRole;
import com.translate.ozh2zh.Pojo.SysUser;
import com.translate.ozh2zh.Uitls.TokenUtil;
import com.translate.ozh2zh.constant.TokenConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Describe: 自定义 Security 登陆成功处理类
 * @Author: zyj
 * CreateTime: 2019/10/23
 * */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // 创建 Json Web Token
        SysUser customUserDetails =(SysUser) authentication.getPrincipal();

        String token = TokenUtil.createAccessToken(customUserDetails);
        String accessToken = TokenConstant.TOKEN_PREFIX + token;
        // 封装返回数据
        Map<String, Object> result =  new HashMap<>();
        result.put("code", 0);
        result.put("token", accessToken);
        // 封装 用户名及权限返回
        result.put("username",customUserDetails.getUsername());

//        List<String> roleNames=new ArrayList<>();
        List<SysRole> roles = (List<SysRole>) customUserDetails.getAuthorities();
//        for(SysRole role:roles ){
//            roleNames.add(role.getAuthority());
//        }
        result.put("roles",roles);


        // 返回封装数据
        httpServletResponse.setHeader("Content-type","application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));


    }
}
