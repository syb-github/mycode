package com.bing.rabbitmqtest.miniprogram.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunyibing
 * @date 2024/4/3
 */
@RestController
@RequestMapping(name = "", path = "/mini-login")
public class MiniLoginController {


    @Value("${weixin.appId}")
    private String appId;

    @Value("${weixin.secret}")
    private String secret;


    // 登录凭证校验
    public static final String jscode2session = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";


    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    // 获取手机号 post
    public static final String getuserphonenumber = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=ACCESS_TOKEN";
    /**
     * 登录接口
     * @return
     */
    @PostMapping(name = "", path = "/login")
    public Map login (@RequestBody Map<String, String> params){
        Map result = new HashMap();
        result.put("data", "123");
        return result;
    }

    /**
     * 一键登录接口
     * @return
     */
    @PostMapping(name = "", path = "/onelogin")
    public Map oneLogin (@RequestBody Map<String, String> params){
        String code = MapUtil.getStr(params, "code");
        String result = HttpUtil.get(String.format(jscode2session, appId, secret, code));
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String openId = jsonObject.getStr("openid");
        String sessionKey = jsonObject.getStr("session_key");
        Map map = new HashMap();
        map.put("data", jsonObject);
        return map;
    }


    @PostMapping(name = "", path = "/getPhoneNumber")
    public Map getPhoneNumber (@RequestBody Map<String, String> params){
        String code = MapUtil.getStr(params, "code");
        //获取
        String result = HttpUtil.get(String.format(jscode2session, appId, secret, code));
        JSONObject jsonObject = JSONUtil.parseObj(result);
        String openId = jsonObject.getStr("openid");
        String sessionKey = jsonObject.getStr("session_key");
        Map map = new HashMap();
        map.put("data", jsonObject);
        return map;
    }
}
