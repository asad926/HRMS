/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.develop101.mavenproject2.dao;

import com.develop101.mavenproject2.MainConnection;
import com.developer101.mavenproject2.model.Login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FINE
 */
public class LoginDao {

    public List<Login> getLogin(){
     String sql = "select * from LOGIN";
        List<Login> logins = new ArrayList<Login>();
      try {
                   Connection conn = MainConnection.connection();
                     PreparedStatement pst = conn.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery();
                 while(rs.next()){
                     Login login = new Login();
             login.setUsername(rs.getString("user_name"));
              login.setPassword(rs.getString("pass"));
              login.setEmail(rs.getString("email"));
              logins.add(login);
                 }
                 conn.close();
         } catch (SQLException ex) {
             Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
         }
                
    return logins;
    }
    public void registerUser(Login login) throws SQLException {
        Connection conn = MainConnection.connection();
      //  String sql = "INSERT into LOGIN values(?,?,?)";
      //  PreparedStatement pst = conn.prepareStatement(sql);
        CallableStatement statement = conn.prepareCall("call REGISTERUSER(?,?,?)");

        statement.setString(1,login.getUsername());
        statement.setString(2,login.getPassword());
        statement.setString(3,login.getEmail());

        statement.execute();
    }

}
