package cn.kgc.tangcco.newdraft.controller;

import cn.kgc.tangcco.newdraft.entity.Resources;
import cn.kgc.tangcco.newdraft.entity.Result;
import cn.kgc.tangcco.newdraft.service.ApplicationService;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/30 13:14
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @Value("${qiniu.path}")
    private String path;


    @RequestMapping("/appInit")
    public String appInit(Model model) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        Resources templateResource = applicationService.gettemplateResource();
        resMap.put("templateResource", templateResource);
        return JSON.toJSONString(resMap);
    }

    @RequestMapping("/upload")
    public Result upload(@RequestParam("id") String applicantId,
                         @RequestParam("name") String applicantName,
                         @RequestParam("phone") String applicantPhone,
                         @RequestParam("excel") MultipartFile file) throws Exception {
        Response response = applicationService.uploadFile(applicantId, applicantPhone, file.getInputStream());
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        //文件链接
        String resLink = path + "/" + putRet.key;
        //文件名
        String resName = putRet.key;
        //文件id
        String resId = resName.substring(0, resName.indexOf("."));
        Result applicationResult = new Result();
        int resAddStatus = applicationService.addResource(resId, resName, resLink, applicantName);
        if (resAddStatus != 0) {
            applicationResult.setCode(2001);
        } else {
            applicationResult.setCode(2002);
        }
        return applicationResult;
    }
}
