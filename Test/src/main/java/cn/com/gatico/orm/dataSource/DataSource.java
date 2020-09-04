package cn.com.gatico.orm.dataSource;

import cn.com.gatico.orm.jdbc.Jdbc;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private Jdbc jdbc;

    public DataSource getDataSource() {
        return this;
    }

    public Connection getConnection() {
        return jdbc.getConnection();
    }

    public void beginTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            getConnection().commit();
            getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            getConnection().rollback();
            getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdate(String sql) {
        try {
            getConnection().setReadOnly(false);
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object> list(String sql) {
        List<Object> objects = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            EntityInfo entityInfo = DataSourceCache.ENTITY_INFO_MAP.get(metaData.getTableName(1));
            while (resultSet.next()) {
                Class aClass = entityInfo.getT();
                Object entity = aClass.newInstance();
                Field[] declaredFields = aClass.getDeclaredFields();
                for (int i = 0; i < declaredFields.length; i++) {
                    declaredFields[i].setAccessible(true);
                    if (declaredFields[i].getName().equals(resultSet.getMetaData().getColumnName(i + 1))) {
                        declaredFields[i].set(entity, resultSet.getObject(resultSet.getMetaData().getColumnName(i + 1)));
                    }
                }
                objects.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return objects;
    }

    public Object get(String sql) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setMaxRows(1);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            EntityInfo entityInfo = DataSourceCache.ENTITY_INFO_MAP.get(metaData.getTableName(1));
            Class aClass = entityInfo.getT();
            Object entity = aClass.newInstance();
            Field[] declaredFields = aClass.getDeclaredFields();
            resultSet.next();
            for (int i = 0; i < declaredFields.length; i++) {
                declaredFields[i].setAccessible(true);
                if (declaredFields[i].getName().equals(resultSet.getMetaData().getColumnName(i + 1))) {
                    declaredFields[i].set(entity, resultSet.getObject(resultSet.getMetaData().getColumnName(i + 1)));
                }
            }
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
