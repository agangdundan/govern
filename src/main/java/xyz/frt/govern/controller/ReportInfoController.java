package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.model.ReportInfo;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.ReportInfoService;

@RestController
public class ReportInfoController extends BaseController<ReportInfo> {

    @Autowired
    private ReportInfoService reportInfoService;

    @Override
    BaseService<ReportInfo> getService() {
        return reportInfoService;
    }

    @GetMapping("/report-info")
    public JsonResult findReportInfo() {
        return findItems();
    }

    @PostMapping("/report-info")
    public JsonResult addReportInfo(ReportInfo reportInfo) {
        return addItem(reportInfo);
    }

    @GetMapping("/report-info/{id}")
    public JsonResult findReportInfoByPrimaryKey(@PathVariable Integer id) {
        return findItemByPrimaryKey(id);
    }


}
