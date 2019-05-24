package cn.com.gatico.dao;

import cn.com.gatico.entity.PerformanceComputeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PerformanceComputeDao extends JpaRepository<PerformanceComputeEntity, Long> {

    List<PerformanceComputeEntity> findBySamplingTimeBetween(Timestamp start, Timestamp end);
}
