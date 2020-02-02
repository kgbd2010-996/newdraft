package cn.kgc.tangcco.newdraft.controller;

import cn.kgc.tangcco.newdraft.entity.Firms;
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
import java.util.List;
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

    @RequestMapping("/before")
    public String before(@RequestParam("uid") String userId,Model model) {
        boolean userRoleByUserId = applicationService.getUserRoleByUserId(userId);
        Map<String, Object> bmap = new HashMap<String, Object>();
        if (userRoleByUserId){
            bmap.put("status",2001);
        }else {
            bmap.put("status",2002);
        }
        return JSON.toJSONString(bmap);

    }


    @RequestMapping("/appInit")
    public String appInit(@RequestParam("pid") Integer pid,Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        Resources templateResource = applicationService.gettemplateResource();
        map.put("templateResource", templateResource);
        List<Firms> firms = applicationService.getFrimsByProgramsId(pid);
        map.put("firms",firms);
        return JSON.toJSONString(map);
    }

    @RequestMapping("/upload")
    public Result upload(@RequestParam("ruid") String applicantId,
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
        int resAddStatus = applicationService.addResource(resName, resLink, applicantId);
        if (resAddStatus != 0) {
            applicationResult.setCode(2001);
        } else {
            applicationResult.setCode(2002);
        }
        return applicationResult;
    }

    @RequestMapping("/submit")
    public Result submit(@RequestParam("cid") String cid,
                         @RequestParam("fid") String fid,
                         Model model) {
        Result subResult = new Result();
        int status = applicationService.addmidClientsFirms(cid, fid);
        if (status==1){
            subResult.setCode(2001);
        }else {
            subResult.setCode(2002);
        }
        return subResult;
    }
}
