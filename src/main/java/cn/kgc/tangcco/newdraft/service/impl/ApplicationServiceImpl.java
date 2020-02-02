package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.ApplicationDao;
import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.Resources;
import cn.kgc.tangcco.newdraft.entity.Users;
import cn.kgc.tangcco.newdraft.service.ApplicationService;
import cn.kgc.tangcco.newdraft.utils.ApplicationUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/30 13:12
 */

@Service
public class ApplicationServiceImpl implements ApplicationService, InitializingBean {
    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private ApplicationUtil applicationUtil;

    //    @Value("${qiniu.Bucket}")
    @Value("caram")
    private String bucket;

    private StringMap putPolicy;

    /**
     * 判断登录用户是否为客户
     * @param userId    用户id
     * @return  登录用户身份
     */
    @Override
    public boolean getUserRoleByUserId(String userId) {
        Users loginUser = applicationDao.getUserRoleByUserId(userId);
        System.out.println(loginUser.getUserRole());
        if (loginUser.getUserRole()==1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 上传申请
     * @param applicantId       申请人id
     * @param applicantPhone    申请人手机号
     * @param inputStream       文件输入流
     * @return
     * @throws QiniuException
     */
    @Override
    public Response uploadFile(String applicantId, String applicantPhone, InputStream inputStream) throws QiniuException {
        Response response=uploadManager.put(inputStream,null,getToken(applicantId+applicantPhone),null,null);

        int trytimes=0;
        while (response.needRetry()&&trytimes<3){
            response=uploadManager.put(inputStream,null,getToken(applicantId+applicantPhone),null,null);
            trytimes++;
        }
        return response;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy=new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
    }

    private String getToken(String str){
        return this.auth.uploadToken(bucket,null,3600,putPolicy.putNotEmpty("saveKey", applicationUtil.res(str)), true);
    }

    /**
     * 获取移民申请模板地址
     * @return  移民申请模板地址
     */
    @Override
    public Resources gettemplateResource() {
        return applicationDao.gettemplateResource();
    }

    /**
     * 根据项目id获取合作商户id与名称
     * @param programsId    合作商户id
     * @return  项目中合作商户的id与名称
     */
    @Override
    public List<Firms> getFrimsByProgramsId(Integer programsId) {
        return applicationDao.getFrimsByProgramsId(programsId);
    }

    /**
     * 向数据库添加申请文件相关信息
     * @param resName   文件名称
     * @param resLink   文件地址
     * @param resOwner  文件所有人id
     * @return  数据添加状态
     */
    @Override
    @Transactional
    public int addResource(String resName, String resLink, String resOwner) {
        return applicationDao.addResource(resName, resLink, resOwner);
    }

    /**
     * 向数据库添加客户与商户的中间表
     * @param clientId  客户id
     * @param firmsd    商户id
     * @return
     */
    @Override
    @Transactional
    public int addmidClientsFirms(String clientId, String firmsId) {
        return applicationDao.addmidClientsFirms(clientId, firmsId);
    }
}
