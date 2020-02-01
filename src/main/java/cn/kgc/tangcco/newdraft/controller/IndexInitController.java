package cn.kgc.tangcco.newdraft.controller;

import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.News;
import cn.kgc.tangcco.newdraft.entity.Programs;
import cn.kgc.tangcco.newdraft.entity.Result;
import cn.kgc.tangcco.newdraft.service.FirmsService;
import cn.kgc.tangcco.newdraft.service.NewsService;
import cn.kgc.tangcco.newdraft.service.ProgramsService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/19 11:03
 * @package cn.kgc.tangcco.newdraft.controller
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class IndexInitController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private FirmsService firmsService;

    @Autowired
    private ProgramsService programsService;

    //主页新闻的初始化
    @RequestMapping("/indexInit")
    public Result indexInit(@RequestParam(name = "programId", required = false, defaultValue = "1") Integer programId) {
        //传前端的result对象
        Result indexNewsInitResult = new Result();
        //传前端的result对象中的data所用的map
        HashMap<String, Object> map = new HashMap<>();
        //初始化新闻
        //首页获得排序后最高的一条新闻当亮点
        News newestNews = newsService.getNewestNews();
        //除了最新的新闻外前3的新闻
        List<News> newsByCreatedTime = newsService.getNewsByCreatedTime();
        //初始化项目页面的合作商
        List<Firms> firmsByProgramId = firmsService.getFirmsByProgramId(programId);
        //初始化项目页面的项目内容
        Programs programsContent = programsService.getProgramsContentByProgramsId(programId);

        //讲获得的所有数据放到map集合中
        map.put("newestNews", newestNews);
        map.put("newsByCreatedTime", newsByCreatedTime);
        map.put("firmsByProgramId", firmsByProgramId);
        map.put("programsContent", programsContent);

        indexNewsInitResult.setData(JSON.toJSONString(map));
        return indexNewsInitResult;
    }

    /**
     * 获取所有新闻（排序后的）
     * @return
     */
    @RequestMapping("/getAllNews")
    public Result getAllNews() {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        List<News> allNews = newsService.getAllNews();
        //结果装到map集合中
        map.put("newsList",allNews);
        result.setCode(2001);
        result.setMessage("获取成功");
        result.setData(JSON.toJSONString(map));
        return result;
    }

    /**
     * 通过分页获取新闻
     * @param currentPage 当前页数
     * @param pagesize 每页显示的数量
     * @return
     */
    @RequestMapping("/getPageNews")
    public Result getPageNews(Integer currentPage, Integer pagesize) {
        Result result = new Result();
        Map<String, Object> map = newsService.getPageNews(currentPage, pagesize);
        result.setCode(2001);
        result.setMessage("获取成功");
        result.setData(JSON.toJSONString(map));
        return result;
    }

    /**
     * 通过新闻id 获取对应的新闻
     * @param newsId 新闻id
     * @return
     */
    @RequestMapping("/getNewsById")
    public Result getNewsById(Integer newsId) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        News news = newsService.getNewsById(newsId);
        News preNews = newsService.getPreNews(newsId);
        News nextNews = newsService.getNextNews(newsId);
        if (null == news) {
            //如果失败 返回2002代码
            result.setCode(2002);
            result.setMessage("获取失败");
            return result;
        }
        //成功返回对应的新闻
        map.put("news", news);
        //预读取上一条新闻
        map.put("preNews",preNews);
        //预读取下一条新闻
        map.put("nextNews",nextNews);
        result.setCode(2001);
        result.setMessage("获取成功");
        result.setData(JSON.toJSONString(map));
        return result;
    }

    /**
     * 根据新闻id获得文章的作者
     * @param newsId 新闻id
     * @return
     */
    @RequestMapping("/getAuthor")
    public Result getAuthor(Integer newsId) {
        Result result = new Result();
        String author = newsService.getAuthor(newsId);
        if (null == author) {
            //如果获取失败默认显示管理员
            result.setCode(2002);
            result.setMessage("获取失败");
            result.setData("管理员");
            return result;
        }
        result.setCode(2001);
        result.setMessage("获取成功");
        result.setData(author);
        return result;
    }
}
