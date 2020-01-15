package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

/**
 * @program: newdraft
 * @description: 管理员表
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class Admins {

  private String adminId;
  private String adminName;
  private String adminPhone;
  private int adminType;
  private int adminStatus;

}
