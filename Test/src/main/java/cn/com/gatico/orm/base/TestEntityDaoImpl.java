package cn.com.gatico.orm.base;

import cn.com.gatico.orm.TestEntity;
import cn.com.gatico.orm.annotation.DBSource;

import java.util.List;

@DBSource(name = "file")
public class TestEntityDaoImpl extends BaseDaoImpl<TestEntity, Long> {

    public List<TestEntity> findAllEntity() {
        return findAll();
    }
}
