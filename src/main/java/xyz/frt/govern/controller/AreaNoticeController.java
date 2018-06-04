package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.AreaNotice;
import xyz.frt.govern.service.AreaNoticeService;
import xyz.frt.govern.service.BaseService;

@RestController
public class AreaNoticeController extends BaseController<AreaNotice> {

    private final AreaNoticeService areaNoticeService;

    private static final String MODULE = "/area-notice";

    @Autowired
    public AreaNoticeController(AreaNoticeService areaNoticeService) {
        this.areaNoticeService = areaNoticeService;
    }

    @Override
    BaseService<AreaNotice> getService() {
        return areaNoticeService;
    }

    @PostMapping(MODULE)
    public JsonResult addArea(AreaNotice item) {
        return addItem(item);
    }

    @DeleteMapping(MODULE + "/{id}")
    public JsonResult removeArea(@PathVariable Integer id) {
        return removeItemByPrimaryKey(id);
    }

    @PatchMapping(MODULE)
    public JsonResult upgrade(AreaNotice item) {
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
    public JsonResult findByPage(PageInfo<AreaNotice> info) {
        return findItems(info);
    }

    @GetMapping(MODULE + "/{key}/{value}")
    public JsonResult findByCondition(@PathVariable String key, @PathVariable Object value) {
        return findItemsByCondition(key, value);
    }

    @GetMapping(MODULE + "/conditions")
    public JsonResult findByConditions(AreaNotice item) {
        return findItemsByConditions(item);
    }


}
