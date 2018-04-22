package xyz.frt.govern.common;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
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
