package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.model.UserRole;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.UserRoleService;

@RestController
public class UserRoleController extends BaseController<UserRole> {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    BaseService<UserRole> getService() {
        return userRoleService;
    }
}
