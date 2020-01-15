package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

import java.util.List;

/**
 * @program: newdraft
 * @description: 用户及商户表
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class Users {

  private String userId;
  private String userWebName;
  private String userPhone;
  private String userPassword;
  private String userWechart;
  private int userRole;
  private int userStatus;
  private List<Firms> firmsList;
}
