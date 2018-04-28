package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.model.Permission;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.PermissionService;

@RestController
public class PermissionController extends BaseController<Permission> {

    @Autowired
    private PermissionService permissionService;

    @Override
    BaseService<Permission> getService() {
        return permissionService;
    }
}
