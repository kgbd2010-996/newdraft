package cn.kgc.tangcco.newdraft.config;

import cn.kgc.tangcco.newdraft.entity.Result;
import cn.kgc.tangcco.newdraft.utils.RedisUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: newdraft
 * @description: TODO
 * @author: cuihao
 * @create: 2020-01-29 13:35
 * @version: V1.0
 **/
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;
    public static MyInterceptor myInterceptor;
    @PostConstruct
    public void init() {
        myInterceptor = this;
        myInterceptor.redisUtils = this.redisUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("经过了拦截器");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //自定义header头部是否允许
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        Result result = new Result();
        String token = request.getHeader("Authorization");
        //System.out.println(token);
        if (null == token || token.equals("null") || token.equals("")) {
            result.setCode(3002);
            result.setMessage("未登录");
            returnJson(response, JSON.toJSONString(result));
            return false;
        }
        boolean exsist = myInterceptor.redisUtils.exsist(token);
        if (!exsist) {
            result.setCode(3003);
            result.setMessage("token过期或者失效");
            returnJson(response, JSON.toJSONString(result));
            return false;
        }
        return true;
    }



    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
