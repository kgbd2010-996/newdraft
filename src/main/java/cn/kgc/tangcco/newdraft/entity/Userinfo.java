package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

/**
 * @program: newdraft
 * @description: 用户信息表
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class Userinfo {

  private String userId;
  private String userName;
  private String userEmail;
  private String userAddress;
  private String userTel;

}
