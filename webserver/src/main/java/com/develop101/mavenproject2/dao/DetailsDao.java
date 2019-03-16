package com.develop101.mavenproject2.dao;

import com.develop101.mavenproject2.MainConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DetailsDao {


    public List<String> getDetails(String sql){
        List<String> emps = new ArrayList<>();
        try {
            Connection conn = MainConnection.connection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){

                emps.add(rs.getString(1));

            }
            return emps;
        }catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
