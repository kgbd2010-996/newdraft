package cn.kgc.tangcco.newdraft.controller;

import cn.kgc.tangcco.newdraft.entity.Result;
import cn.kgc.tangcco.newdraft.service.LoginRegisterService;
import cn.kgc.tangcco.newdraft.utils.MD5;
import cn.kgc.tangcco.newdraft.utils.RedisUtils;
import cn.kgc.tangcco.newdraft.utils.UserAgentUtils;
import cn.kgc.tangcco.newdraft.vo.UserVO;
import com.alibaba.fastjson.JSON;
import cz.mallat.uasparser.UserAgentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 姚顺
 * @version V1.0
 * @Project: newdraft
 * @Package cn.kgc.tangcco.newdraft.controller
 * @date 2020/1/20 13:39 星期一
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class LoginController {

    @Autowired
    LoginRegisterService lrService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 普通的账号密码登陆 将会判断unknowLogin这个字符串是手机号还是用户名
     * 2001说明登陆成功
     * 2002说明账号或者密码输入错误了
     * 2003说明该账号不存在
     * 2004出现了一个异常
     * @param userPhone
     * @param pwd
     * @return
     */
    @RequestMapping("doLogin")
    public Result doLogin(@RequestParam("userPhone")String userPhone,
                          @RequestParam("pwd")String pwd,
                          HttpServletRequest request){

        UserVO userVO = lrService.getUserVOByPhone(userPhone);

        Result result = new Result();

        if(userVO != null){ //说明存在该对象
            String pwdKey = MD5.getMD5_SsaltStrPwd(pwd, userVO.getUserId());
            if(pwdKey.equals(userVO.getUserPassword())){ //说明密码校验成功
                try{
                    Map<String, Object> map = new HashMap<>();
                    String token = this.setToken(userVO, request);
                    map.put("user",userVO);
                    map.put("token",token);
                    result.setMessage("正在登陆");
                    result.setCode(2001);
                    result.setData(JSON.toJSONString(map));
                }catch (Exception e){
                    result.setMessage("出现异常");
                    result.setCode(2004);
                }


                //在这里应该对这个用户进行某些处理 比如session或token

            }else{ //否则密码校验失败
                result.setMessage("请检查账号或密码");
                result.setCode(2002);
            }
        }else{ //说明不存在该对象 也就是不存在该用户的信息
            result.setMessage("该用户不存在");
            result.setCode(2003);
        }

        return result;
    }

    /**
     * 手机号+验证码登陆 因为在login.js已经验证完了验证码 所以可以直接获取到对象
     * 2001说明登陆成功
     * 2003说明该手机号不存在
     * 2004出现了一个异常
     * @param phone
     * @return
     */
    @RequestMapping("doPhoneLogin")
    public Result doPhoneLogin(@RequestParam("phone")String phone,
                               HttpServletRequest request){

        UserVO userVO = lrService.getUserVOByPhone(phone);

        Result result = new Result();

        if(userVO != null){ //说明存在该手机的用户
            try{
                Map<String, Object> map = new HashMap<>();
                String token = this.setToken(userVO, request);
                map.put("user",userVO);
                map.put("token",token);
                result.setMessage("正在登陆");
                result.setCode(2001);
                result.setData(JSON.toJSONString(map));
            }catch (Exception e){
                result.setMessage("出现异常");
                result.setCode(2004);
            }
        }else{ //好像在这里验证了两次手机是否存在（发送验证码时候的一次  和  这里一次） 说明不存在该手机用户
            result.setMessage("该手机不存在");
            result.setCode(2003);
        }

        return result;
    }


    /**
     * 设置token
     * @param userVO
     */
    public String setToken(UserVO userVO,HttpServletRequest request) throws IOException {
        String ua=request.getHeader("User-Agent");
        //System.out.println("ua:"+ua);
        UserAgentInfo userAgentInfo= UserAgentUtils.uaSparser.parse(ua);
        String type=userAgentInfo.getDeviceType();
        //System.out.println(type);
        String token = this.createToken(userVO, type);
        boolean flag = this.saveToken(token, userVO);
        if (flag) {
            return token;
        }
        return null;
    }


    /**
     * 根据UserVO对象产生token
     * @param user
     * @param type
     * @return
     */
    private String createToken(UserVO user,String type){
        StringBuilder builder=new StringBuilder();
        builder.append("token-");
        builder.append(type).append("-");
        String info= MD5.getMD5(user.toString(),16);
        builder.append(info+"-");
        builder.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        builder.append(UUID.randomUUID().toString().substring(0,8));
        return builder.toString();
    }

    /**
     * 保存token和UserVO对象
     * @param token
     * @param user
     */
    private boolean saveToken(String token,UserVO user){
        String key = user.getUserId();
        String value = (String) redisUtils.get(key);
        if (null != value) {
            redisUtils.del(key);
            redisUtils.del(value);
        }
        try {
            redisUtils.set(key, token, 3000);
            redisUtils.set(token,JSON.toJSONString(user),3000);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
