package com.bing.rabbitmqtest.config;

import com.bing.rabbitmqtest.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * @author sunyibing
 * @date 2024/4/5
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handleRunTimeException(RuntimeException e) {
        log.error("运行异常处理", e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result handleAccessDeniedException(AccessDeniedException e) {
        log.error("security权限不足", e);
        return Result.fail(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = BadCredentialsException.class)
    public Result handleBadCredentialsException(BadCredentialsException e) {
        log.error("认证失败", e);
        return Result.fail(e.getMessage());
    }
}
