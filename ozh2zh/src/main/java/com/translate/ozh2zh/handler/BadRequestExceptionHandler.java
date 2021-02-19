package com.translate.ozh2zh.handler;


import com.translate.ozh2zh.Pojo.SysResult;
import com.translate.ozh2zh.constant.CustomHttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zyj
 * @version 1.0
 * @date 2020/4/23 0023 下午 2:20
 */
@ControllerAdvice
public class BadRequestExceptionHandler{

    private static final Logger logger = LoggerFactory.getLogger(BadRequestExceptionHandler.class);

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public SysResult paramExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<String> errors = new ArrayList<>();
        // 如果 BindingResult 的 hasErrors 方法返回true，则表示有错误信息
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        /// 遍历错误信息，返回给前端
        for (ObjectError error : allErrors) {
            errors.add(error.getDefaultMessage());
        }
        return SysResult.error(errors, CustomHttpStatus.PARAM_CHECK.getCode());
    }

//    @ExceptionHandler(value = ServiceException.class)
//    @ResponseBody
//    public SysResult serviceExceptionHandler(HttpServletRequest req, ServiceException e){
//        return SysResult.error(e.getMessage(), CustomHttpStatus.SERVICE_ABNORMAL.getCode());
//    }
}
