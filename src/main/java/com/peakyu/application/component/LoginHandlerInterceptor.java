package com.peakyu.application.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    //检查是否未登录的非法访问
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("operator");
        if(user == null){
            request.setAttribute("msg","请先登录！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }
        else{
            return  true;
        }

    }
}
