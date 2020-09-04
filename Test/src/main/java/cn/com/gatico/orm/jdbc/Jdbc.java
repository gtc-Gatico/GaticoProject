package cn.com.gatico.orm.jdbc;


import java.sql.*;

public class Jdbc implements BaseJdbc {

    String url = "";
    String user = "";
    String driver = "";
    String password = "";

    public Connection conn = null;

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                openConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    Object executeSql(String sql) {
        PreparedStatement ptmt = null; //预编译SQL，减少sql执行
        ResultSet resultSet = null;
        try {
            ptmt = conn.prepareStatement(sql);
            //执行
            resultSet = ptmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        //传参
//        ptmt.setString(1, g.getUser_name());
//        ptmt.setInt(2, g.getSex());
//        ptmt.setInt(3, g.getAge());
//        ptmt.setDate(4, new Date(g.getBirthday().getTime()));
//        ptmt.setString(5, g.getEmail());
//        ptmt.setString(6, g.getMobile());
//        ptmt.setString(7, g.getCreate_user());
//        ptmt.setString(8, g.getUpdate_user());
//        ptmt.setInt(9, g.getIsDel());

        return resultSet;
    }

    public void openConnection() throws SQLException {

        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(getUrl(), getUser(), getPassword());
        //sql
//        String sql = "select * from user_files ";
//        //预编译
//        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行
//
////        //传参
////        ptmt.setString(1, g.getUser_name());
////        ptmt.setInt(2, g.getSex());
////        ptmt.setInt(3, g.getAge());
////        ptmt.setDate(4, new Date(g.getBirthday().getTime()));
////        ptmt.setString(5, g.getEmail());
////        ptmt.setString(6, g.getMobile());
////        ptmt.setString(7, g.getCreate_user());
////        ptmt.setString(8, g.getUpdate_user());
////        ptmt.setInt(9, g.getIsDel());
//
//        //执行
//        ResultSet resultSet = ptmt.executeQuery();
//        while (resultSet.next()){
//            String courseName = resultSet.getString("name");
//            System.out.println(courseName);
//        }


    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getDriver() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
