package com.jy.modules.boot.web;

import com.google.common.base.Throwables;
import com.jy.modules.boot.exception.DroolsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 定义统一处理handler
 * <br> @ControllerAdvice+@ExceptionHandler配合使用
 * <br> 首先，我们通过@ControllerAdvice来定义一个controller增强处理器，可以通过配合使用@ExceptionHandler来进行异常的统一处理。
 * <br> 其次，通过实现ResponseBodyAdvice，对于数据的返回，进行进一步的处理，使得接口的返回值都是统一的对象。
 * Created by apple on 2019/4/30.
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private static Logger logger = LoggerFactory.getLogger(GlobalResponseHandler.class);


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        final String returnTypeName = returnType.getParameterType().getName();
        boolean feedback = !"com.jy.modules.boot.web.GlobalResponse".equals(returnTypeName) && !"org.springframework.http.ResponseEntity".equals(returnTypeName);
        return feedback;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        final String returnTypeName = returnType.getParameterType().getName();
        //无返回值
        if ("void".equals(returnTypeName)) {
            return GlobalResponse.success(null);
        }
        //返回值不是json类型
        if (!selectedConverterType.equals(MediaType.APPLICATION_JSON)) {
            return body;
        }
        if ("com.jy.modules.boot.web.GlobalResponse".equals(returnTypeName)) {
            return body;
        }
        return GlobalResponse.success(body);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({DroolsException.class})
    public <T> GlobalResponse<T> handleException(DroolsException e) {
        logger.error(Throwables.getStackTraceAsString(e));
        return GlobalResponse.fail(e.getMessage(), e.getErrorCode());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Throwable.class})
    public <T> GlobalResponse<T> handleException(Throwable e) {
        logger.error(Throwables.getStackTraceAsString(e));
        return GlobalResponse.fail(Throwables.getStackTraceAsString(e), null);
    }

}