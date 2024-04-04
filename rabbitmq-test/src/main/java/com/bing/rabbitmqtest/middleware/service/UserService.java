package com.bing.rabbitmqtest.middleware.service;

import com.bing.rabbitmqtest.middleware.entity.User;

/**
 * @author sunyibing
 * @date 2024/4/4
 */
public interface UserService {

    User findByAge(int age);

    User findByName(String name);

    User findById(String id);
}
