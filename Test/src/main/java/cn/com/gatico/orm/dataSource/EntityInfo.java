package cn.com.gatico.orm.dataSource;

import java.util.Map;

public class EntityInfo {

    //Entity名
    private String name;

    private Object id;

    //表名
    private String tableName;

    //实体对象
    private Class t;

    //字段映射
    private Map<String, Class<?>> field;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getT() {
        return t;
    }

    public void setT(Class t) {
        this.t = t;
    }

    public Map<String, Class<?>> getField() {
        return field;
    }

    public void setField(Map<String, Class<?>> field) {
        this.field = field;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
