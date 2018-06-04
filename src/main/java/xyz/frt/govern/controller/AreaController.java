package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.Area;
import xyz.frt.govern.service.AreaService;
import xyz.frt.govern.service.BaseService;

@RestController
public class AreaController extends BaseController<Area> {

    private final AreaService areaService;

    private static final String MODULE = "/areas";

    @Autowired
    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @Override
    BaseService<Area> getService() {
        return areaService;
    }

    @PostMapping(MODULE)
    public JsonResult addArea(Area item) {
        return addItem(item);
    }

    @DeleteMapping(MODULE + "/{id}")
    public JsonResult removeArea(@PathVariable Integer id) {
        return removeItemByPrimaryKey(id);
    }

    @PatchMapping(MODULE)
    public JsonResult upgrade(Area item) {
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
    public JsonResult findByPage(PageInfo<Area> info) {
        return findItems(info);
    }

    @GetMapping(MODULE + "/{key}/{value}")
    public JsonResult findByCondition(@PathVariable String key, @PathVariable Object value) {
        return findItemsByCondition(key, value);
    }

    @GetMapping(MODULE + "/conditions")
    public JsonResult findByConditions(Area item) {
        return findItemsByConditions(item);
    }

}
