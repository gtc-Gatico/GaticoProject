package cn.com.gatico.orm.dataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataSourceCache {
    public static Map<Object, EntityInfo> ENTITY_INFO_MAP = new ConcurrentHashMap<>();
    public static Map<String, Object> jdbcCache = new ConcurrentHashMap<>();
    public static Map<Class, Object> dataSourceCache = new ConcurrentHashMap<>();

}
