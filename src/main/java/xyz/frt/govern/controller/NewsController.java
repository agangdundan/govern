package xyz.frt.govern.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyz.frt.govern.common.JsonResult;
import xyz.frt.govern.common.PageInfo;
import xyz.frt.govern.model.News;
import xyz.frt.govern.service.BaseService;
import xyz.frt.govern.service.NewsService;

@RestController
public class NewsController extends BaseController<News> {

    @Autowired
    private NewsService newsService;

    @Override
    BaseService<News> getService() {
        return newsService;
    }


    @GetMapping("/news")
    public JsonResult findNews(PageInfo info) {
        return findItems(info);
    }

    @GetMapping("/news/{id}")
    @RequiresPermissions("user:add")
    public JsonResult findNewsByPrimaryKey(@PathVariable Integer id) {
        SecurityUtils.getSubject().hasRole("admin");
       return findItemByPrimaryKey(id);
    }

}
