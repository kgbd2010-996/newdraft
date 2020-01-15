package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

import java.util.List;

/**
 * @program: newdraft
 * @description: mint项目表
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class Programs {

  private String programId;
  private String programTitle;
  private String programContent;
  private String programComment;
  private int programStatus;
  private List<Firms> firmsList;
}
