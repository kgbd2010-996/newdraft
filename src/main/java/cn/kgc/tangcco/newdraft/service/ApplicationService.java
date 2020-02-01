package cn.kgc.tangcco.newdraft.service;

import cn.kgc.tangcco.newdraft.entity.Resources;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.InputStream;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/30 13:12
 */
public interface ApplicationService {
    /*文件上传*/
    public Response uploadFile(String applicantId, String applicantPhone, InputStream inputStream)throws QiniuException;

    /*向数据库中添加文件相关信息*/
    public int addResource(String resId, String resName, String resLink, String resOwner);

    /*获取移民申请模板链接地址*/
    public Resources gettemplateResource();
}
