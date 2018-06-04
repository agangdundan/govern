package xyz.frt.govern.common;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-09-2018
 * @description
 */
@Slf4j
public class JWTFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return getSubject(servletRequest, servletResponse) != null && getSubject(servletRequest, servletResponse).isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String requestToken = req.getHeader(AppConst.KEY_AUTHORIZATION);
        if (BaseUtils.isNullOrEmpty(requestToken)) {
            log.error("Token invalid, Try re-login.");
            res.sendRedirect("/401");
            return false;
        }
        //Verify token
        try {
            Map<String, Object> map = JWTUtil.parserJwt(requestToken);
            servletRequest.setAttribute(AppConst.KEY_ID, map.get(AppConst.KEY_ID));
            servletRequest.setAttribute(AppConst.KEY_USERNAME, map.get(AppConst.KEY_USERNAME));
        } catch (ExpiredJwtException e) {
            log.error("Token expired, Try re-login.");
            res.sendRedirect("/401");
            return false;
        } catch (Exception e) {
            log.error("Invalid token, Please login first.");
            res.sendRedirect("/401");
            return false;
        }

        try {
            JWTToken token = new JWTToken(requestToken, req.getRemoteHost());
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException ae) {
            log.error(ae.getMessage());
            res.sendRedirect("/401");
        }
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,PATCH,DELETE");
        res.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setStatus(HttpStatus.OK.value());
            return false;
        }
        return true;
    }

}
