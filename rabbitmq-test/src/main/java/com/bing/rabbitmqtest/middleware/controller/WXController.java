package com.bing.rabbitmqtest.middleware.controller;

import com.bing.rabbitmqtest.middleware.dto.Param;
import com.bing.rabbitmqtest.middleware.service.WXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/wx")
public class WXController {

    @Resource
    WXService wxService;


    /**
     * signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * timestamp	时间戳
     * nonce	随机数
     * echostr	随机字符串
     *
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
     * @return
     */
    @GetMapping
    public String test1(Param param) throws IOException {
        System.out.println("param:"+param);
        if(wxService.check(param)){
            System.out.println("接入成功");
            //原样返回
            return param.getEchostr();
        }else {
            System.out.println("接入失败");
            return null;
        }
    }
}