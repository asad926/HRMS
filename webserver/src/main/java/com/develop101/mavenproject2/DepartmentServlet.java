package com.develop101.mavenproject2;

import com.develop101.mavenproject2.dao.DepartmentDao;
import com.developer101.mavenproject2.model.Departments;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "DepartmentServlet", urlPatterns = {"/DepartmentServlet"})
public class DepartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    Departments dept1  = new Departments();
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {

        DepartmentDao deptDao = new DepartmentDao();
        String param = request.getParameter("msg");
        if(param.equals("ShowDeptDetails")) {
            String deptName = request.getParameter("JsonRequestData");
            dept1 = deptDao.getDeptDetails(deptName);

            response.getWriter().write(new Gson().toJson(dept1));

        }else if(param.equals("WorkingDetails")) {
            List<Departments> dept2;
            String deptName = request.getParameter("JsonRequestData");
            dept2 = deptDao.getWorkingEmpDetails(deptName);

            response.getWriter().write(new Gson().toJson(dept2));

        }
    }
}
