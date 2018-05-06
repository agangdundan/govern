package xyz.frt.govern.common;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-09-2018
 * @description
 */
public class JWTFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return getSubject(servletRequest, servletResponse) != null && getSubject(servletRequest, servletResponse).isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String requestToken = req.getHeader(AppConst.KEY_AUTHORIZATION);
        if (BaseUtils.isNullOrEmpty(requestToken)) {
            out(servletResponse, JSON.toJSONString(JsonResult.error(401, "Token invalid, Try re-login.")));
            return false;
        }
        //Verify token
        try {
            Map<String, Object> map = JWTUtil.parserJwt(requestToken);
            servletRequest.setAttribute(AppConst.KEY_ID, map.get(AppConst.KEY_ID));
            servletRequest.setAttribute(AppConst.KEY_USERNAME, map.get(AppConst.KEY_USERNAME));
        } catch (ExpiredJwtException e) {
            out(servletResponse, JSON.toJSONString(JsonResult.error(401, "Token expired, Try re-login.")));
            return false;
        } catch (Exception e) {
            out(servletResponse, JSON.toJSONString(JsonResult.error(401, "Invalid token, Please login first.")));
            return false;
        }

        try {
            JWTToken token = new JWTToken(requestToken, req.getRemoteHost());
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
        }
        return true;
    }

    /**
     * 对跨域提供支持
     * @param request req
     * @param response res
     * @return Boolean
     * @throws Exception ex
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,PATCH,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    private void out(ServletResponse res, String content) {
        try {
            PrintWriter out = res.getWriter();
            res.setContentType("application/json; charset=utf-8");
            out.println(content);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
