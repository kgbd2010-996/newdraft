package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.PersonalCenterDao;
import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.News;
import cn.kgc.tangcco.newdraft.entity.Resources;
import cn.kgc.tangcco.newdraft.entity.Userinfo;
import cn.kgc.tangcco.newdraft.service.PersonalCenterService;
import cn.kgc.tangcco.newdraft.utils.MD5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonalCenterServiceImpl implements PersonalCenterService {
    @Resource
    private PersonalCenterDao pd;

    @Override
    //根据用户id查询用户的基本信息
    public Userinfo selectUserinfoByuserId(String userId) {
        Userinfo userinfo = pd.selectUserinfoByuserId(userId);
        //返回查询到的信息
        return userinfo;
    }

    @Override
    public Resources getResourceByUserId(String userId) {
        return pd.getResourceByUserId(userId);
    }


    @Override
    //根据用户id修改用户的基本信息
    public int updateUserinfoByuserid(String userId, Userinfo userinfo) {
        int i = pd.updateUserinfoByuserid(userId, userinfo);
        return i;
    }


    @Override
    //根据用户id修改用户的密码
    public int updateUserpassword(String userId, String userPassword,String newUserPassword) {
        //用MD5对前端传过来的密码进行加密
        //把前端传过来的旧密码加密
        String MD5userPassword = MD5.getMD5_SsaltStrPwd(userPassword,userId);
        //把前端传过来的新密码加密
        String MD5newUserPassword = MD5.getMD5_SsaltStrPwd(newUserPassword, userId);
        //按用户id查询密码
        String s = pd.selectuserPasswordByuserId(userId);
        //用查询到的密码与前端传过来的密码做对比
        boolean equals = s.equals(MD5userPassword);
        //如果一样就进行密码修改
        if (equals){
            int i = pd.updateUserpassword(userId, MD5newUserPassword);
            return i;
        } else {
            //不一样就返回0
            return 0;
        }
    }

    @Override
    //商家发布新闻
    public int userAddNews(News news) {
        int i = pd.userAddNews(news);
        int newsId = news.getNewsId();
        int i1 = pd.addMidNewsFirms(newsId, news.getNewsOwner());
        if (i == 1 && i1 == 1){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    //根据商家id查找商家信息
    public Firms selectFirmsByid(String firmsId) {
        Firms firms = pd.selectFirmsByid(firmsId);
        return firms;
    }

    @Override
    //根据商家id修改商家信息
    public int updateFirmsByid(Firms firms) {
        int i = pd.updateFirmsByid(firms);
        return i;
    }

    @Override
    //根据商家姓名查找该商家发布的新闻
    public List<News> userSelectNewsBynewsOwner(String newsOwner) {
        List<News> news = pd.userSelectNewsBynewsOwner(newsOwner);
        return news;
    }

    @Override
    public News selectByNewsId(int newsId) {
        News news = pd.selectByNewsId(newsId);
        return news;
    }

    @Override
    //根据新闻id修改新闻信息
    public int userUpdateNewsByNewsid(News news, int newsId) {
        int i = pd.userUpdateNewsByNewsid(news, newsId);
        return i;
    }
}
