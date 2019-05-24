package cn.com.gatico.service;

import cn.com.gatico.entity.PerformanceComputeEntity;

import java.sql.Timestamp;
import java.util.List;

public interface PerformanceComputeService {

    List<PerformanceComputeEntity> listBySamplingTimeBetween(Timestamp start, Timestamp end);
}
