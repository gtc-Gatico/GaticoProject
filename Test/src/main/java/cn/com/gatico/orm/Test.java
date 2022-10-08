package cn.com.gatico.orm;

import cn.com.gatico.orm.base.Test2EntityDaoImpl;
import cn.com.gatico.orm.base.TestEntityDaoImpl;
import cn.com.gatico.orm.dataSource.DataSourceCache;
import cn.com.gatico.orm.dataSource.DataSourceScanner;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.setProperty("datasource.scanner.package", "cn.com.gatico.orm");
        DataSourceScanner.scannerDao();
        TestEntityDaoImpl dataSource = (TestEntityDaoImpl) DataSourceCache.dataSourceCache.get(TestEntityDaoImpl.class);
        List<TestEntity> list = dataSource.findAllEntity();
        list.forEach(testEntity -> {
            System.out.println(testEntity.getId()+"\t"+testEntity.getName());
        });
        System.out.println(list);
//        TestEntity testEntity = dataSource.findOne(1L);
//        System.out.println(testEntity.getName());
//        testEntity.setName("456789");
//        dataSource.update(testEntity);
//
//        TestEntity t1 = new TestEntity();
//        t1.setName("1123");
//        dataSource.save(t1);
//        System.out.println(t1.getId());
//        dataSource.delete(t1);
//        System.out.println(t1.getName());
        Test2EntityDaoImpl test2EntityDao = (Test2EntityDaoImpl) DataSourceCache.dataSourceCache.get(Test2EntityDaoImpl.class);
        List<Test2Entity> allEntity = test2EntityDao.findAllEntity();
        System.out.println(allEntity);



    }
}
