/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.develop101.mavenproject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FINE
 */
public class MainConnection {
    
    public static Connection connection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                             "jdbc:oracle:thin:@localhost:1521/pdborcl",
                             "HR", "hr");
                     return conn;
        }catch( SQLException e) {
                     e.printStackTrace();
                     System.out.println("Not Connected!");
                 } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
        
    }
}