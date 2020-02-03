package cn.kgc.tangcco.newdraft.controller;

import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.Result;
import cn.kgc.tangcco.newdraft.entity.Userinfo;
import cn.kgc.tangcco.newdraft.entity.Users;
import cn.kgc.tangcco.newdraft.service.LoginRegisterService;
import cn.kgc.tangcco.newdraft.utils.NoteUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 姚顺
 * @version V1.0
 * @Project: newdraft
 * @Package cn.kgc.tangcco.newdraft.controller
 * @date 2020/1/21 02:02 星期二
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class RegisterController {

    @Autowired
    LoginRegisterService lrService;

    //发送验证码
    @RequestMapping("sendRandomNumber")
    public Result sendRandomNumber(@RequestParam("phone")String phone){

        //System.out.println("手机号:" + phone);
        NoteUtil note = new NoteUtil("LTAI4FspxP1P449AbphnJtk5", "NzYFZ7zns4ixm2X3GwKWbzrXsKYHwQ");
        String randomNumber = note.getRandomNumber();
        //System.out.println("验证码:" + randomNumber);
        boolean flag = note.send(randomNumber, phone, "KGBD2010项目", "SMS_180051043");
        Result result = new Result();
        if(flag) {
            result.setMessage("发送成功");
            result.setCode(2001);
            JSONObject jo = new JSONObject();
            jo.put("verCode",randomNumber); //验证码
            result.setData(jo.toJSONString());
        }else {
            result.setMessage("发送失败");
            result.setCode(2002);
            result.setData(randomNumber);
        }
        return result;
    }

    //判断手机号是否存在
    @RequestMapping("isPhoneExists")
    public Result isPhoneExists(@RequestParam("phone")String phone){
        Result result = new Result();
        if(lrService.isPhoneExists(phone)){
            result.setMessage("手机号存在");
            result.setCode(2001);
            result.setData("true");
        }else{
            result.setMessage("手机号不存在");
            result.setCode(2002);
            result.setData("false");
        }
        //System.out.println(result);
        return result;
    }

    //判断用于登陆的用户名是否存在
    @RequestMapping("isUsernameExists")
    public Result isUsernameExists(@RequestParam("username")String userWebName){
        Result result = new Result();
        if(lrService.isWebNameExists(userWebName)){
            result.setMessage("用户名存在");
            result.setCode(2001);
            result.setData("true");
        }else{
            result.setMessage("用户名不存在");
            result.setCode(2002);
            result.setData("false");
        }
        return result;
    }

    //注册
    @RequestMapping("register")
    public Result register(Users newUser){

        //密码MD5加密前的输出 md5加密在service里
        //System.out.println(newUser);
        Result result = new Result();
        if(lrService.register(newUser)){ //说明注册成功
            result.setMessage("注册成功");
            result.setCode(2001);
            result.setData("true");
        }else{
            result.setMessage("注册失败");
            result.setCode(2002);
            result.setData("true");
        }
        //密码MD5加密后的输出
        //System.out.println(newUser);
        return result;
    }
}
