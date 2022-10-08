package cn.com.gatico.jdbc;

import com.google.common.base.CaseFormat;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil {

    final static String HOSTNAME = "172.31.0.81:3306";
    final static String DATABASE = "colossus";
    final static String USERNAME = "matrix";
    final static String PASSWORD = "7xNetworks@C0M";

    /**
     * @param sql               查询语句
     * @param underScoreToCamel 是否需要将下划线命名转换为驼峰命名
     * @param <T>               返回值类型
     * @return 集合
     */
    public static <T> List<T> queryList(String sql, Class<T> clz, Boolean underScoreToCamel) {


        //创建集合用于存放结果数据
        ArrayList<T> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + HOSTNAME + "/" + DATABASE + "?characterEncoding=utf-8&useSSL=false",
                    USERNAME,
                    PASSWORD
            );

            //预编译SQL
            preparedStatement = connection.prepareStatement(sql);

            //执行查询
            resultSet = preparedStatement.executeQuery();

            //获取查询结果中的列信息
            ResultSetMetaData metaData = preparedStatement.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {

                //通过反射的方式获取对象
                T t = clz.newInstance();

                for (int i = 1; i < columnCount + 1; i++) {

                    //获取列名
                    String columnName = metaData.getColumnName(i);
                    if (underScoreToCamel) {
                        columnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                    }
                    //获取值信息
                    Object value = resultSet.getObject(i);
                    if (value != null) {
                        Field field = clz.getDeclaredField(columnName);
                        field.setAccessible(true);
                        field.set(t, value);
                        field.setAccessible(false);
                    }
                    //给对象赋值
                    //BeanUtils.setProperty(t, columnName, value);
                }

                //将对象放入集合
                result.add(t);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //返回结果集
        return result;
    }

    public static void main(String[] args) {
        List<TableProcess_ck> tableProcess_cks = queryList("select * from npm_check_point", TableProcess_ck.class, true);
        tableProcess_cks.forEach(tableProcess_ck -> {
            System.out.println(tableProcess_ck.getAssetId());
        });
    }
}
