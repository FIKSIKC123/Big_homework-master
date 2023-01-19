
import com.mycompany.file.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Student
 */
public class FileStatus {
    File obj;
    String nameF;
    String pathF;
    long lenghtF;
    long lastM;
    boolean exist;
    FileStatus(String pathFile){
       obj= new File(pathFile);
       
    }
    

