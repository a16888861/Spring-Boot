package com.kali.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息拦截器，配合视图拦截器使用
 *
 * @author Elliot
 */
@Component
@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {

    private final ThreadLocal<Long> THREAD = new ThreadLocal<>();

    /**
     * preHandle 请求前拦截
     * 调用时间：Controller方法处理之前
     * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序一个接一个执行
     * 若返回false，则中断执行，注意：不会进入afterCompletion
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @return 结果
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        THREAD.set(System.currentTimeMillis());
        return Boolean.TRUE;
    }

    /**
     * postHandle
     * 调用前提：preHandle返回true
     * 调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
     * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序倒着执行。
     * 备注：postHandle虽然post打头，但post、get方法都能处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle未做任何处理");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * afterCompletion
     * 调用前提：preHandle返回true
     * 调用时间：DispatcherServlet进行视图的渲染之后
     * 多用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*统计方法执行耗时*/
        long millis = System.currentTimeMillis();
        log.info(request.getServletPath() + "耗时：" + (millis - THREAD.get()) + "ms");
        THREAD.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}