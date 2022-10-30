package com.lyx.common.web.exception;

import cn.hutool.core.collection.CollectionUtil;
import com.lyx.common.base.exception.BizException;
import com.lyx.common.base.result.R;
import com.lyx.common.base.result.ResultCode;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 黎勇炫
 * @date 2022年10月08日 18:56
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public <T> R<T> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("非法参数异常，异常原因：{}", e.getMessage(), e);
        return R.failed(e.getMessage());
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BizException.class)
    public <T> R<T> handleIllegalArgumentException(BizException e) {
        return R.failed(e.getResultCode());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> R<T> handleIllegalArgumentException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String errorMsg = null;
        if (CollectionUtil.isNotEmpty(fieldErrors)) {
            List<String> msgList = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
            errorMsg = StringUtil.join(",",msgList).toString();
        }
        return R.failed(ResultCode.PARAM_VALID_FAIL, errorMsg);
    }

}
