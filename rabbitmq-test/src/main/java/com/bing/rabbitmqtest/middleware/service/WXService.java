package com.bing.rabbitmqtest.middleware.service;

import com.bing.rabbitmqtest.middleware.dto.Param;

/**
 * @author sunyibing
 * @date 2024/4/14
 */
public interface WXService {
    boolean check(Param param);
}
