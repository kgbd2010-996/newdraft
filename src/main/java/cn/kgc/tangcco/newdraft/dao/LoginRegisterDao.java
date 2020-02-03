package cn.kgc.tangcco.newdraft.dao;


import cn.kgc.tangcco.newdraft.entity.Users;
import cn.kgc.tangcco.newdraft.entity.Userinfo;
import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.vo.UserVO;
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
    UserVO getUsersByPhone(@Param("phone") String phone);

    //根据用户名返回一个Users对象
    UserVO getUsersByUserWebName(@Param("userWebName") String userWebName);

    //添加一个Users对象
    int addUsers(@Param("users") Users newUser);


    int addUserIdToInfo(@Param("userId") String userId);


    int addUserIdToFirms(@Param("firmsId") String firmsId);


}
