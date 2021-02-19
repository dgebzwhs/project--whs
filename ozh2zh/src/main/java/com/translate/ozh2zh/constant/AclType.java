package com.translate.ozh2zh.constant;

/**
 * @author zyj
 * @version 1.0
 * @date 2020/4/26 0026 下午 2:02
 */
public enum  AclType {
    PAGE(1, "页面"),BUTTON(1, "按钮"),OTHER(1, "其他"),
    ;
    private int code;
    private String msg;

    AclType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
       return this.code;
    }
}
