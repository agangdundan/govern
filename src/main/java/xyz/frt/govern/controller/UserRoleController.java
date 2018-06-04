package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.UserRole;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.UserRoleService;

@RestController
public class UserRoleController extends BaseController<UserRole> {

    private final UserRoleService userRoleService;

    private static final String MODULE = "/user-roles";

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    BaseService<UserRole> getService() {
        return userRoleService;
    }

    @PostMapping(MODULE)
    public JsonResult addArea(UserRole item) {
        return addItem(item);
    }

    @DeleteMapping(MODULE + "/{id}")
    public JsonResult removeArea(@PathVariable Integer id) {
        return removeItemByPrimaryKey(id);
    }

    @PatchMapping(MODULE)
    public JsonResult upgrade(UserRole item) {
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
    public JsonResult findByPage(PageInfo<UserRole> info) {
        return findItems(info);
    }

    @GetMapping(MODULE + "/{key}/{value}")
    public JsonResult findByCondition(@PathVariable String key, @PathVariable Object value) {
        return findItemsByCondition(key, value);
    }

    @GetMapping(MODULE + "/conditions")
    public JsonResult findByConditions(UserRole item) {
        return findItemsByConditions(item);
    }


}
