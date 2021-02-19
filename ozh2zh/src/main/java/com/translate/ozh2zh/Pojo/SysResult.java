package com.translate.ozh2zh.Pojo;

import com.translate.ozh2zh.constant.CustomHttpStatus;
import lombok.Data;

/**
 * @author fyzzz
 * 2019/8/12 14:48
 */
@Data
public class SysResult {

    private Integer code;

    private String msg;

    private Object data;

    private SysResult(){}

    public static SysResult ok(){
        SysResult ok = new SysResult();
        ok.setCode(CustomHttpStatus.REQUEST_SUCCESS.getCode());
        return ok;
    }

    public static SysResult ok(Object data){
        SysResult ok = ok();
        ok.setCode(CustomHttpStatus.REQUEST_SUCCESS.getCode());
        ok.setData(data);
        return ok;
    }

    public static SysResult error(String msg){
        SysResult error = new SysResult();
        error.setCode(CustomHttpStatus.SERVICE_ABNORMAL.getCode());
        error.setMsg(msg);
        return error;
    }

    public static SysResult error(Integer code){
        SysResult error = new SysResult();
        error.setCode(code);
        return error;
    }

    public static SysResult error(Object data, Integer code){
        SysResult error = new SysResult();
        error.setCode(code);
        error.setData(data);
        return error;
    }

}
