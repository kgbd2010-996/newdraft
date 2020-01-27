package cn.kgc.tangcco.newdraft.dao;

import cn.kgc.tangcco.newdraft.entity.VO.AddUsersVO;
import cn.kgc.tangcco.newdraft.entity.VO.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 姚顺
 * @version V1.0
 * @Project: newdraft
 * @Package cn.kgc.tangcco.newdraft.dao
 * @date 2020/1/21 21:16 星期二
 * 登陆+注册用的dao层
 */
@Mapper
public interface LoginRegisterDao {

    //根据手机号返回一个Users对象
    public UserVO getUsersByPhone(@Param("phone") String phone);

    //根据用户名返回一个Users对象
    public UserVO getUsersByUserWebName(@Param("userWebName") String userWebName);

    //添加一个Users对象
    public int addUsers(@Param("users") AddUsersVO newUser);
}
