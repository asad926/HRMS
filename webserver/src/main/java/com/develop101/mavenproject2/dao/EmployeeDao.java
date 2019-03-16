/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.develop101.mavenproject2.dao;

import com.develop101.mavenproject2.MainConnection;
import com.developer101.mavenproject2.model.Employee;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FINE
 */
public class EmployeeDao {
    public void insertEmployeeDetails(Employee emp){
      try {
                   Connection conn = MainConnection.connection();
          CallableStatement statement = conn.prepareCall("call INSERT_INTO_EMPLOYEES(?,?,?,?,?,?,?,?,?,?)");

                       statement.setInt(1,Integer.parseInt(emp.getEmp_id()));
                       statement.setString(2,emp.getFname());
                       statement.setString(3,emp.getLname());
                       statement.setString(4,emp.getEmail());
                       statement.setString(5,emp.getPhone());
                       statement.setString(6,emp.getHire());
                       statement.setString(7,emp.getJob_id());
                       statement.setInt(8,Integer.parseInt(emp.getSalary()));
                       statement.setDouble(9,Double.parseDouble(emp.getCom_per()));

                       statement.setString(10,emp.getDept_id());


          statement.executeUpdate();
          System.out.println("New Employee Registered!!!");
                   
         } catch (SQLException ex) {
             Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
         }
           
    }


    public List<Employee> selectEmployeeDetails() {
        List<Employee> emps = new ArrayList<>();
        try {
            Connection conn = MainConnection.connection();
            String sql = "SELECT * FROM EMP_DETAIL_VIEW";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();


            while(rs.next()){

                Employee emp = new Employee(rs.getString(1),
                                             rs.getString(2),
                                              rs.getString(3),
                                                rs.getString(4),null,
                                                rs.getDate(5).toString(),
                                                 rs.getString(6),
                                                  rs.getString(9),
                                                   rs.getString(7),null,
                                                    rs.getString(8)
                        );

                emps.add(emp);

            }
            return emps;
        }catch (SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpdateEmployeeDetails(Employee emp){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MMM-yyyy");
LocalDate localDate = LocalDate.parse(emp.getHire(),formatter);

        try {
            Connection conn = MainConnection.connection();
            CallableStatement statement = conn.prepareCall("call update_employee_details(?,?,?,?,?,?,?,?,?)");

            statement.setInt(1, Integer.parseInt(emp.getEmp_id()));
            statement.setString(2, emp.getFname());
            statement.setString(3, emp.getLname());
            statement.setString(4, emp.getEmail());
            statement.setString(5, localDate.format(format));
            statement.setString(6, emp.getJob_id());
            statement.setInt(7, Integer.parseInt(emp.getSalary()));
            statement.setString(8, emp.getMng_id());
            statement.setString(9, emp.getDept_id());


            statement.executeUpdate();
            System.out.println("Employee Updated!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public  void DeleteEmployee(int id) throws SQLException {

        Connection conn = MainConnection.connection();
        System.out.println("ID "+id);
        CallableStatement statement = conn.prepareCall("call delete_empoloyee(?)");
        statement.setInt(1,id);

        statement.executeUpdate();

    }
}
