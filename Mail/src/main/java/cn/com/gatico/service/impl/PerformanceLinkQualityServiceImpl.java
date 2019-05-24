package cn.com.gatico.service.impl;

import cn.com.gatico.dao.PerformanceLinkQualityDao;
import cn.com.gatico.entity.PerformanceLinkQualityEntity;
import cn.com.gatico.service.PerformanceLinkQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PerformanceLinkQualityServiceImpl implements PerformanceLinkQualityService {
    @Autowired
    PerformanceLinkQualityDao performanceLinkQualityDao;

    @Override
    public List<PerformanceLinkQualityEntity> findByLinkIdAndSamplingTimeBetween(long linkId, Timestamp startTime, Timestamp endTime) {
        return performanceLinkQualityDao.findByLinkIdAndSamplingTimeBetween(linkId,startTime,endTime);
    }
}
