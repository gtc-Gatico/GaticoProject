package cn.com.gatico.orm.base;

import cn.com.gatico.orm.Exception.NoTableException;
import cn.com.gatico.orm.dataSource.DataSource;
import cn.com.gatico.orm.dataSource.DataSourceCache;
import cn.com.gatico.orm.dataSource.EntityInfo;

import javax.persistence.Entity;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BaseDaoImpl<T, I extends Serializable> extends DataSource {

    public Class<T> getEntityClass() {
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class) params[0];
    }

    public T findOne(I i) {
        Entity entity = getEntityClass().getAnnotation(Entity.class);
        if (entity == null) {
            try {
                throw new NoTableException(" 没有对应表名");
            } catch (NoTableException e) {
                e.printStackTrace();
            }
            return null;
        }
        EntityInfo entityInfo = DataSourceCache.ENTITY_INFO_MAP.get(entity.name());
        String sql = " select * from " + entityInfo.getTableName() + " where " + ((Field) entityInfo.getId()).getName() + " = " + i;
        return (T) getDataSource().get(sql);
    }

    public List<T> findAll() {
        Entity entity = getEntityClass().getAnnotation(Entity.class);
        if (entity == null) {
            try {
                throw new NoTableException(" 没有对应表名");
            } catch (NoTableException e) {
                e.printStackTrace();
            }
            return null;
        }
        String sql = " select * from " + entity.name() + " where 1 = 1";
        List<T> object = (List<T>) getDataSource().list(sql);
        return object;
    }

    public void delete(T t) {
        Entity entity = getEntityClass().getAnnotation(Entity.class);
        if (entity == null) {
            try {
                throw new NoTableException(" 没有对应表名");
            } catch (NoTableException e) {
                e.printStackTrace();
            }
        }
        EntityInfo entityInfo = DataSourceCache.ENTITY_INFO_MAP.get(entity.name());
        try {
            Class<?> aClass = t.getClass();
            Field field = aClass.getDeclaredField(((Field) entityInfo.getId()).getName());
            field.setAccessible(true);
            String sql = " delete from " + entityInfo.getTableName() + " where " + ((Field) entityInfo.getId()).getName() + " = " + field.get(t);
            getDataSource().executeUpdate(sql);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void save(T t) {
        Entity entity = getEntityClass().getAnnotation(Entity.class);
        if (entity == null) {
            try {
                throw new NoTableException(" 没有对应表名");
            } catch (NoTableException e) {
                e.printStackTrace();
            }
        }
        EntityInfo entityInfo = DataSourceCache.ENTITY_INFO_MAP.get(entity.name());
        StringBuilder sql = new StringBuilder("insert into " + entityInfo.getTableName() + " (");
        PreparedStatement preparedStatement = null;
        try {
            List<Object> v = new LinkedList<>();
            Object[] objects = entityInfo.getField().keySet().toArray();
            StringBuilder valuesSql = new StringBuilder();
            for (int i = 0; i < objects.length; i++) {
                String key = objects[i].toString();
                if (i != objects.length - 1) {
                    sql.append(key).append(", ");
                    valuesSql.append("?, ");
                } else {
                    sql.append(key).append(" ");
                    valuesSql.append("? ");
                }
                Class<?> aClass = t.getClass();
                Field field = aClass.getDeclaredField(key);
                field.setAccessible(true);
                v.add(field.get(t));
            }

            sql.append(" ) values ( ").append(valuesSql).append(" )");
            preparedStatement = getDataSource().getConnection().prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < v.size(); i++) {
                preparedStatement.setObject(i + 1, v.get(i));
            }
            preparedStatement.executeUpdate();

            Class<?> aClass = t.getClass();
            Field field = aClass.getDeclaredField(((Field) entityInfo.getId()).getName());
            field.setAccessible(true);
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            field.set(t, generatedKeys.getObject(1));
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }


    }

    public void update(T t) {
        Entity entity = getEntityClass().getAnnotation(Entity.class);
        if (entity == null) {
            try {
                throw new NoTableException(" 没有对应表名");
            } catch (NoTableException e) {
                e.printStackTrace();
            }
        }
        EntityInfo entityInfo = DataSourceCache.ENTITY_INFO_MAP.get(entity.name());
        String sql = "update " + entityInfo.getTableName() + " set ";
        PreparedStatement preparedStatement = null;
        try {
            List<Object> v = new LinkedList<>();
            Object[] objects = entityInfo.getField().keySet().toArray();
            for (int i = 0; i < objects.length; i++) {
                String key = objects[i].toString();
                if (i != objects.length - 1) {
                    sql += key + "= ?, ";
                } else {
                    sql += key + "= ? ";
                }
                Class<?> aClass = t.getClass();
                Field field = aClass.getDeclaredField(key);
                field.setAccessible(true);
                v.add(field.get(t));
            }
            Class<?> aClass = t.getClass();
            Field field = aClass.getDeclaredField(((Field) entityInfo.getId()).getName());
            field.setAccessible(true);
            v.add(field.get(t));
            sql += " where " + ((Field) entityInfo.getId()).getName() + " = ?";
            preparedStatement = getDataSource().getConnection().prepareStatement(sql);
            for (int i = 0; i < v.size(); i++) {
                preparedStatement.setObject(i + 1, v.get(i));
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}
