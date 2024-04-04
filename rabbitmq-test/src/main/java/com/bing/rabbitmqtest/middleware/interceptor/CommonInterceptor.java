package com.bing.rabbitmqtest.middleware.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * 1、实现HandlerInterceptor接口 preHandle、postHandle、afterCompletion
 * 2、新建WebMvcConfig并实现WebMvcConfigurer  通过addInterceptors方法注册拦截器
 * 3、WebMvcConfig通过@Configration注册到spring容器
 *
 * 权限控制：拦截器可以在请求到达处理器之前进行权限验证，从而实现对不同用户的访问控制。
 * 日志记录：拦截器可以在请求处理过程中记录请求和响应的详细信息，便于后期分析和调试。
 * 接口幂等性校验：拦截器可以在请求到达处理器之前进行幂等性校验，防止重复提交。
 * 数据校验：拦截器可以在请求到达处理器之前对请求数据进行校验，确保数据的合法性。
 * 缓存处理：拦截器可以在请求处理之后对响应数据进行缓存，提高系统性能。
 * @author sunyibing
 * @date 2024/3/25
 */

public class CommonInterceptor implements HandlerInterceptor {
    /**
     * 在请求到达处理器之前执行，可以用于权限验证、数据校验等操作。如果返回true，则继续执行后续操作；如果返回false，则中断请求处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器执行了1");
        return true;
    }

    /**
     * 在处理器处理请求之后执行，可以用于日志记录、缓存处理等操作
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器执行了2");
    }

    /**
     * 在视图渲染之后执行，可以用于资源清理等操作
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("拦截器执行了3");
    }
}
