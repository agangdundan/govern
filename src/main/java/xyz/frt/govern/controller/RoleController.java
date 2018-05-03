package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.model.Role;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.RoleService;

@RestController
public class RoleController extends BaseController<Role> {

    @Autowired
    private RoleService roleService;

    @Override
    BaseService<Role> getService() {
        return roleService;
    }
}
