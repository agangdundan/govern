package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.DailyPatrol;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.DailyPatrolService;

@RestController
public class DailyPatrolController extends BaseController<DailyPatrol> {

    @Autowired
    private DailyPatrolService dailyPatrolService;

    @Override
    BaseService<DailyPatrol> getBaseService() {
        return dailyPatrolService;
    }

    @GetMapping("/daily-patrols")
    public JsonResult findDailyPatrols(PageInfo info) {
        return findItems(info);
    }

    @GetMapping("/daily-patrols/{id}")
    public JsonResult findDailyPatrolByPrimaryKey(@PathVariable Integer id) {
        return findItemByPrimaryKey(id);
    }

}
