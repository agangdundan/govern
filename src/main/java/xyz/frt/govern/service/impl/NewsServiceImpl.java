package xyz.frt.govern.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.frt.govern.dao.BaseMapper;
import xyz.frt.govern.dao.NewsMapper;
import xyz.frt.govern.model.News;
import xyz.frt.govern.service.NewsService;

@Service
@Transactional
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public BaseMapper<News> getMapper() {
        return newsMapper;
    }

}
