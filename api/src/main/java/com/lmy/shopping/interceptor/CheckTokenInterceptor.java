package com.lmy.shopping.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmy.shopping.vo.ResultVo;
import com.lmy.shopping.vo.StatusCode;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *  前置处理 拦截对应需要对应权限的的请求，基于token来作为判断依据
 *
 * @return
 * @throws Exception
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        //前端请求后端会有一次预检请求，此请求直接放行
        if("OPTIONS".equalsIgnoreCase(method)){
            return true;
        }
        String token = request.getHeader("token");
        if(token == null){
            ResultVo resultVO= new ResultVo(StatusCode.TOKEN_FAIL, "请先登录！", null);
            doResponse(response,resultVO);
        }else{
            try {
                JwtParser parser = Jwts.parser();
                //解析token的SigningKey必须和生成token时设置密码一致
                parser.setSigningKey("LY&&LMY");
                //如果token正确（密码正确，有效期内）则正常执行，否则抛出异常
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                return true;
            }catch (ExpiredJwtException e){
                ResultVo resultVO = new ResultVo(StatusCode.TOKEN_OVERDUE, "登录过期，请重新登录！", null);
                doResponse(response,resultVO);
            }catch (UnsupportedJwtException e){
                ResultVo resultVO = new ResultVo(StatusCode.TOKEN_UN_LEGAL, "Token不合法，请自重！", null);
                doResponse(response,resultVO);
            }catch (Exception e){
                ResultVo resultVO = new ResultVo(StatusCode.TOKEN_FAIL, "请先登录！", null);
                doResponse(response,resultVO);
            }
        }
        return false;
    }

    /**
     * 设置响应体
     * @param response
     * @param resultVO
     * @throws IOException
     */
    private void doResponse(HttpServletResponse response,ResultVo resultVO) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String s = new ObjectMapper().writeValueAsString(resultVO);
        out.print(s);
        out.flush();
        out.close();
    }

}
