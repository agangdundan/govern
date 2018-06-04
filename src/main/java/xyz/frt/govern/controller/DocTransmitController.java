package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.DocTransmit;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.DocTransmitService;

@RestController
public class DocTransmitController extends BaseController<DocTransmit> {

    private final DocTransmitService docTransmitService;

    private static final String MODULE = "/doc-transmit";

    @Autowired
    public DocTransmitController(DocTransmitService docTransmitService) {
        this.docTransmitService = docTransmitService;
    }

    @Override
    BaseService<DocTransmit> getService() {
        return docTransmitService;
    }

    @PostMapping(MODULE)
    public JsonResult addArea(DocTransmit item) {
        return addItem(item);
    }

    @DeleteMapping(MODULE + "/{id}")
    public JsonResult removeArea(@PathVariable Integer id) {
        return removeItemByPrimaryKey(id);
    }

    @PatchMapping(MODULE)
    public JsonResult upgrade(DocTransmit item) {
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
    public JsonResult findByPage(PageInfo<DocTransmit> info) {
        return findItems(info);
    }

    @GetMapping(MODULE + "/{key}/{value}")
    public JsonResult findByCondition(@PathVariable String key, @PathVariable Object value) {
        return findItemsByCondition(key, value);
    }

    @GetMapping(MODULE + "/conditions")
    public JsonResult findByConditions(DocTransmit item) {
        return findItemsByConditions(item);
    }


}
