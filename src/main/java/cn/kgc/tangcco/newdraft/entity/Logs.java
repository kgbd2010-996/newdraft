package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

/**
 * @program: newdraft
 * @description: 日志表
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class Logs {

  private int lid;
  private String logOwner;
  private String logContent;
  private int logType;
  private int logStatus;

}
