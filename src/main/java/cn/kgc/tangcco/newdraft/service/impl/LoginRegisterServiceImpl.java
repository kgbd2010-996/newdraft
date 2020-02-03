package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.LoginRegisterDao;
import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.entity.Userinfo;
import cn.kgc.tangcco.newdraft.entity.Users;
import cn.kgc.tangcco.newdraft.service.LoginRegisterService;
import cn.kgc.tangcco.newdraft.utils.IDGenerator;
import cn.kgc.tangcco.newdraft.utils.MD5;
import cn.kgc.tangcco.newdraft.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 姚顺
 * @version V1.0
 * @Project: newdraft
 * @Package cn.kgc.tangcco.newdraft.service.impl
 * @date 2020/1/22 14:33 星期三
 */
@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Autowired
    LoginRegisterDao lrDao;

    /**
     * 判断手机号是否存在。。好像尴尬了 其实可以直接获取到对象传给Controller这样登陆也能用。。
     * @param phone
     * @return 是否存在
     */
    @Override
    public boolean isPhoneExists(String phone) {
        UserVO user = lrDao.getUsersByPhone(phone);
        if(user != null){
            return true;
        }
        return false;
    }

    /**
     * 判断登陆用户名是否存在。。好像尴尬了 其实可以直接获取到对象传给Controller这样登陆也能用。。
     * @param user_web_name
     * @return
     */
    @Override
    public boolean isWebNameExists(String user_web_name) {
        UserVO user = lrDao.getUsersByUserWebName(user_web_name);
        if(user != null){
            return true;
        }
        return false;
    }

    /**
     * 添加一个用户信息 注册
     * @param newUser
     * @return
     */
    @Override
    public boolean register(Users newUser) {

        //获得唯一id标识
        newUser.setUserId(IDGenerator.getID());
        //将密码进行md5加密
        newUser.setUserPassword(MD5.getMD5_SsaltStrPwd(newUser.getUserPassword(),newUser.getUserId()));
        int count = lrDao.addUsers(newUser);
        if(count > 0){
            /*客户信息*/
            if (newUser.getUserRole()==1){
                System.out.println(newUser.getUserId());
                int i = lrDao.addUserIdToInfo(newUser.getUserId());
                if (i>0){
                    return true; //说明添加成功
                }
                /*商户信息*/
            }else if (newUser.getUserRole()==2){
                System.out.println(newUser.getUserId());
                int i = lrDao.addUserIdToFirms(newUser.getUserId());
                if (i>0){
                    return true; //说明添加成功
                }
                /*游客信息*/
            }
        }
        return false;
    }

    /**
     * 根据web用户名获取对象
     * @param userWebName
     * @return
     */
    @Override
    public UserVO getUserVOByUserWebName(String userWebName) {
        return lrDao.getUsersByUserWebName(userWebName);
    }

    /**
     * 根据手机号获取对象
     * @param phone
     * @return
     */
    @Override
    public UserVO getUserVOByPhone(String phone) {
        return lrDao.getUsersByPhone(phone);
    }
}
