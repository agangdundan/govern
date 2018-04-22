package xyz.frt.govern.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.common.BaseUtils;
import xyz.frt.govern.common.JWTToken;
import xyz.frt.govern.common.JWTUtil;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.UserMapper;
import xyz.frt.govern.model.User;
import xyz.frt.govern.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
@Service
@Transactional
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

    @Override
    public JsonResult signUp(User user, String code) {
        String username = user.getUsername();
        if (BaseUtils.isNullOrEmpty(username)) {
            return JsonResult.error("Username cannot be empty");
        }
        String password = user.getPassword();
        if (BaseUtils.isNullOrEmpty(password)) {
            return JsonResult.error("Password cannot be empty");
        }
        String phone = user.getPhone();
        if (BaseUtils.isNullOrEmpty(phone)) {
            return JsonResult.error("Phone cannot be empty");
        }
        if (BaseUtils.isNullOrEmpty(code)) {
            return JsonResult.error("Verify code cannot be empty");
        }
        //Verify msg code
        /*if (!password.equals()) {
            return JsonResult.error("Check you password and repass");
        }*/
        if (userMapper.insertSelective(user) == 0) {
            return JsonResult.error("Internal server error");
        }
        return JsonResult.success("Sign up successful");
    }

    @Override
    public JsonResult findPass(User user, String code) {
        if (BaseUtils.isNullOrEmpty(code)) {
            return JsonResult.error("Verify code cannot be empty");
        }
        if (BaseUtils.isNullOrEmpty(user.getPassword())) {
            return JsonResult.error("Password cannot be empty");
        }
        if (BaseUtils.isNullOrEmpty(user.getId())) {
            return JsonResult.error("Id cannot be empty");
        }
        //Verify msg code

        if (userMapper.updateByPrimaryKeySelective(user) == 0) {
            return JsonResult.error("Update error");
        }
        return JsonResult.success("Update password successful");
    }

    @Override
    public JsonResult logout() {
        return JsonResult.success("Log out successful");
    }

    @Override
    public JsonResult updatePass(String oldPass, String newPass, Integer userId) {
        if (BaseUtils.isNullOrEmpty(oldPass) || BaseUtils.isNullOrEmpty(newPass)) {
            JsonResult.error("Get request params error");
        }
        User user = getMapper().selectByPrimaryKey(userId);
        if (!(user.getPassword().equals(oldPass))) {
            return JsonResult.error("Password incorrect");
        }
        user.setId(userId);
        if (getMapper().updateByPrimaryKey(user) == 0) {
            return JsonResult.error("Update password error");
        }
        return JsonResult.success("Update password successful");
    }



}
