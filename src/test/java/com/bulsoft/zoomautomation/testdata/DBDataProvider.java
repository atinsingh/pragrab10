package com.bulsoft.zoomautomation.testdata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDataProvider  {

    private static Connection con;
    public static void conntectDb(){
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            // Creating Action Driver
            DriverManager.registerDriver(driver);
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gateway","root","");
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static List<String> doQuery(String sql){
        List<String> data = new ArrayList<>();
        if(con==null){
            conntectDb();
        }
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                int cols = metaData.getColumnCount();
                for(int i = 1; i<cols;i++){
                    System.out.println(metaData.getColumnLabel(i));
                    data.add(resultSet.getString(i));
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return data;
    }
}
