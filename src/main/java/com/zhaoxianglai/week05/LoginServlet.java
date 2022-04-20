package com.zhaoxianglai.week05;

import com.zhaoxianglai.dao.UserDao;
import com.zhaoxianglai.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name = "LoginServlet",
        value = "/login"
)
public class LoginServlet extends HttpServlet {
    Connection conn = null;

    @Override
    public void init() throws ServletException {
        super.init();
        conn = (Connection) getServletContext().getAttribute("conn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when click "Login" the method is get()
        //forward to login.jsp
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //write mvc code
        //use model and dao
        UserDao userDao = new UserDao();
        try {
            User user = userDao.findByUsernamePassword(conn,username,password);
            if (user != null){
                String rememberMe = request.getParameter("rememberMe");
                if (rememberMe != null && rememberMe.equals("1")){
                    //want to rememberMe
                    //create 3 cookies
                    Cookie usernameCookie = new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie = new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie = new Cookie("cRememberMe",rememberMe);
                    //set age of cookies
                    usernameCookie.setMaxAge(5);//5 second
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);
                    //add cookies into response
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);
                }

                //week 8 exercise#1:create a session
                HttpSession session = request.getSession();//create a new session if not exist otherwise return existing session
                /*//check session id
                System.out.println("session id-->"+session.getId());*/
                //set time for session
                session.setMaxInactiveInterval(10*60);//for 10 minutes if request not come,tomcat kill session
                //change request(one page) to session - so we can get session attribute in many jsp page
                session.setAttribute("user",user);//set user info into session
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp").forward(request,response);
            }else{
                //invalid
                request.setAttribute("message","Username or Password Error!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}