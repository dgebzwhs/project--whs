package com.translate.ozh2zh.security.authent;


import com.translate.ozh2zh.Pojo.SysUser;
import com.translate.ozh2zh.Uitls.MD5Utils;
import com.translate.ozh2zh.cache.SysCache;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @author zyj
 * @version 1.0
 * @date 2020/5/6 0006 上午 11:01
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        System.out.println("密码进行加密处理" + charSequence.toString());
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        SysUser sysUser = SysCache.sysUserThreadLocal.get();
        String saltPassword = MD5Utils.getSaltMd5AndSha(charSequence.toString(), sysUser.getSlat());
        return s.equals(saltPassword);
    }
}
