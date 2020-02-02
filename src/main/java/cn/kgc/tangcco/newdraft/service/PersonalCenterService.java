package cn.kgc.tangcco.newdraft.service;

import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.News;
import cn.kgc.tangcco.newdraft.entity.Resources;
import cn.kgc.tangcco.newdraft.entity.Userinfo;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

public interface PersonalCenterService {

    /**
     * 根据用户id查到当前用户的详细信息
     * @param userId 用户ID
     * @return 查到的用户信息
     */
    Userinfo selectUserinfoByuserId(String userId);

    /**
     * 根据客户id查找客户的申请文件
     * @param userId
     * @return
     */
    Resources getResourceByUserId(@Param("userId")String userId);

    /**
     * 根据用户id修改当前用户的基本信息
     * @param userId 用户ID
     * @return  是否修改成功
     */
    int updateUserinfoByuserid(String userId,Userinfo userinfo);

    /**
     * 根据用户id修改用户密码
     * @param userId 用户id
     * @param userPassword 用户密码
     * @return 是否修改成功
     */
    int updateUserpassword(String userId,String userPassword,String newUserPassword);

    /**
     * 商家发布新闻
     * @param news 新闻的对象
     * @return 是否发布成功
     */
    int userAddNews(News news);

    /**
     * 根据商家id查找商家信息
     * @param firmsId 商家id
     * @return 商家信息
     */
    Firms selectFirmsByid(String firmsId);

    /**
     * 根据商家id修改商家信息
     * @param firms
     * @return
     */
    int updateFirmsByid(Firms firms);

    /**
     * 根据商家姓名查询商家发布的新闻
     * @param newsOwner 商家姓名
     * @return 查询到的商家发布的新闻
     */
    List<News> userSelectNewsBynewsOwner(String newsOwner);

    /**
     * 根据新闻id查询新闻
     * @param newsId 新闻id
     * @return 查到的新闻对象
     */
    News selectByNewsId(int newsId);

    /**
     *商家对发布新闻内容管理
     * @param news 新闻对象
     * @return 是否修改成功
     */
    int userUpdateNewsByNewsid(News news,int newsId);
}
