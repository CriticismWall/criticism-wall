package com.freedom.security;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

/**
 * 自定义@UserOrThrow参数处理器
 * <p>
 * 如果不能从Context获取当前用户，则抛出异常
 */
public class UserOrThrowArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        UserOrThrow annotation = methodParameter.getParameterAnnotation(UserOrThrow.class);
        return annotation != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未认证");
        }
        Object principal = authentication.getPrincipal();
        if ((principal == null)
                || (!methodParameter.getParameterType().isAssignableFrom(principal.getClass()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未认证");
        }
        return principal;
    }
}
