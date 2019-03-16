package com.develop101.mavenproject2;

 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.develop101.mavenproject2.dao.LoginDao;
import com.developer101.mavenproject2.model.Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FINE
 */
@WebServlet(name = "Login" , urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    LoginDao dao = new LoginDao();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String msg = null;
                   msg = request.getParameter("msg");
            System.out.println(msg);
          if(msg.equals("Register")){

              RegisterNewUser(request,response);
          }else {
              loginUser(request, response);
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void RegisterNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String userName = request.getParameter("user");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        Login login = new Login();
        login.setUsername(userName);
        login.setPassword(pass);
        login.setEmail(email);

        dao.registerUser(login);
        response.getWriter().write("Registered");
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("user");
        String password = request.getParameter("pass");
        List<Login> login = dao.getLogin();
        int i = 0;
        while (login.get(i) != null) {
            if (userName.equals(login.get(i).getUsername()) && password.equals(login.get(i).getPassword())) {
                response.getWriter().write("Login");
                break;
            }
            i++;
            // out.write("Not Login..");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
