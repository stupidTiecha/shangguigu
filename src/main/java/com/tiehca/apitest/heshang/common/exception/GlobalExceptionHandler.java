package com.tiehca.apitest.heshang.common.exception;


import com.tiehca.apitest.heshang.bean.dto.BaseResp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author chen9
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResp err (Exception e) {
        return BaseResp.failed("系统异常：" + e.getMessage());
    }
}
