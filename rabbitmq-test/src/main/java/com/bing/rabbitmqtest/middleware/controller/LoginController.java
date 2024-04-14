package com.bing.rabbitmqtest.middleware.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bing.rabbitmqtest.common.Result;
import com.bing.rabbitmqtest.middleware.service.impl.UserDetailsImpl;
import com.bing.rabbitmqtest.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.security.auth.login.LoginException;

/**
 * 登录
 * @author sunyibing
 * @date 2024/4/4
 */
@Controller
@RequestMapping(path = "/")
public class LoginController {

    @Value("${weixingzh.appId}")
    private String appId;
    @Value("${weixingzh.secret}")
    private String secret;

    @Resource
    private AuthenticationManager authenticationManager;
//    @RequestMapping(name = "", path = "/toLogin")
//    public String toLogin () {
//        return "login.html";
//    }


    @RequestMapping(name = "", path = "/toLogin")
    public String toLogin () {
        return "login.html";
    }

    @RequestMapping(name = "", path = "/login/gzh")
    public String togzhLogin (String code) {
        String redirect_uri = "https://368wg1uo1140.vicp.fun/toLogin";
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "appid="+appId+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
            String result = HttpUtil.get(url);
        return "login.html";
    }


    @GetMapping(name = "", path = "/login")
    @ResponseBody
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
