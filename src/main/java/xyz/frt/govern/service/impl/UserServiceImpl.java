package xyz.frt.govern.service.impl;

import xyz.frt.govern.common.BaseUtils;
import xyz.frt.govern.common.JWTToken;
import xyz.frt.govern.common.JWTUtil;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.UserMapper;
import xyz.frt.govern.model.User;
import xyz.frt.govern.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    public JsonResult login(User user, String host) {
        Map<String, Object> dataMap;
        if (BaseUtils.isNullOrEmpty(user)) {
            return JsonResult.error("Username or password error");
        }
        if (BaseUtils.isNullOrEmpty(user.getUsername())) {
            return JsonResult.error("Username error");
        }
        if (BaseUtils.isNullOrEmpty(user.getPassword())) {
            return JsonResult.error("Password error");
        }
        try {
            User currUser = selectByUsername(user.getUsername());
            if (BaseUtils.isNullOrEmpty(currUser)) {
                return JsonResult.error("user didn't exist");
            }
            String password = currUser.getPassword();

            //MD5
            if (!user.getPassword().equals(password)) {
                return JsonResult.error("password incorrect");
            }

            String token = JWTUtil.createJwt(currUser.getId() + "", currUser.getUsername());
            JWTToken jwtToken = new JWTToken(token, host);
            SecurityUtils.getSubject().login(jwtToken);

            dataMap = new HashMap<>();
            dataMap.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error("Login error:" + e.getMessage());
        }
        return JsonResult.success("Login successful", dataMap);
    }

    @Override
    public User selectByUsername(String username) {
        return selectByUnique("username", username);
    }
}
