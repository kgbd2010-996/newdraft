package cn.kgc.tangcco.newdraft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: newdraft
 * @description: TODO
 * @author: cuihao
 * @create: 2020-01-29 13:38
 * @version: V1.0
 **/
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                //要拦截的地址  如果全部拦截就用 "/**"
                .addPathPatterns("/isLogin")
//                .addPathPatterns("/appInit")
                //不需要拦截的地址
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/user_loginreg/login.html")
                .excludePathPatterns("/user_loginreg/reg.html");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

}
