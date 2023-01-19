/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.file;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Student
 */
public interface ISQL extends AutoCloseable{
    boolean connect(String url, String username, String password);
    
    boolean create(String tableName, String[] columns);
    
    int insert(String tableName, String columns, String[] values);
    
    int updateRows(String tableName, String column, String conditionColumn, String[] conditions, String[] values);
    
    boolean updateColumns(String tableName, String[] columns, String[] values, String condition);
    
    List<String[]> select(String tableName, String[] columns);
    
    boolean delete(String tableName);
    
    int deleteRows(String tableName, String condition);
    
    boolean addColumn(String tableName, String column); //column = "name TEXT NOT NULL"
    
    //close();
}
