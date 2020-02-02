package cn.kgc.tangcco.newdraft.dao;

import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.News;
import cn.kgc.tangcco.newdraft.entity.Resources;
import cn.kgc.tangcco.newdraft.entity.Userinfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PersonalCenterDao {

    /**
     * 根据用户id查到当前用户的详细信息
     * @param userId 用户ID
     * @return 查到的用户信息
     */
    Userinfo selectUserinfoByuserId(@Param("userId")String userId);

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
    int updateUserinfoByuserid(@Param("userId")String userId,@Param("userinfo")Userinfo userinfo);

    /**
     * 根据用户id查询用户的密码
     * @param userId 用户id
     * @return 查询到的用户密码
     */
    String selectuserPasswordByuserId(@Param("userId")String userId);

    /**
     * 根据用户id修改用户密码
     * @param userId 用户id
     * @param userPassword 用户密码
     * @return 是否修改成功
     */
    int updateUserpassword(@Param("userId")String userId,@Param("userPassword")String userPassword);

    /**
     * 商家发布新闻
     * @param news 新闻的对象
     * @return 是否发布成功
     */
    int userAddNews(@Param("news")News news);

    /**
     * 添加商户新闻中间表
     * @param newsId 新闻id
     * @param newsOwner 商户名称
     * @return
     */
    int addMidNewsFirms(@Param("newsId")int newsId,@Param("newsOwner")String newsOwner);

    /**
     * 根据商家id查找商家信息
     * @param firmsId 商家id
     * @return 商家信息
     */
    Firms selectFirmsByid(@Param("firmsId")String firmsId);

    /**
     * 根据商家id修改商家信息
     * @param firms
     * @return
     */
    int updateFirmsByid(@Param("firms")Firms firms);

    /**
     * 根据商家姓名查询商家发布的新闻
     * @param newsOwner 商家姓名
     * @return 查询到的商家发布的新闻
     */
    List<News> userSelectNewsBynewsOwner(@Param("newsOwner")String newsOwner);

    /**
     * 根据新闻id查询新闻
     * @param newsId 新闻id
     * @return 查到的新闻对象
     */
    News selectByNewsId(@Param("newsId")int newsId);

    /**
     *商家对发布新闻内容管理
     * @param news 新闻对象
     * @return 是否修改成功
     */
    int userUpdateNewsByNewsid(@Param("news")News news,@Param("newsId")int newsId);
}
