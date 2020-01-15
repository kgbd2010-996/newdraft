package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

/**
 * @program: newdraft
 * @description: 返回给前端的类
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class Result {
    //返回编码
    private int code;
    //返回对应的信息
    private String message;
    //返回的数据json字符串
    private String data;
}
