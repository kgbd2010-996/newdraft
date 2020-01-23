package cn.kgc.tangcco.newdraft.service;

import cn.kgc.tangcco.newdraft.entity.Firms;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/20 15:45
 * @package cn.kgc.tangcco.newdraft.service
 */
public interface FirmsService {

    List<Firms> getFirmsByProgramId(Integer programId);

}
