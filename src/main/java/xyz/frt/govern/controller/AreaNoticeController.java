package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.AreaNotice;
import xyz.frt.govern.service.AreaNoticeService;
import xyz.frt.govern.service.BaseService;

@RestController
public class AreaNoticeController extends BaseController<AreaNotice> {

    @Autowired
    private AreaNoticeService areaNoticeService;

    @Override
    BaseService<AreaNotice> getBaseService() {
        return areaNoticeService;
    }

    @GetMapping("/area-notices")
    public JsonResult findAreaNotices(PageInfo info) {
        return findItems(info);
    }

    @GetMapping("/area-notice/{id}")
    public JsonResult findAreaNoticeByPrimaryKey(@PathVariable Integer id) {
        return findItemByPrimaryKey(id);
    }

}
