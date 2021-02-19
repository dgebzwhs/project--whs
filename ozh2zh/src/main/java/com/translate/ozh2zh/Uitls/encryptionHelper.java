package com.translate.ozh2zh.Uitls;

import java.security.MessageDigest;

/**
 * @author whs
 * @date 2020/5/7 0007 下午 2:35
 */
public class encryptionHelper {
    private static final String SALT = "neimengguqingchengchengxiangjiansheyanjiuyan";       // 盐
    public static String MD5Helper(String text) {
        //        // 自己写加密
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = text.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for(int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }


        return hexValue.toString();
    }
}
