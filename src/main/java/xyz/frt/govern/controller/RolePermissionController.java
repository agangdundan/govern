package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.model.RolePermission;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.RolePermissionService;

@RestController
public class RolePermissionController extends BaseController<RolePermission> {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    BaseService<RolePermission> getService() {
        return rolePermissionService;
    }
}
