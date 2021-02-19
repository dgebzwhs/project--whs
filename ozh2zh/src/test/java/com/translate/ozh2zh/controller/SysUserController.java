package com.translate.ozh2zh.controller;


import com.translate.ozh2zh.Pojo.SysResult;
import com.translate.ozh2zh.Service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whs
 * @since 2020-05-19
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    public SysResult register(String userName,String passWord){
        Integer status=sysUserService.register(userName,passWord);
        return SysResult.ok();
    }
}
