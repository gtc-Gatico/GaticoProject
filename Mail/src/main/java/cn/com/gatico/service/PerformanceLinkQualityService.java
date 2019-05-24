package cn.com.gatico.service;

import cn.com.gatico.entity.PerformanceLinkQualityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

public interface PerformanceLinkQualityService  {

    List<PerformanceLinkQualityEntity> findByLinkIdAndSamplingTimeBetween(long linkId, Timestamp startTime, Timestamp endTime);

}
