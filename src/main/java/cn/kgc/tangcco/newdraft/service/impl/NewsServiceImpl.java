package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.NewsDao;
import cn.kgc.tangcco.newdraft.entity.News;
import cn.kgc.tangcco.newdraft.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String,Object> getPageNews(Integer currentPage, Integer pagesize) {
        Map<String, Object> map = new HashMap<>();
        Integer start = (currentPage-1)*pagesize;
        List<News> pageNews = newsDao.getPageNews(start, pagesize);
        Integer total = newsDao.getTotal();
        map.put("pageNews",pageNews);
        map.put("total",total);
        return map;
    }

    @Override
    public News getPreNews(Integer newsId) {
        Integer index = newsDao.getIndex(newsId);
        if (index == 0) {
            return null;
        }
        News preNews = newsDao.getPreNextNews(index - 1);
        return preNews;
    }

    @Override
    public News getNextNews(Integer newsId) {
        Integer index = newsDao.getIndex(newsId);
        News nextNews = newsDao.getPreNextNews(index + 1);
        return nextNews;
    }
}
