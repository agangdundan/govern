package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.DocTransmit;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.DocTransmitService;

@RestController
public class DocTransmitController extends BaseController<DocTransmit> {

    @Autowired
    private DocTransmitService docTransmitService;

    @Override
    BaseService<DocTransmit> getService() {
        return docTransmitService;
    }

    @GetMapping("/doc-transmits")
    public JsonResult findDocTransmits(PageInfo info) {
        return findItems(info);
    }

    @GetMapping("/doc-transmits/{id}")
    public JsonResult findDocTransmitByPrimaryKey(@PathVariable Integer id) {
        return findItemByPrimaryKey(id);
    }

}
