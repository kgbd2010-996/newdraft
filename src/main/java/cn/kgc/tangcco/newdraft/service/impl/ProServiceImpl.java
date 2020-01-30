package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.ProDao;
import cn.kgc.tangcco.newdraft.service.ProService;
import cn.kgc.tangcco.newdraft.utils.RedisUtils;
import cn.kgc.tangcco.newdraft.vo.UserVO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: newdraft
 * @description: TODO
 * @author: cuihao
 * @create: 2020-01-15 17:37
 * @version: V1.0
 **/
@Service
public class ProServiceImpl implements ProService {
    @Autowired
    private ProDao proDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String isLogin(String token) {
        boolean flag = redisUtils.exsist(token);
        if (flag) {
            String usersJson = (String)redisUtils.get(token);
            return usersJson;
        }
        return null;
    }

    @Override
    public boolean resetToken(String token) {
        boolean flag = redisUtils.exsist(token);
        if (flag) {
            String usersJson = (String)redisUtils.get(token);
            UserVO users = JSON.parseObject(usersJson,UserVO.class);
            redisUtils.expire(token,3000);
            redisUtils.expire(users.getUserId(),3000);
            return true;
        }
        return false;
    }
}
