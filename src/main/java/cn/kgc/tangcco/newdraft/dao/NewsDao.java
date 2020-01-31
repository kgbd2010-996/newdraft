package cn.kgc.tangcco.newdraft.dao;

import cn.kgc.tangcco.newdraft.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/16 11:05
 * @package cn.kgc.tangcco.newdraft.dao
 */
@Mapper
public interface NewsDao {
    List<News> getAllNews();

    News getNewsById(@Param("newsId") Integer newsId);

    News getNewestNews();

    List<News> getNewsByCreatedTime();

    String getAuthor(@Param("newsId") Integer newsId);

    List<News> getPageNews(@Param("start")Integer start,@Param("end")Integer end);

    Integer getTotal();

    Integer getIndex(@Param("newsId") Integer newsId);

    News getPreNextNews(@Param("index")Integer index);


}
