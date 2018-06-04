package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.DailyPatrol;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.DailyPatrolService;

@RestController
public class DailyPatrolController extends BaseController<DailyPatrol> {

    private final DailyPatrolService dailyPatrolService;

    private static final String MODULE = "/daily-patrol";

    @Autowired
    public DailyPatrolController(DailyPatrolService dailyPatrolService) {
        this.dailyPatrolService = dailyPatrolService;
    }

    @Override
    BaseService<DailyPatrol> getService() {
        return dailyPatrolService;
    }

    @PostMapping(MODULE)
    public JsonResult addArea(DailyPatrol item) {
        return addItem(item);
    }

    @DeleteMapping(MODULE + "/{id}")
    public JsonResult removeArea(@PathVariable Integer id) {
        return removeItemByPrimaryKey(id);
    }

    @PatchMapping(MODULE)
    public JsonResult upgrade(DailyPatrol item) {
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
    public JsonResult findByPage(PageInfo<DailyPatrol> info) {
        return findItems(info);
    }

    @GetMapping(MODULE + "/{key}/{value}")
    public JsonResult findByCondition(@PathVariable String key, @PathVariable Object value) {
        return findItemsByCondition(key, value);
    }

    @GetMapping(MODULE + "/conditions")
    public JsonResult findByConditions(DailyPatrol item) {
        return findItemsByConditions(item);
    }


}
