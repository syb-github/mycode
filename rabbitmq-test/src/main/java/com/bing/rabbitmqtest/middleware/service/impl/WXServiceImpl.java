package com.bing.rabbitmqtest.middleware.service.impl;

import com.bing.rabbitmqtest.middleware.dto.Param;
import com.bing.rabbitmqtest.middleware.service.WXService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class WXServiceImpl implements WXService {

    @Value("${token.value}")
    public String token;

    /**
     * 开发者通过检验signature对请求进行校验（下面有校验方式）。
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
     *
     * 1）将token、timestamp、nonce三个参数进行字典序排序
     * 2）将三个参数字符串拼接成一个字符串进行sha1加密
     * 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * @param param
     * @return
     */
    @Override
    public boolean check(Param param) {
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strs = {token,param.getTimestamp(),param.getNonce()};
        Arrays.sort(strs);
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String str = strs[0]+strs[1]+strs[2];
        String mysign = sha1(str);
        System.out.println("加密前："+str);
        System.out.println("Signature:"+param.getSignature());
        System.out.println("mysign:："+mysign);
        //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return  param.getSignature().equals(mysign);
    }

    private String sha1(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(str.getBytes());
            char[] chars = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            
            //处理加密结果
            for (byte b : digest){
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}