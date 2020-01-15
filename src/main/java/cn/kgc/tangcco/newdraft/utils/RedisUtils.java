package cn.kgc.tangcco.newdraft.utils;


import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @program: newdraft
 * @description: redis工具类
 * @author: cuihao
 * @create: 2020-01-15 18:01
 * @version: V1.0
 **/
@Component
public class RedisUtils {


    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 添加k-v
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        try{
            ValueOperations<String,Object> vo=redisTemplate.opsForValue();
            vo.set(key,value);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加k-v 并设置时间
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public boolean set(String key,String value,long seconds){
        try{
            ValueOperations<String,Object> vo=redisTemplate.opsForValue();
            vo.set(key,value);
            expire(key,seconds);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置存在时间
     * @param key
     * @param expireTime
     * @return
     */
    public boolean expire(final String key,final long expireTime){
        return redisTemplate.execute(new RedisCallback<Boolean>(){
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                boolean flag=false;
                try{
                    byte[] keys=new StringRedisSerializer().serialize(key);
                    flag=redisConnection.expire(keys,expireTime);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return flag;
            }
        });
    }

    /**
     * 删除key值
     * @param key
     * @return
     */
    public boolean del(String key) {
        try {
            Boolean flag = redisTemplate.delete(key);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查看剩余时间
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取key值
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断是否存在
     * @param key
     * @return
     */
    public boolean exsist(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        if (null == o || o == "") {
            return false;
        }else {
            return true;
        }
    }

    /**
     * 修改并且保留存在时间
     * @param key
     * @param value
     * @return
     */
    public boolean update(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
        Long time = getExpire(key);
        if (time == null) {
            return false;
        }
        if (time == 0 || time == -2) {
            return false;
        }
        if (time > 0) {
            return expire(key, time);
        }
        return true;
    }
}
