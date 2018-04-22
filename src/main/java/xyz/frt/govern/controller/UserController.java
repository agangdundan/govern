package xyz.frt.govern.controller;

import org.springframework.web.bind.annotation.*;
import xyz.frt.govern.common.AppConst;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.User;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
@RestController
public class UserController extends BaseController<User> {

    @Autowired
    private UserService userService;

    @Override
    BaseService<User> getBaseService() {
        return userService;
    }

    @GetMapping("/users/{id}")
    public JsonResult findByPrimaryKey(@PathVariable Integer id) {
        return findItemByPrimaryKey(id);
    }

    @PostMapping("/sign-in")
    public JsonResult signIn(User user, ServletRequest req) {
        return userService.login(user, req.getRemoteHost());
    }

    @PostMapping("/sign-up")
    public JsonResult signUp(@RequestParam String code, User user) {
        return userService.signUp(user, code);
    }

    @GetMapping("/sign-out")
    public JsonResult signOut() {
        return userService.logout();
    }

    @GetMapping("/edit-pass")
    public JsonResult updatePass(String oldPass, String newPass, ServletRequest req) {
        return userService.updatePass(oldPass, newPass, Integer.valueOf((String) req.getAttribute(AppConst.KEY_ID)));
    }

    @PostMapping("/find-pass")
    public JsonResult findPass(@RequestParam String code, User user) {
        return userService.findPass(user, code);
    }

    @GetMapping("/users")
    public JsonResult findUsers(PageInfo info) {
        return findItems(info);
    }

}
