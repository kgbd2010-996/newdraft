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

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/19 11:03
 * @package cn.kgc.tangcco.newdraft.controller
 */
@RestController
@CrossOrigin
public class IndexInitController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private FirmsService firmsService;

    @Autowired
    private ProgramsService programsService;

    //主页新闻的初始化
    @RequestMapping("/indexInit")
    public Result indexInit(@RequestParam(name =  "programId",required = false,defaultValue = "1")Integer programId,
                                Model model){
        //传前端的result对象
        Result indexNewsInitResult = new Result();
        //传前端的result对象中的data所用的map
        HashMap<String, Object> map = new HashMap<String, Object>();
        //初始化新闻
        //最新的新闻
        News newestNews = newsService.getNewestNews();
        //除了最新的新闻外前3的新闻
        List<News> newsByCreatedTime = newsService.getNewsByCreatedTime();
        //初始化项目页面的合作商
        List<Firms> firmsByProgramId = firmsService.getFirmsByProgramId(programId);
        //初始化项目页面的项目内容
        Programs programsContent = programsService.getProgramsContentByProgramsId(programId);


        map.put("newestNews",newestNews);
        map.put("newsByCreatedTime",newsByCreatedTime);
        map.put("firmsByProgramId",firmsByProgramId);
        map.put("programsContent",programsContent);

        indexNewsInitResult.setData(JSON.toJSONString(map));
        return indexNewsInitResult;
    }



}
