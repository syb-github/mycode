package com.bing.rabbitmqtest.middleware.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.bing.rabbitmqtest.middleware.dto.UserDto;
import com.bing.rabbitmqtest.middleware.entity.User;
import com.bing.rabbitmqtest.middleware.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * spring-security根据用户名获取用户信息
 * @author sunyibing
 * @date 2024/4/4
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userService.findByName(name);
        if (user == null) {
            System.out.println("用户不存在");
            log.error("用户不存在");
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
        userDetailsImpl.setUser(user);
        return userDetailsImpl;
    }
}
