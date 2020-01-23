package cn.kgc.tangcco.newdraft.dao;

import cn.kgc.tangcco.newdraft.entity.Firms;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/20 15:07
 * @package cn.kgc.tangcco.newdraft.dao
 */
@Mapper
public interface FirmsDao {

    List<Firms> getFirmsByProgramId(@Param("programId") Integer programId);

}
