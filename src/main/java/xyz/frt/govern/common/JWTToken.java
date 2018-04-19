package xyz.frt.govern.common;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author phw
 * @date Created in 04-09-2018
 * @description
 */
public class JWTToken implements AuthenticationToken {

    private String token;
    private String host;

    public JWTToken(String token, String host) {
        this.token = token;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
