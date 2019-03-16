package com.develop101.mavenproject2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.develop101.mavenproject2.dao.DetailsDao;
import com.develop101.mavenproject2.dao.EmployeeDao;
import com.developer101.mavenproject2.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FINE
 */
@WebServlet(name="EmployeeServlet" , urlPatterns = {"/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {

    DetailsDao dao = new DetailsDao();
    Employee emp = new Employee();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String reqMSG = request.getParameter("msg");
            if(reqMSG.equals("GetDetails")){
                String sql = "select FIRST_NAME from EMPLOYEES";
                List<String> names = dao.getDetails(sql);
                String sql2 = "select DEPARTMENT_NAME from DEPARTMENTS";
                List<String> deptNames = dao.getDetails(sql2);
                String sql3 = "select JOB_ID from JOBS";
                List<String> jobTitles = dao.getDetails(sql3);
                String sql4 = "SELECT  DEPARTMENT_NAME from DEPT_DETAILS_VIEW";
                List<String> WorkingDepts = dao.getDetails(sql4);

                Map<String,Object> obj = new HashMap<String,Object>();
                obj.put("ManagerNames",names);
                obj.put("DeptNames",deptNames);
                obj.put("JobTitles",jobTitles);
                obj.put("WorkingDepts",WorkingDepts);
                out.write(new Gson().toJson(obj));
            }else if(reqMSG.equals("SetDetails")){

                insertNewEmployee(request,response);

                out.write("Registered Successfully!!");
            }else if(reqMSG.equals("SelectDetails")){

                EmployeeDao empDao = new EmployeeDao();
                List<Employee> employees = empDao.selectEmployeeDetails();


                out.write(new Gson().toJson(employees));
            }else if(reqMSG.equals("UpdateDetails")){
                ObjectMapper objectMapper = new ObjectMapper();
                //Set pretty printing of json
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

                String JsonArray = request.getParameter("JsonRequestData");

                TypeReference<Employee> mapType = new TypeReference<Employee>() {};
                Employee emp = objectMapper.readValue(JsonArray, mapType);

                EmployeeDao dao = new EmployeeDao();
                dao.UpdateEmployeeDetails(emp);

            }
            else if(reqMSG.equals("DeleteEmployee")){

                String Emp_id = request.getParameter("JsonRequestData");
                System.out.println(Emp_id);
                new EmployeeDao().DeleteEmployee(Integer.parseInt(Emp_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertNewEmployee(HttpServletRequest request, HttpServletResponse response) {
        Employee emp = new Employee();
        EmployeeDao empDao = new EmployeeDao();
        emp.setEmp_id(request.getParameter("empID"));
        emp.setFname(request.getParameter("fname"));
        emp.setLname(request.getParameter("lname"));
        emp.setCom_per(request.getParameter("comper"));
        emp.setDept_id(request.getParameter("deptname"));
        emp.setEmail(request.getParameter("email"));
        emp.setHire(request.getParameter("hire"));
        emp.setJob_id(request.getParameter("jobtitle"));
        emp.setPhone(request.getParameter("phone"));
        emp.setSalary(request.getParameter("salary"));

        empDao.insertEmployeeDetails(emp);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
