package cn.kgc.tangcco.newdraft.controller;


import cn.kgc.tangcco.newdraft.entity.Result;
import cn.kgc.tangcco.newdraft.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 判断用户是否登录
     * @param token 读取token
     * @return
     */
    @RequestMapping("/isLogin")
    public Result isLogin(String token) {
        Result result = new Result();
        String json = proService.isLogin(token);
        if (null==json) {
            result.setCode(2002);
            result.setMessage("获取失败");
            return result;
        }
        result.setCode(2001);
        result.setMessage("获取user对象成功");
        result.setData(json);
        return result;
    }

    /**
     * 刷新token存在时间
     * @param token token令牌
     * @return
     */
    @RequestMapping("/resetToken")
    public Result resetToken(String token) {
        Result result = new Result();
        boolean flag = proService.resetToken(token);
        if (!flag) {
            result.setCode(2002);
            result.setMessage("刷新失败！");
            result.setData("fail");
            return result;
        }
        result.setCode(2001);
        result.setMessage("刷新成功！");
        result.setData("success");
        return result;
    }
}
