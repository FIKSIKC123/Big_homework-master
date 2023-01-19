/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */
public class PostgreSQLClass implements ISQL{
    private static PostgreSQLClass instance;    
    private Connection con;
    private Statement stm;
    
    
    private PostgreSQLClass(){}
    
    @Override
    public boolean connect(String url, String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
            stm = con.createStatement();
        } catch (SQLException ex) {
            con = null;
            stm = null;
            return false;
        }
        return true;
    }

    @Override
    public boolean create(String tableName, String[] columns){
        String sql="CREATE TABLE " + tableName + '(';
         String pk="PRIMARY KEY( ";
         for(String col: columns){
         if(col.contains("PK")){
             pk+=col.substring(0,col.indexOf(" "))+',';
             sql+=col.substring(0,col.length()-3)+',';
        
         }
         else sql+=col+',';
         }
         pk=pk.substring(0,pk.length()-1);
         pk+=')';
         sql+=pk;
         sql+=");";
         System.out.println(sql);
        try {
            stm.execute(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
}


    @Override
    public int insert(String tableName, String columns, String[] values){
        String sql = "INSERT INTO " + tableName +'('+columns+')'+"VALUES";
         for(String str:values)
             sql+='(' +str+"),";
         sql=sql.substring(0,sql.length()-1);
         sql+=";";
         System.out.println(sql);
        try {
            return stm.executeUpdate(sql);
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public int updateRows(String tableName, String column, String conditionColumn, String[] conditions, String[] values) {
        String sql="update " + tableName+" set "+column + "=CASE "+ conditionColumn;
        int min=Math.min(conditions.length,values.length );
        for(int i=0;i<min;++i){
            sql+=" WHEN "+"'" +conditions[i]+"'THEN'"+values[i]+"'";
        }
      sql+=" ELSE "+column+" END WHERE "+ conditionColumn+" IN(";
      
      for(int i=0;i<min;++i)sql+="'"+conditions[i]+"',";
      
      sql=sql.substring(0,sql.length()-1);
      sql+=");";System.out.println(sql);
      try {
            return stm.executeUpdate(sql);
        } catch (SQLException ex) {
            return 0;
        }
        
    }
                

    @Override
    public boolean updateColumns(String tableName, String[] columns, String[] values, String condition) {
      
      String sql="UPDATE " + tableName+ " SET ";
      int min=Math.min(columns.length,values.length );
        for(int i = 0; i < min; ++i){
            sql+= columns[i] + " = " + values[i] + ",";
            System.out.println(columns[i]);
            
        }
     sql=sql.substring(0,sql.length()-1);
     sql+=" WHERE " + condition+";";
     System.out.println(sql);
     try {
            stm.execute(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public List<String[]> select(String tableName, String[] columns) {
        String sql="SELECT " ;
        for(int i=0; i<columns.length;i++)
            sql+=columns[i]+",";
        sql=sql.substring(0,sql.length()-1);
        sql+=" FROM " + tableName+";";
        System.out.println(sql);
        try {
            ResultSet resultSet = stm.executeQuery(sql);
            List<String[]> res = new LinkedList<>();
           while(resultSet.next()){
               String[] arr = new String[columns.length];
                for (var i = 0; i < columns.length; ++i){
                   arr[i]=resultSet.getString(columns[i]);
               }
               res.add(arr);
           }
           return res;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public boolean delete(String tableName) {
        String sql= "DROP TABLE "+ tableName+';';
        try {
            stm.execute(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public int deleteRows(String tableName, String condition) {
        String sql= " DELETE FROM " + tableName + " WHERE " + condition +";";
        try {
            return stm.executeUpdate(sql);
        } catch (SQLException ex) {
            return 0;
        }
    }

    @Override
    public boolean addColumn(String tableName, String column) {
       String sql=" ALTER TABLE "+tableName+ " ADD COLUMN "+column +";";
       try {
            stm.execute(sql);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public void close() {
        try {
            con.close();
            stm.close();
        } catch (SQLException ex) {
        }
    }
    
    public static PostgreSQLClass getInstance(){
        if (instance == null) instance = new PostgreSQLClass();
        return instance;
    }
    
}

