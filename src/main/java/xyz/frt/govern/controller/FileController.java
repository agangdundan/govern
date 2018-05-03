package xyz.frt.govern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.frt.govern.common.AppConst;
import xyz.frt.govern.common.BaseUtils;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.model.File;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.FileService;

import javax.servlet.ServletRequest;
import java.util.HashMap;

@RestController
public class FileController extends BaseController<File> {

    @Autowired
    private FileService fileService;

    @Override
    BaseService<File> getService() {
        return fileService;
    }

    @PostMapping("/file/upload")
    public JsonResult upload(ServletRequest req, MultipartFile file) {
        return fileUpload(req, file);
    }

    @PostMapping("/files/upload")
    public JsonResult upload(ServletRequest req) {
        return filesUpload(req);
    }

    @GetMapping("/files/{fileName}")
    public JsonResult findFileByFileName(@PathVariable String fileName) {
        File file = fileService.selectByUnique("fileName", fileName);
        if (BaseUtils.isNullOrEmpty(file)) {
            return JsonResult.error("No such file");
        }
        dataMap = new HashMap<>();
        dataMap.put(AppConst.KEY_DATA, file);
        return JsonResult.success("OK", dataMap);
    }
}
