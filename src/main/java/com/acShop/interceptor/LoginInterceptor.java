package com.acShop.interceptor;

import com.acShop.pojo.Result;
import com.acShop.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tools.jackson.databind.ObjectMapper;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        if(url.contains("login")){
            return true;
        }

        String jwt = request.getHeader("token");
        if(jwt==null){
            Result error = Result.error("Not Login");
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();;
            response.getWriter().write(mapper.writeValueAsString(error));
            return false;
        }
        try{
            JwtUtils.parseJwt(jwt);
        }
        catch (Exception e){
            e.printStackTrace();
            Result error = Result.error("Not Login");
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();;
            response.getWriter().write(mapper.writeValueAsString(error));
            return false;
        }
        return true;
    }


}
