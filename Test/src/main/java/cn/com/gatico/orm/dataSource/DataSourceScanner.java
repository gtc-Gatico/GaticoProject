package cn.com.gatico.orm.dataSource;

import cn.com.gatico.orm.annotation.DBSource;
import cn.com.gatico.orm.annotation.JDBC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DataSourceScanner {
    public static void scannerDao() {
        String property = System.getProperty("datasource.scanner.package");
        if (property == null || property.isEmpty()) {
            try {
                throw new Exception("未指定扫描包");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Set<Class<?>> classSet = Scanner.getClasses(property);
        classSet.forEach(aClass -> {
            JDBC jdbc = aClass.getAnnotation(JDBC.class);
            if (jdbc != null) {
                String name = jdbc.name();
                if (name != null && name.length() > 0) {
                    try {
                        DataSourceCache.jdbcCache.put(name, aClass.newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        DataSourceCache.jdbcCache.put(aClass.getSimpleName(), aClass.newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            Entity entity = aClass.getAnnotation(Entity.class);
            if (entity != null) {
                EntityInfo entityInfo = new EntityInfo();
                entityInfo.setName(entity.name());
                Field[] fields = aClass.getDeclaredFields();
                Map<String, Class<?>> fieldMap = new ConcurrentHashMap<>();
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    Column column = field.getAnnotation(Column.class);
                    if (column != null) {
                        fieldMap.put(column.name(), field.getType());
                    }
                    Id id = field.getAnnotation(Id.class);
                    if (id != null) {
                        entityInfo.setId(field);
                        fieldMap.remove(column.name());
                    }
                }
                entityInfo.setName(aClass.getSimpleName());
                entityInfo.setTableName(entity.name());
                entityInfo.setField(fieldMap);
                entityInfo.setT(aClass);
                DataSourceCache.ENTITY_INFO_MAP.put(entityInfo.getTableName(), entityInfo);
            }
            DBSource dbSource = aClass.getAnnotation(DBSource.class);
            if (dbSource != null) {
                try {
                    if (dbSource.name() != null && dbSource.name().length() > 0) {
                        Class<?> baseDao = aClass.getSuperclass();
                        Class<?> database = baseDao.getSuperclass();
                        Field fields = database.getDeclaredField("jdbc");
                        if (fields != null) {
                            DataSourceCache.dataSourceCache.put(aClass, aClass.newInstance());
                        }
                    }
                    DataSource dataSourceImpl = new DataSource();
                    Field[] dataSource = aClass.getDeclaredFields();
                    for (int i = 0; i < dataSource.length; i++) {
                        Field field = dataSource[i];
                        if (field.getType() == DataSource.class) {
                            field.setAccessible(true);
                            field.set(aClass, dataSourceImpl);
                            field.setAccessible(false);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        DataSourceCache.dataSourceCache.forEach((aClass, o) -> {
            try {
                Class<?> baseDao = aClass.getSuperclass();
                Class<?> database = baseDao.getSuperclass();
                Field jdbc = database.getDeclaredField("jdbc");
                jdbc.setAccessible(true);
                String name = o.getClass().getAnnotation(DBSource.class).name();
                Object o1 = DataSourceCache.jdbcCache.get(name);
                if (o1 == null) {
                    name = aClass.getSimpleName();
                }
                jdbc.set(o, DataSourceCache.jdbcCache.get(name));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
