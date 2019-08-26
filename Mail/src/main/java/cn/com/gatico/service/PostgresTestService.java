package cn.com.gatico.service;

import cn.com.gatico.entity.PostgresTestEntity;

import java.util.List;

public interface PostgresTestService {

    List<PostgresTestEntity> findAll();

    void insert(int id, String name);
}
