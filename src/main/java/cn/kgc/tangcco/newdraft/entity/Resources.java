package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

/**
 * @program: newdraft
 * @description: 移民信息提交表
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class Resources {

  private String resId;
  private String resName;
  private String resLink;
  private String resOwner;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp updatedTime;
  private String resComment;
  private int resStatus;

}
