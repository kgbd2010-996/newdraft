package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.FirmsDao;
import cn.kgc.tangcco.newdraft.entity.Firms;
import cn.kgc.tangcco.newdraft.service.FirmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王雨
 * @version 1.0
 * @date 2020/1/20 15:45
 * @package cn.kgc.tangcco.newdraft.service.impl
 */
@Service
public class FirmsServiceImpl implements FirmsService {

    @Autowired
    private FirmsDao firmsDao;

    @Override
    public List<Firms> getFirmsByProgramId(Integer programId) {
        return firmsDao.getFirmsByProgramId(programId);
    }
}
