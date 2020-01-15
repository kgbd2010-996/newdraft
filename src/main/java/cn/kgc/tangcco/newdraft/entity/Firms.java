package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

/**
 * @program: newdraft
 * @description: 商户表
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class Firms {

  private String firmsId;
  private String firmsName;
  private String firmsIntroduction;
  private String firmsTel;
  private String firmsEmail;
  private String firmsAddress;
  private int firmsImageId;
  private String firmsUrl;
  private int frimsStatus;

}
