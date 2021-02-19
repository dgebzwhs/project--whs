package com.translate.ozh2zh.Config;

import com.translate.ozh2zh.handler.CustomAccessDeniedHandler;
import com.translate.ozh2zh.security.authent.CustomPasswordEncoder;
import com.translate.ozh2zh.security.authent.CustomTokenAuthenticationFilter;
import com.translate.ozh2zh.security.handel.CustomAuthenticationEntryPointHandler;
import com.translate.ozh2zh.security.handel.CustomAuthenticationFailureHandler;
import com.translate.ozh2zh.security.handel.CustomAuthenticationSuccessHandler;
import com.translate.ozh2zh.security.handel.CustomLogoutSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import com.translate.ozh2zh.Service.SysUserService;

/**
 * @author fyzzz
 * @date 2019-5-17 18:48
 */
@Slf4j
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("sysUserServiceImpl")
    @Autowired
    private UserDetailsService userService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private CustomAuthenticationEntryPointHandler customAuthenticationEntryPointHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

//    @Value("${spring.profiles.active}")
//    private String profile;

    @Value("${server.port}")
    private Integer httpsPort;

    private static final String DEV = "dev";


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //校验用户
        auth.userDetailsService(userService).passwordEncoder(new CustomPasswordEncoder());
    }

    /**
     * 验证所有请求
     * 开放/login接口,参数 username password 类型post
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sys-user/register","/**").permitAll()
                .regexMatchers("/sys-common/aclPage" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //自定义登录的url method=post
                .loginProcessingUrl("/login").permitAll()
                //登录接口参数配置
                .usernameParameter("userName")
                .passwordParameter("passWord")
                //自定义登录失败返回的json
                .failureHandler(customAuthenticationFailureHandler)
                //自定义登录成功返回的json
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                //是否清除认证信息
                .clearAuthentication(true)
                //是否使session失效
                .invalidateHttpSession(true)
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
//                .sessionManagement()
//                .invalidSessionStrategy((request,response) -> {
//                    //先设置编码，再获取输出流
//                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//                    PrintWriter out = response.getWriter();
//                    out.write(mapper.writeValueAsString(SysResult.error("登录信息过期，请重新登录。")));
//                    out.flush();
//                    out.close();
//                })
//                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPointHandler)
                .and()
                .csrf().disable()
                // 基于Token不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 添加JWT过滤器
                .addFilter(new CustomTokenAuthenticationFilter(authenticationManager()))
                // 禁用缓存
                .headers().cacheControl();

    }

    /**
     * 开放url
     * swagger页面不需要登录
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
//        if(DEV.equals(profile)){
//            web.ignoring()
//                    .antMatchers("/trackerType/**")
//                    .antMatchers("/moneyTracker/**");
//        }
        web.ignoring()
                .antMatchers("/swagger-ui.html")
                .antMatchers("/v2/**")
                .antMatchers("/webjars/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("article/**")
                .antMatchers("/sys-user/register")
                .antMatchers(
                        "/index.html",
                        "/js/**",
                        "/css/**",
                        "/fonts/**",
                        "/image/**",
                        "/favicon.ico",
                        "/",
                        "/error");
    }

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许任何的head头部
        corsConfiguration.addAllowedHeader("*");
        // 允许任何域名使用
        corsConfiguration.addAllowedOrigin("*");
        // 允许任何的请求方法
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

}
