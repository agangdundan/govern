package xyz.frt.govern.service;

import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.model.User;

/**
 * @author phw
 * @date Created in 04-19-2018
 * @description
 */
public interface UserService extends BaseService<User> {

    JsonResult login(User user, String host);

    User selectByUsername(String username);

}
