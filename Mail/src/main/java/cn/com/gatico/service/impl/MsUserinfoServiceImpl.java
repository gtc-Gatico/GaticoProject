package cn.com.gatico.service.impl;

import cn.com.gatico.dao.MsUserinfoDao;
import cn.com.gatico.entity.MsUserinfoEntity;
import cn.com.gatico.service.MsUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class MsUserinfoServiceImpl implements MsUserinfoService {

    @Autowired
    MsUserinfoDao msUSerinfoDao;

    @Override
    public List<MsUserinfoEntity> listAll() {
        return msUSerinfoDao.findAll();
    }

    @Override
    public List<MsUserinfoEntity> findMsUserinfoEntityById(Long Id) {
        return msUSerinfoDao.findMsUserinfoEntityById(Id);
    }
}
