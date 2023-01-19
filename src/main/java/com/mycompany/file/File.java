/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.file;

import java.util.Arrays;

/**
 *
 * @author Student
 */
public class File {

    public static void main(String[] args) {
        var instance = PostgreSQLClass.getInstance();
        if (instance.connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")) System.out.println("Connected");
        String table = "Products";
//        String[]cols = {"col1 INT PK","col2 TEXT","col3 INT NOT NULL","col4 TEXT"};
//        instance.create(table, cols);
//        String[] cols1 = {"2,'sssssss',3434,'ffffffff'"};
//        instance.insert(table, "col1,col2,col3,col4", cols1);
        String[] arr = {"col1","col2","col3","col4"};
        String[]vals={"aaa","000","'44444'"};
//        instance.updateColumns(table, arr, vals, "col1 = 1");
//        instance.insert(table, "col1,col2,col3,col4", cols1);
//        instance.updateColumns("my_table",cols,cols1,"id = 6");
//        instance.deleteRows(table, "col3 = 7");
//        System.out.println(Arrays.toString(arr));

//        instance.addColumn(table, "name text");
//        instance.deleteRows(table, "col1=2");
        var list = instance.select(table, arr);
        for (var a: list)
            System.out.println(Arrays.toString(a));
        instance.delete(table);
//        instance.updateRows(table, "col2", "col1", new String[]{"1","2"}, vals);
    }
}
