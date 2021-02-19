package com.translate.ozh2zh.Controller;


import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Pojo.Article;
import com.translate.ozh2zh.Pojo.SysResult;
import com.translate.ozh2zh.Pojo.SysUser;
import com.translate.ozh2zh.Service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Integer register(String userName,String passWord){
        Integer status=sysUserService.register(userName,passWord);
        return status;
    }

    @PostMapping("/getList")
    public PageInfo<SysUser> getList(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "20")Integer pageSize){
        PageInfo<SysUser> returnThings=sysUserService.getList(pageNum,pageSize);
        return returnThings;
    }



    @PostMapping("/getRoles")
    public List<String> getRoles(String roleName){
        List<String> returnThings=sysUserService.getRoles(roleName);
        return returnThings;
    }


    @PostMapping("/gaiRole")
    public Integer gaiRole(String roleDes,String userName){
        Integer returnThings=sysUserService.gaiRole(roleDes,userName);
        return returnThings;
    }


    @PostMapping("/delate/{userName}")
    public Integer delate(@PathVariable("userName") String userName){
        Integer returnThings=sysUserService.delate(userName);
        return returnThings;
    }
}
