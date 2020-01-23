package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.ProgramsDao;
import cn.kgc.tangcco.newdraft.entity.Programs;
import cn.kgc.tangcco.newdraft.service.ProgramsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/20 19:23
 * @package cn.kgc.tangcco.newdraft.service.impl
 */
@Service
public class ProgramsServiceImpl implements ProgramsService {

    @Autowired
    private ProgramsDao programsDao;

    @Override
    public Programs getProgramsContentByProgramsId(Integer programId) {
        return programsDao.getProgramsContentByProgramsId(programId);
    }
}
