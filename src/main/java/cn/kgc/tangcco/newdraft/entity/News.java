package cn.kgc.tangcco.newdraft.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: newdraft
 * @description: 新闻信息表
 * @author: cuihao
 * @create: 2020-01-15 17:33
 * @version: V1.0
 **/
@Data
public class News {

  private int newsId;
  private int newsType;
  private String newsTitle;
  private String newsContent;
  private String newsOwner;
  private Date newsCreatedTime;
  private Date newsVerifiedTime;
  private Date newsUpdatedTime;
  private int newsLevel;
  private int newsIndex;
  private int newsStatus;
  private Firms firms;
}
