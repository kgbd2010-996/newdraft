package cn.kgc.tangcco.newdraft.controller;

import cn.kgc.tangcco.newdraft.entity.*;
import cn.kgc.tangcco.newdraft.service.NewsService;
import cn.kgc.tangcco.newdraft.service.PersonalCenterService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Json;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class PersonalCenterController {

    @Resource
    private PersonalCenterService ps;

    @Resource
    private NewsService newsService;

    @PostMapping("/selectUserinfo")
    //根据用户id查到当前用户的详细信息 2001将会返回一个json字符串
    public Result selectUserinfoByUserid(@RequestParam("userId") String userId) {
        Userinfo userinfo = ps.selectUserinfoByuserId(userId);
        Resources userres = ps.getResourceByUserId(userId);
        Result result = new Result();
        if (userinfo != null) {
            result.setMessage("success");
            result.setCode(2001);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("userinfo", userinfo);
            map.put("userres", userres);
            result.setData(JSON.toJSONString(map));
        } else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }

    @PostMapping("/updateUserinfo")
    //根据用户id修改用户信息 2001代表修改成功 2002代表修改失败
    public Result updateUserinfoByuserid(@RequestBody Userinfo userinfo) {
        int count = ps.updateUserinfoByuserid(userinfo.getUserId(), userinfo);
        Result result = new Result();
        if (count > 0) {
            result.setMessage("success");
            result.setCode(2001);
        } else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }

    @PostMapping("/updateUserPassword")
    //根据用户id修改用户密码 2001说明修改成功 2002说明修改失败
    public Result updateUserpassword(@RequestParam("userId") String userId,
                                     @RequestParam("userPassword") String userPassword,
                                     @RequestParam("newUserPassword") String newUserPassword) {
        int count = ps.updateUserpassword(userId, userPassword, newUserPassword);
        Result result = new Result();
        if (count > 0) {
            result.setMessage("success");
            result.setCode(2001);
        } else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }


    @PostMapping("/selectFirmsByid")
    //根据商家id查找商家信息
    public Result selectFirmsByid(@RequestParam("firmsId")String firmsId){
        Firms firms = ps.selectFirmsByid(firmsId);
        Result result = new Result();
        Map<String,Object> map = new HashMap<String,Object>();
        if (firms != null){
            result.setMessage("success");
            result.setCode(2001);
            map.put("firms",firms);
            result.setData(JSON.toJSONString(map));
        }else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }


    @PostMapping("/selectNews")
    //根据商家姓名查找新闻 2001将会返回一个json字符串
    public Result userSelectNewsBynewsOwner(@RequestParam("newsOwner")String newsOwner){
        List<News> news = ps.userSelectNewsBynewsOwner(newsOwner);
        Result result = new Result();
        Map<String,Object> map = new HashMap<String,Object>();
        if (news != null){
            result.setMessage("success");
            result.setCode(2001);
            map.put("news",news);
            result.setData(JSON.toJSONString(map));
        }else{
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }

    @PostMapping("/selectByNewsId")
    //根据新闻id查找新闻信息 2001将会返回一个json字符串
    public Result selectByNewsId(@RequestParam("newsId")int newsId){
        News news = ps.selectByNewsId(newsId);
        Result result = new Result();
        Map<String,Object> map = new HashMap<String,Object>();
        if (news != null){
            result.setMessage("success");
            result.setCode(2001);
            map.put("news",news);
            result.setData(JSON.toJSONString(map));
        }else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }

    @PostMapping("/addNews")
    //商家添加新闻 2001添加成功 2002添加失败
    public Result userAddNews(@RequestBody News news) {
        System.out.println(news.toString());
        int i = ps.userAddNews(news);
        Result result = new Result();
        if (i > 0) {
            result.setMessage("success");
            result.setCode(2001);
        } else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }

    @PostMapping("/updateFirmsByid")
    public Result updateFirmsByid(@RequestBody Firms firms) {
        System.out.println(firms.toString());
        int i = ps.updateFirmsByid(firms);
        Result result = new Result();
        if (i > 0) {
            result.setMessage("success");
            result.setCode(2001);
        } else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }


    @PostMapping("/updateNews")
    //商家修改新闻 2001修改成功 2002修改失败
    public Result userUpdateNewsByNewsid(@RequestBody News news) {
        int i = ps.userUpdateNewsByNewsid(news, news.getNewsId());
        Result result = new Result();
        if (i > 0) {
            result.setMessage("success");
            result.setCode(2001);
        } else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }

    @PostMapping("/delNews")
    //商家修改新闻 2001修改成功 2002修改失败
    public Result delNews(@RequestParam("newsId")Integer newsId) {
        Integer delStatus = newsService.delNewsByNewsId(newsId);
        Result result = new Result();
        if (delStatus > 0) {
            result.setMessage("success");
            result.setCode(2001);
        } else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }

}
