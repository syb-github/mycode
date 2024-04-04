package com.bing.rabbitmqtest.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户信息
 * @author sunyibing
 * @date 2024/4/2
 */
@Table(name = "USER")
@Entity
@Data
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;

}
