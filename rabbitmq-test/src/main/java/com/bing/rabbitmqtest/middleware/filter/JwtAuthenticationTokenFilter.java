package com.bing.rabbitmqtest.middleware.filter;

import cn.hutool.core.bean.BeanUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bing.rabbitmqtest.middleware.dto.UserDto;
import com.bing.rabbitmqtest.middleware.entity.User;
import com.bing.rabbitmqtest.middleware.service.UserService;
import com.bing.rabbitmqtest.middleware.service.impl.UserDetailsImpl;
import com.bing.rabbitmqtest.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * jwttoken 过滤器
 * @author sunyibing
 * @date 2024/4/4
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("执行token验证过滤器");
        if(request.getRequestURI().startsWith("/login")){
            filterChain.doFilter(request, response);
            return;
        }
        // 获取header中的token
        String token = request.getHeader("token");
        if(StringUtils.isNotBlank(token) && token.startsWith("Bearer")){
            token = token.substring(7);
        } else {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }
        // 校验token
        String userId = JwtUtil.verifyToken(token);
        // 从redis中获取用户信息 login:userId
        User user = userService.findById(userId);
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUser(user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 10);
        String token = JWT.create().withClaim("userId", "1")
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC384(JwtUtil.TOKEN_SECRET));
        System.out.println(token);
        //
        DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC384(JwtUtil.TOKEN_SECRET)).build().verify(token);
        Date date = decodedJwt.getExpiresAt();
        if(date.getTime() > Calendar.getInstance().getTime().getTime()){
            System.out.println("token有效");
            String userId = decodedJwt.getClaim("userId").asString();
            System.out.println("用户id:"+userId);
        }
    }
}
