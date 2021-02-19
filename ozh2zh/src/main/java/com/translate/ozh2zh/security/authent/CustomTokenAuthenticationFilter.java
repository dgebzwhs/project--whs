package com.translate.ozh2zh.security.authent;

import com.translate.ozh2zh.constant.CustomHttpStatus;
import com.translate.ozh2zh.constant.TokenConstant;
//import com.translate.ozh2zh.exception.CustomException;
import com.translate.ozh2zh.Pojo.SysResult;
import com.translate.ozh2zh.Pojo.SysUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Describe: 自定义 Security Basic Filter 主要增加 Token的验证
 * Author: zyj
 * CreateTime: 2019/10/23
 */
public class CustomTokenAuthenticationFilter extends BasicAuthenticationFilter {

    public static final Logger log = LoggerFactory.getLogger(CustomTokenAuthenticationFilter.class);

    public CustomTokenAuthenticationFilter(AuthenticationManager authenticationManager) {

        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException
//            , CustomException
    {
        // 获得TokenHeader
        String tokenHeader = request.getHeader(TokenConstant.TOKEN_HEADER);
        if (null != tokenHeader && tokenHeader.startsWith(TokenConstant.TOKEN_PREFIX)) {
            try {
                // 获取请求头中JWT的Token
                if (!StringUtils.isEmpty(request.getHeader(TokenConstant.TOKEN_HEADER))) {
                    // 截取JWT前缀
                    String token = request.getHeader(TokenConstant.TOKEN_HEADER).replace(TokenConstant.TOKEN_PREFIX, "");
                    // 解析JWT
                    Claims claims = Jwts.parser()
                            .setSigningKey(TokenConstant.SECRET)
                            .parseClaimsJws(token)
                            .getBody();
                    // 获取用户名
                    String username = claims.getSubject();
                    String userId = claims.getId();
                    if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(userId)) {
                        // 获取角色
                        List<GrantedAuthority> authorities = new ArrayList<>();

                        String authority = claims.get("authorities").toString();
                        if (!StringUtils.isEmpty(authority)) {
                            List<Map<String, String>> authorityMap = JSONObject.parseObject(authority, List.class);
                            for (Map<String, String> role : authorityMap) {
                                if (!StringUtils.isEmpty(role)) {
                                    authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                                }
                            }
                        }
                        //组装参数
                        SysUser selfUserEntity = new SysUser();
                        selfUserEntity.setUsername(claims.getSubject());
                        selfUserEntity.setId(Integer.valueOf(claims.getId()));
                        selfUserEntity.setAuthorities(authorities);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(selfUserEntity, userId, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (ExpiredJwtException e) {
                log.error("Json Web Token 超 时 提 醒");
                e.printStackTrace();
                tokenPerfect(request, response, "Token失效，请重新登录");
                return;
            } catch (Exception e) {
                log.error("无 效 的 Json Web Token");
                tokenPerfect(request, response, "无效Token，请重新登录");
                e.printStackTrace();
                return;
            }
        }
        chain.doFilter(request, response);
        return;
    }

    /**
     * 负责处理失效或无效token的后期初始化
     * @param request
     * @param response
     * @param errorMsg
     * @throws IOException
     */
    public void tokenPerfect(HttpServletRequest request, HttpServletResponse response, String errorMsg) throws IOException {
        // 处理浏览器中保存的cookie
        Cookie cookie = new Cookie(TokenConstant.TOKEN_HEADER, null);
        cookie.setMaxAge(0);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

        SysResult sysResult = SysResult.error(errorMsg, CustomHttpStatus.TOKEN_INVALID.getCode());
        SecurityContextHolder.clearContext();
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSON.toJSONString(sysResult));
    }
}
