package com.bess.springboot.wfx.filter;

import com.bess.springboot.wfx.util.JWTUtil;
import com.bess.springboot.wfx.vo.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Bess Croft
 * @DateTime 2020/8/29 11:08
 */
@WebFilter("/*")
public class JWTFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String uri = request.getRequestURI();
        String method = request.getMethod();
        // 判断请求方式
        System.out.println("请求方式："+request.getMethod());
        System.out.println("请求资源："+request.getRequestURI());
        if (uri.endsWith("/customer/login") || uri.endsWith("/memeber/login")
            || uri.contains("swagger-")||uri.contains("api-docs")
            || uri.contains("favicon") || "OPTIONS".equals(method)
            || uri.endsWith("/memeber/register")) {
            System.out.println("放行了");
            // 放行
            filterChain.doFilter(request,response);
        } else {
            try {
                //判断请求的请求头是否带上 "Token"
                if (request.getHeader("token") != null) {
                    // 验证token
                    Jws<Claims> jws = JWTUtil.Decrypt(request.getHeader("token"));
                    filterChain.doFilter(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                ResultVO vo = new ResultVO(1, "认证失败", null);
                String s = new ObjectMapper().writeValueAsString(vo);
                response.getWriter().write(s);
                System.out.println("认证失败");
                return;
            }
        }
    }

    @Override
    public void destroy() {

    }

}
