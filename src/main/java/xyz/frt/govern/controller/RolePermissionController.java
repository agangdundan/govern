package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.RolePermission;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.RolePermissionService;

@RestController
public class RolePermissionController extends BaseController<RolePermission> {

    private final RolePermissionService rolePermissionService;

    private static final String MODULE = "/role-permissions";

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @Override
    BaseService<RolePermission> getService() {
        return rolePermissionService;
    }

    @PostMapping(MODULE)
    public JsonResult addArea(RolePermission item) {
        return addItem(item);
    }

    @DeleteMapping(MODULE + "/{id}")
    public JsonResult removeArea(@PathVariable Integer id) {
        return removeItemByPrimaryKey(id);
    }

    @PatchMapping(MODULE)
    public JsonResult upgrade(RolePermission item) {
        return upgradeItemByPrimaryKey(item);
    }

    @GetMapping(MODULE + "/all")
    public JsonResult findAll() {
        return findItems();
    }

    @GetMapping(MODULE + "/{id}")
    public JsonResult findByPrimaryKey(@PathVariable Integer id) {
        return findItemByPrimaryKey(id);
    }

    @GetMapping(MODULE)
    public JsonResult findByPage(PageInfo<RolePermission> info) {
        return findItems(info);
    }

    @GetMapping(MODULE + "/{key}/{value}")
    public JsonResult findByCondition(@PathVariable String key, @PathVariable Object value) {
        return findItemsByCondition(key, value);
    }

    @GetMapping(MODULE + "/conditions")
    public JsonResult findByConditions(RolePermission item) {
        return findItemsByConditions(item);
    }


}
