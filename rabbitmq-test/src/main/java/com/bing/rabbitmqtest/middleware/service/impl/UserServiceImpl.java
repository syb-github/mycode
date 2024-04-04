package com.bing.rabbitmqtest.middleware.service.impl;

import com.bing.rabbitmqtest.middleware.dao.UserRepository;
import com.bing.rabbitmqtest.middleware.entity.User;
import com.bing.rabbitmqtest.middleware.service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sunyibing
 * @date 2024/4/4
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;
    @Override
    public User findByAge(int age) {
        return userRepository.findByAge(age);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findById(String id) {
        return userRepository.getOne(id);
    }
}
