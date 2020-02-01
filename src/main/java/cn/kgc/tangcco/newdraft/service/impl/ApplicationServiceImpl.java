package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.ApplicationDao;
import cn.kgc.tangcco.newdraft.entity.Resources;
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

    @Value("${qiniu.Bucket}")
    private String bucket;

    private StringMap putPolicy;

    /*文件上传*/
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

    /*向数据库中添加文件相关信息*/
    @Override
    @Transactional
    public int addResource(String resId, String resName, String resLink, String resOwner) {
        return applicationDao.addResource(resId, resName, resLink, resOwner);
    }

    /*获取移民申请模板链接地址*/
    @Override
    public Resources gettemplateResource() {
        return applicationDao.gettemplateResource();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy=new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
    }

    private String getToken(String str){
        return this.auth.uploadToken(bucket,null,3600,putPolicy.putNotEmpty("saveKey", applicationUtil.res(str)), true);
    }
}
