package xyz.frt.govern.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import xyz.frt.govern.common.BaseUtils;
import xyz.frt.govern.common.JsonResult;

@Controller
public class OtherController extends BaseUtils {

    @GetMapping("/other/file/{fileName}")
    public JsonResult file(@PathVariable String fileName) {
        return null;
    }

}
