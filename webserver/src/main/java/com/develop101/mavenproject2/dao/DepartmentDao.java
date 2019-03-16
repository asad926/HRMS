package com.develop101.mavenproject2.dao;

import com.develop101.mavenproject2.MainConnection;
import com.developer101.mavenproject2.model.Departments;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    Departments dept = new Departments();
    public Departments getDeptDetails(String deptname) throws SQLException {
        Connection conn = MainConnection.connection();
        CallableStatement statement = conn.prepareCall("call DEPARTMENT_DETAILS(?,?,?,?,?,?,?,?,?)");
        statement.setString(1,deptname);
        statement.registerOutParameter(2,Types.VARCHAR);
        statement.registerOutParameter(3,Types.VARCHAR);
        statement.registerOutParameter(4,Types.VARCHAR);
        statement.registerOutParameter(5,Types.VARCHAR);
        statement.registerOutParameter(6,Types.VARCHAR);
        statement.registerOutParameter(7,Types.VARCHAR);
        statement.registerOutParameter(8,Types.VARCHAR);
        statement.registerOutParameter(9,Types.VARCHAR);

                   statement.execute();
          ResultSet rs =  statement.getResultSet();

            dept = new Departments();
                    dept.setDept_name(statement.getString(2));
                    dept.setMng_name(statement.getString(3));
            dept.setStreet( statement.getString(4));
            dept.setPostal( statement.getString(5));
            dept.setCity( statement.getString(6));
            dept.setState( statement.getString(7));
            dept.setCountry(  statement.getString(8));
            dept.setRegion(  statement.getString(9));


        System.out.println(dept.getCountry());
        return dept;
    }

    public List<Departments> getWorkingEmpDetails(String deptName) throws SQLException {

        Connection conn = MainConnection.connection();
         String sql = "SELECT * FROM DEPT_WORKING_EMPLOYEES WHERE DEPARTMENT_NAME = ?";
         PreparedStatement pst = conn.prepareStatement(sql);
         pst.setString(1,deptName);
        ResultSet rs = pst.executeQuery();
        List<Departments> list = new ArrayList<Departments>();
        while (rs.next()){
           Departments dept = new Departments(
                               rs.getString(1),
                   rs.getString(2),rs.getString(3),rs.getString(4)
                   ,rs.getString(5),rs.getString(6), rs.getString(7), ""
           );
           list.add(dept);
        }

        return list;
    }
}
