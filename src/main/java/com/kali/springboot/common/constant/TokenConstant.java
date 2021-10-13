package com.kali.springboot.common.constant;

/**
 * @author Elliot
 */
public class TokenConstant {

    /**
     * token过期时间
     */
    public static final long TOKEN_EXPIRATION_TIME = 12 * 60 * 60;

    /**
     * 刷新token过期时间
     */
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 12 * 60 * 60;

    /**
     * 存放Token的header字段
     */
    public static final String TOKEN_NAME = "X-Token";

    /**
     * 存放刷新token的header字段
     */
    public static final String REFRESH_TOKEN_NAME = "Refresh-Token";
}