package cn.kgc.tangcco.newdraft.controller;

import cn.kgc.tangcco.newdraft.entity.*;
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

    @PostMapping("/selectUserinfo")
    //根据用户id查到当前用户的详细信息 2001将会返回一个json字符串
    public Result selectUserinfoByUserid(@RequestParam("userId") String userId) {

        Userinfo userinfo = ps.selectUserinfoByuserId(userId);
        Resources userres = ps.getResourceByUserId(userId);
        Result result = new Result();
        if (userinfo != null && userres != null) {
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
    public Result updateUserinfoByuserid(Userinfo userinfo) {
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
    public Result selectFirmsByid(@RequestParam("firmsId") String firmsId,
                                  @RequestParam("newsOwner") String newsOwner,
                                  @RequestParam("newsId") int newsId) {
        Firms firms = ps.selectFirmsByid(firmsId);

        List<News> allNews = ps.userSelectNewsBynewsOwner(newsOwner);

        News newsByid = ps.selectByNewsId(newsId);

        Result result = new Result();
        if (firms != null && allNews != null && newsByid != null) {
            result.setMessage("success");
            result.setCode(2001);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("firms", firms);
            map.put("allNews", allNews);
            map.put("newsByid", newsByid);
            result.setData(JSON.toJSONString(map));
        } else {
            result.setMessage("failure");
            result.setCode(2002);
        }
        return result;
    }

    @PostMapping("/addNews")
    //商家添加新闻 2001添加成功 2002添加失败
    public Result userAddNews(News news) {
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
    public Result updateFirmsByid(Firms firms) {
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
    public Result userUpdateNewsByNewsid(News news) {
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

}
