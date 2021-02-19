package com.translate.ozh2zh.constant;

/**
 * @author zyj
 * @version 1.0
 * @date 2020/5/7 0007 上午 9:47
 */
public enum CommandType {
    SHOUTING(1,"喊话"),
    LED(2,"LED"),
    LIMIT_HEIGHT(3,"限高")
    ;

    CommandType(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    private int type;
    private String msg;

    public int getType(){
        return this.type;
    }
}
