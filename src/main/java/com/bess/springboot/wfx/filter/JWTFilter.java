package com.bess.springboot.wfx.filter;

import com.bess.springboot.wfx.vo.ResultVO;
import io.jsonwebtoken.*;
import org.springframework.web.bind.annotation.ResponseBody;

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

        StringBuffer requestURL = request.getRequestURL();
        String URL = new String(requestURL);
        System.out.println(URL);
        if (URL.endsWith("/customer/login") || URL.endsWith("/memeber/login")) {
            // 放行
        } else {
            try {
                //判断请求的请求头是否带上 "Token"
                if (request.getHeader("token") != null) {
                    System.out.println("传过来的token：" + request.getHeader("token"));
                    // 验证token
                    Jws<Claims> jws = Jwts.parser().setSigningKey("fadj@Jq4$fka").parseClaimsJws(request.getHeader("token"));
                    // 获取解析的token中的用户名、id等
                    String subject = jws.getBody().getSubject();
                    System.out.println(subject);
                    filterChain.doFilter(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("认证失败");
                return;
            }
        }
    }

    @Override
    public void destroy() {

    }
}
