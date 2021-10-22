package com.kali.blog.common.constant;

import java.time.format.DateTimeFormatter;

/**
 * @author Elliot
 */
public class CommonConstants {

    /**
     * 查询判断条件(所有)
     */
    public static final String SELECT_ALL = "all";
    /**
     * 查询判断条件(顶级)
     */
    public static final String SELECT_TOP = "top";
    /**
     * 查询判断条件(空)
     */
    public static final String SELECT_NULL = "null";

    /*----------------------------------------符号相关----------------------------------------*/
    /**
     * 横杠
     */
    public static final String HORIZONTAL_BAR = "-";
    /**
     * 空格
     */
    public static final String SPACE = " ";
    /**
     * 逗号
     */
    public static final String COMMA = ",";
    /**
     * 冒号
     */
    public static final String COLON = ":";

    /*----------------------------------------线程相关----------------------------------------*/
    /*异步线程配置*/
    /**
     * 核心线程数
     */
    public static final Integer ASYNC_EXECUTOR_THREAD_CORE_POOL_SIZE = 5;
    /**
     * 最大线程数
     */
    public static final Integer ASYNC_EXECUTOR_THREAD_MAX_POOL_SIZE = 5;
    /**
     * 队列大小
     */
    public static final Integer ASYNC_EXECUTOR_THREAD_QUEUE_CAPACITY = 99999;
    /**
     * 线程池中的线程的名称前缀
     */
    public static final String ASYNC_EXECUTOR_THREAD_NAME_PREFIX = "async-service-";

    /*----------------------------------------日期相关----------------------------------------*/
    /**
     * 日期格式(yyyy年:MM月:dd日-HH时:mm分:ss秒)
     */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy年:MM月:dd日-HH时:mm分:ss秒");
}
