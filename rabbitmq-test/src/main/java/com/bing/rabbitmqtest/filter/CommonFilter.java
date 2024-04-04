package com.bing.rabbitmqtest.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器
 * servlet组件  可以对所用请求进行操作
 *
 * 可以通过 @Component 注册到spring容器
 * 也可以通过@ServletComponentScan统一将 @WebServlet、@WebFilter、@WebListener注册到spring容器
 *
 * @author sunyibing
 * @date 2024/3/25
 */
@WebFilter(urlPatterns = "/**")
public class CommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CommonFilter过滤器初始化12");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("CommonFilter过滤器1执行。。。。");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
