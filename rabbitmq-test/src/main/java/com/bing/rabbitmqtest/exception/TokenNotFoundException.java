package com.bing.rabbitmqtest.exception;

/**
 * header未携带token
 * @author sunyibing
 * @date 2024/4/5
 */
public class TokenNotFoundException extends Exception{
    public TokenNotFoundException(String message) {
        super(message);
    }
}
