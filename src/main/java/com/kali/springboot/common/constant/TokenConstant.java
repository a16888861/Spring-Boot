package com.kali.springboot.common.constant;

/**
 * @author Elliot
 */
public class TokenConstant {

    /**
     * token过期时间
     */
    public static final long TOKEN_EXPIRATION_TIME = 2 * 60 * 60;
    /**
     * 刷新token过期时间
     */
    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 12 * 60 * 60;
    /**
     * Token
     */
    public static final String TOKEN = "token";
    /**
     * 用户信息前缀(全局线程使用)
     */
    public static final String USER_INFO = "user-info";
    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer";
    /**
     * 用户Token
     */
    public static final String USER_TOKEN = "user-token";
    /**
     * 用户刷新Token
     */
    public static final String USER_REFRESH_TOKEN = "user-refreshToken";
    /**
     * Token请求头标识符
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * 刷新Token求头标识符
     */
    public static final String X_AUTHORIZATION = "X-Authorization";
}