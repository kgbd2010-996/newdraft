package cn.kgc.tangcco.newdraft.dao;

import cn.kgc.tangcco.newdraft.entity.Resources;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/29 17:06
 */
@Mapper
public interface ApplicationDao {

    /*获取移民申请模板链接地址*/
    Resources gettemplateResource();

    /*向数据库中添加文件相关信息*/
    int addResource(@Param("resId") String resId, @Param("resName") String resName, @Param("resLink") String resLink, @Param("resOwner") String resOwner);
}
