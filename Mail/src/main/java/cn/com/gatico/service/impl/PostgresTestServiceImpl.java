package cn.com.gatico.service.impl;

import cn.com.gatico.dao.PostgresTestDao;
import cn.com.gatico.entity.PostgresTestEntity;
import cn.com.gatico.service.PostgresTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PostgresTestServiceImpl implements PostgresTestService {
    @Autowired
    private PostgresTestDao postgresTestDao;

    @Override
    public List<PostgresTestEntity> findAll() {
        return postgresTestDao.findAll();
    }
}
