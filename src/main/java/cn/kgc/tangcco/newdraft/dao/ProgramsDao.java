package cn.kgc.tangcco.newdraft.dao;

import cn.kgc.tangcco.newdraft.entity.Programs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/20 15:12
 * @package cn.kgc.tangcco.newdraft.dao
 */
@Mapper
public interface ProgramsDao {

    Programs getProgramsContentByProgramsId(@Param("programId") Integer programId);

}
