package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.model.Area;
import xyz.frt.govern.service.AreaService;
import xyz.frt.govern.service.BaseService;

@RestController
public class AreaController extends BaseController<Area> {

    @Autowired
    private AreaService areaService;

    @Override
    BaseService<Area> getService() {
        return areaService;
    }
}
