package com.bing.rabbitmqtest.middleware.controller;

import com.bing.rabbitmqtest.common.Result;
import com.bing.rabbitmqtest.middleware.service.impl.UserDetailsImpl;
import com.bing.rabbitmqtest.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;

/**
 * 登录
 * @author sunyibing
 * @date 2024/4/4
 */
@RestController
@RequestMapping(path = "/")
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;
    @GetMapping(name = "", path = "/login")
    public Result login (String username, String password) throws LoginException {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication= authenticationManager.authenticate(authenticationToken);
        if(authentication == null){
            throw new LoginException("登录失败");
        }
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = JwtUtil.createToken(userDetails.getUser().getId());
        return Result.success(token);
    }
}
