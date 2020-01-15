package cn.kgc.tangcco.newdraft.controller;


import cn.kgc.tangcco.newdraft.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: newdraft
 * @description: 项目首页
 * @author: cuihao
 * @create: 2020-01-15 17:31
 * @version: V1.0
 **/
@RestController
//实现跨域注解
//origin="*"代表所有域名都可访问
//maxAge飞行前响应的缓存持续时间的最大年龄，简单来说就是Cookie的有效期 单位为秒
//若maxAge是负数,则代表为临时Cookie,不会被持久化,Cookie信息保存在浏览器内存中,浏览器关闭Cookie就消失
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProController {
    @Autowired
    private ProService proService;
}
