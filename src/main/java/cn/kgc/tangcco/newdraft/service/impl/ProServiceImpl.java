package cn.kgc.tangcco.newdraft.service.impl;

import cn.kgc.tangcco.newdraft.dao.ProDao;
import cn.kgc.tangcco.newdraft.service.ProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: newdraft
 * @description: TODO
 * @author: cuihao
 * @create: 2020-01-15 17:37
 * @version: V1.0
 **/
@Service
public class ProServiceImpl implements ProService {
    @Autowired
    private ProDao proDao;
}
