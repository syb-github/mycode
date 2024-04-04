package com.bing.rabbitmqtest.middleware.dao;

import com.bing.rabbitmqtest.middleware.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户信息
 * @author sunyibing
 * @date 2024/4/4
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * 根据年龄查询用户
     * @param age 年龄
     * @return 用户
     */
    User findByAge(int age);


    User findByName(String name);
}
