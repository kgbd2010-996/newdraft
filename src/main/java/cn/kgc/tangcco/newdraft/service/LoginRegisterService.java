package cn.kgc.tangcco.newdraft.service;

import cn.kgc.tangcco.newdraft.entity.VO.AddUsersVO;
import cn.kgc.tangcco.newdraft.entity.VO.UserVO;

/**
 * @author 姚顺
 * @version V1.0
 * @Project: newdraft
 * @Package cn.kgc.tangcco.newdraft.service.impl
 * @date 2020/1/22 14:32 星期三
 */
public interface LoginRegisterService {

    /**
     * 判断手机号是否存在
     * @param phone
     * @return 是否存在
     */
    public boolean isPhoneExists(String phone);

    /**
     * 判断登陆用户名是否存在
     * @param user_web_name
     * @return
     */
    public boolean isWebNameExists(String user_web_name);

    /**
     * 添加一个用户信息 注册
     * @param newUser
     * @return
     */
    public boolean register(AddUsersVO newUser);

    /**
     * 根据web用户名获取对象
     * @param userWebName
     * @return
     */
    public UserVO getUserVOByUserWebName(String userWebName);

    /**
     * 根据手机号获取对象
     * @param phone
     * @return
     */
    public UserVO getUserVOByPhone(String phone);
}
