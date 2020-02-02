package cn.kgc.tangcco.newdraft.service;

import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.Resources;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.InputStream;
import java.util.List;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/30 13:12
 */
public interface ApplicationService {
    /**
     * 判断登录用户是否为客户
     * @param userId    用户id
     * @return  登录用户身份
     */
    boolean getUserRoleByUserId(String userId);

    /**
     * 上传申请
     * @param applicantId       申请人id
     * @param applicantPhone    申请人手机号
     * @param inputStream       文件输入流
     * @return
     * @throws QiniuException
     */
    public Response uploadFile(String applicantId, String applicantPhone, InputStream inputStream)throws QiniuException;

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
    List<Firms> getFrimsByProgramsId(Integer programsId);

    /**
     * 向数据库添加申请文件相关信息
     * @param resName   文件名称
     * @param resLink   文件地址
     * @param resOwner  文件所有人id
     * @return  数据添加状态
     */
    int addResource(String resName, String resLink, String resOwner);

    /**
     * 向数据库添加客户与商户的中间表
     * @param clientId  客户id
     * @param firmsId    商户id
     * @return
     */
    int addmidClientsFirms(String clientId, String firmsId);

}
