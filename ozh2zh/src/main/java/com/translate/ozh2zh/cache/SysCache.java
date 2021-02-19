package com.translate.ozh2zh.cache;


import com.translate.ozh2zh.Pojo.SysUser;

/**
 * @author zyj
 * @version 1.0
 * @date 2020/5/6 0006 上午 10:45
 */
public class SysCache {
    public static ThreadLocal<SysUser> sysUserThreadLocal = new ThreadLocal<>();
}
