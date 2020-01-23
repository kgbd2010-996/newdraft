package cn.kgc.tangcco.newdraft.service;

import cn.kgc.tangcco.newdraft.entity.Programs;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/20 19:23
 * @package cn.kgc.tangcco.newdraft.service
 */
public interface ProgramsService {

    Programs getProgramsContentByProgramsId(Integer programId);
}
