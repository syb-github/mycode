package com.bing.rabbitmqtest.middleware.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户信息
 * @author sunyibing
 * @date 2024/4/2
 */

@Data
public class UserDto {
    private String id;
    private String name;
    private Integer age;
    private String password;
    private String username;
}
