package xyz.frt.govern.controller;

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
    BaseService<News> getBaseService() {
        return newsService;
    }


    @GetMapping("/news")
    public JsonResult findNews(PageInfo info) {
        return findItems(info);
    }

    @GetMapping("/news/{id}")
    public JsonResult findNewsByPrimaryKey(@PathVariable Integer id) {
       return findItemByPrimaryKey(id);
    }

}
