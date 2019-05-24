package cn.com.gatico.service.impl;

import cn.com.gatico.dao.PerformanceComputeDao;
import cn.com.gatico.entity.PerformanceComputeEntity;
import cn.com.gatico.service.PerformanceComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PerformanceComputeServiceImpl implements PerformanceComputeService {

    @Autowired
    PerformanceComputeDao performanceComputeDao;

    @Override
    public List<PerformanceComputeEntity> listBySamplingTimeBetween(Timestamp start, Timestamp end) {
        return performanceComputeDao.findBySamplingTimeBetween(start,end);
    }
}
