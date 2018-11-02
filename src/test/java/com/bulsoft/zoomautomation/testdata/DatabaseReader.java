package com.bulsoft.zoomautomation.testdata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReader {
    private static Connection connection;

    private DatabaseReader(){
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agreementdb","root","");
        }catch (Exception  ex){
            ex.printStackTrace();
        }
    }



    public static List<Object[]> queryDataBase(String sql){

        List<Object[]> dataSet = new ArrayList<>();

        if(connection == null){
            new DatabaseReader();
        }
        try{
            Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                ResultSetMetaData metaData = resultSet.getMetaData();
                int totalCols = metaData.getColumnCount();
                List<Object> colData = new ArrayList<>();
                for(int i =1; i<totalCols; i++){
                    colData.add(resultSet.getString(i));
                }
                dataSet.add(colData.toArray());
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return dataSet;
    }
}
