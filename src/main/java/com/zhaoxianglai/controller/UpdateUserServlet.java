package com.zhaoxianglai.controller;

import com.zhaoxianglai.dao.UserDao;
import com.zhaoxianglai.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "UpdateUserServlet" ,value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con=null;
    public void init() throws SecurityException, ServletException {
        super.init();
        con=(Connection) getServletContext().getAttribute("con");

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/updateUser.jsp").forward(request,response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(1);
        String id=request.getParameter("id");
        String username=request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthDate = request.getParameter("birthDate");
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        //user.setID(Integer.parseInt(id));
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setGender(gender);
        user.setBirthdate(Date.valueOf(birthDate));
        UserDao userDao = new UserDao();
        try{
            int a =  userDao.updateUser(con,user);
            System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("accountDetails").forward(request,response);
    }
}