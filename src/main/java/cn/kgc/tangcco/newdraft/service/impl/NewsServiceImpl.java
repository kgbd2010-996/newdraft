package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.NewsDao;
import cn.kgc.tangcco.newdraft.entity.News;
import cn.kgc.tangcco.newdraft.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/19 10:11
 * @package cn.kgc.tangcco.newdraft.service.impl
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsDao newsDao;

    @Override
    public List<News> getAllNews() {
        return newsDao.getAllNews();
    }

    @Override
    public News getNewsById(Integer newsId) {
        return newsDao.getNewsById(newsId);
    }

    @Override
    public News getNewestNews() {
        return newsDao.getNewestNews();
    }

    @Override
    public List<News> getNewsByCreatedTime() {
        return newsDao.getNewsByCreatedTime();
    }

    @Override
    public String getAuthor(Integer newsId) {
        return newsDao.getAuthor(newsId);
    }
}
