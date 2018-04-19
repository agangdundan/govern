package xyz.frt.govern.controller;

import xyz.frt.govern.common.AppConst;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.model.User;
import xyz.frt.govern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public JsonResult findByPrimaryKey(@PathVariable Integer id) {
        User user = userService.selectByPrimaryKey(id);
        if (user != null) {
            Map<String, Object> map = new HashMap<>();
            map.put(AppConst.KEY_DATA, user);
            return JsonResult.success("OK", map);
        }
        return JsonResult.error("Error");
    }

    @PostMapping("/login")
    public JsonResult login(User user, ServletRequest req) {
        return userService.login(user, req.getRemoteHost());
    }

}
