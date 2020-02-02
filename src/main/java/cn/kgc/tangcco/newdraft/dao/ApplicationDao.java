package cn.kgc.tangcco.newdraft.dao;

import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.Resources;
import cn.kgc.tangcco.newdraft.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/29 17:06
 */
@Mapper
public interface ApplicationDao {

    /**
     * 判断登录用户是否为客户
     * @param userId    用户id
     * @return  登录用户身份
     */
    Users getUserRoleByUserId(@Param("userId") String userId);

    /**
     * 获取移民申请模板地址
     * @return  移民申请模板地址
     */
    Resources gettemplateResource();

    /**
     * 根据项目id获取合作商户id与名称
     * @param programsId    合作商户id
     * @return  项目中合作商户的id与名称
     */
    List<Firms> getFrimsByProgramsId(@Param("programsId") Integer programsId);

    /**
     * 向数据库添加申请文件相关信息
     * @param resName   文件名称
     * @param resLink   文件地址
     * @param resOwner  文件所有人id
     * @return  数据添加状态
     */
    int addResource(@Param("resName") String resName,
                    @Param("resLink") String resLink,
                    @Param("resOwner") String resOwner);

    /**
     * 向数据库添加客户与商户的中间表
     * @param clientId  客户id
     * @param firmsd    商户id
     * @return
     */
    int addmidClientsFirms(@Param("clientId") String clientId,
                           @Param("firmsId") String firmsId);
}
