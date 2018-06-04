package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.ReportInfo;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.ReportInfoService;

@RestController
public class ReportInfoController extends BaseController<ReportInfo> {

    private final ReportInfoService reportInfoService;

    private static final String MODULE = "/reports";

    @Autowired
    public ReportInfoController(ReportInfoService reportInfoService) {
        this.reportInfoService = reportInfoService;
    }

    @Override
    BaseService<ReportInfo> getService() {
        return reportInfoService;
    }

    @PostMapping(MODULE)
    public JsonResult addArea(ReportInfo item) {
        return addItem(item);
    }

    @DeleteMapping(MODULE + "/{id}")
    public JsonResult removeArea(@PathVariable Integer id) {
        return removeItemByPrimaryKey(id);
    }

    @PatchMapping(MODULE)
    public JsonResult upgrade(ReportInfo item) {
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
    public JsonResult findByPage(PageInfo<ReportInfo> info) {
        return findItems(info);
    }

    @GetMapping(MODULE + "/{key}/{value}")
    public JsonResult findByCondition(@PathVariable String key, @PathVariable Object value) {
        return findItemsByCondition(key, value);
    }

    @GetMapping(MODULE + "/conditions")
    public JsonResult findByConditions(ReportInfo item) {
        return findItemsByConditions(item);
    }


}
