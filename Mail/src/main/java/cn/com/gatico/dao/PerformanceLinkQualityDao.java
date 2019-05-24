package cn.com.gatico.dao;

import cn.com.gatico.entity.PerformanceLinkQualityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PerformanceLinkQualityDao extends JpaRepository<PerformanceLinkQualityEntity, Long> {

    List<PerformanceLinkQualityEntity> findByLinkIdAndSamplingTimeBetween(long linkId, Timestamp start, Timestamp end);

}
