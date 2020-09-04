package cn.com.gatico.orm.base;

import cn.com.gatico.orm.Test2Entity;
import cn.com.gatico.orm.annotation.DBSource;

import java.util.List;

@DBSource(name = "kt")
public class Test2EntityDaoImpl extends BaseDaoImpl<Test2Entity, Long> {

    public List<Test2Entity> findAllEntity() {
        return findAll();
    }
}
