package com.zhaoxianglai.week05;

import com.zhaoxianglai.dao.UserDao;
import com.zhaoxianglai.model.User;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    Connection con = null;
    public void init() throws SecurityException, ServletException {
        super.init();

        //TODO 1: GET 4 CONTEXT PARAM - DRIVER , URL,USERNAME , PASSWORD
        /*ServletContext context = getServletContext();
        String driver = context.getInitParameter("driver");
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");*/


        //TODO 2: GET JDBC connection
        /*try {
            Class.forName(driver);
            con= DriverManager.getConnection(url,username,password);
            System.out.println("Connection --> in JDBCDemoServlet"+con);//just print for test
            //one connection -
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/
        con = (Connection) getServletContext().getAttribute("con");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("login serlvet doGet");
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login servlet dopost");
        //PrintWriter writer = response.getWriter();

        //TODO 3: GET REQUEST PARAMETER - USERNAME AND PASSEORD from login
        String username = request.getParameter("username");//name of input type
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);


        UserDao userDao=new UserDao();
        try {
            User user= userDao.findByUsernamePassword(con,username,password);
            System.out.println(user);
            if(user!=null){
                String rememberMe=request.getParameter("rememberMe");
                if (rememberMe!=null && rememberMe.equals("1")){
                    Cookie usernameCookie =new Cookie("cUsername",user.getUsername());
                    Cookie passwordCookie =new Cookie("cPassword",user.getPassword());
                    Cookie rememberMeCookie =new Cookie("cRememberMe",rememberMe);

                    usernameCookie.setMaxAge(5);
                    passwordCookie.setMaxAge(5);
                    rememberMeCookie.setMaxAge(5);

                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    response.addCookie(rememberMeCookie);

                }

                HttpSession session = request.getSession();
                System.out.println("session id -->"+session.getId());
                session.setMaxInactiveInterval(3600);

                session.setAttribute("user",user);
                request.getRequestDispatcher("WEB-INF/views/userList.jsp").forward(request,response);
            }else {
                request.setAttribute("message","Username or Password Eorror!!!");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // TODO 4:VALIDATE USER - SELECT * FROM USERTABLE WHERE USERNAME='XXX' AND PASSWORD='YYY'
       /* try {
           // String sql = "Select * from usertable where username=? and password=? ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            //5
            if(resultSet.next()){
                //writer.println("<br/> <h1>Login Success!!!</h1><br/>");
                //writer.println("<h1> Welcome "+username+" </h1>");
                request.setAttribute("id",resultSet.getInt("id"));
                request.setAttribute("username",resultSet.getString("username"));
                request.setAttribute("password",resultSet.getString("password"));
                request.setAttribute("email",resultSet.getString("email"));
                request.setAttribute("gender",resultSet.getString("gender"));
                request.setAttribute("birth date",resultSet.getString("birthDate"));
                request.getRequestDispatcher("userList.jsp").forward(request,response);
            }else {
                //writer.println("<h1>Username or Password Error!!!</h1>");
            //writer.close();
                request.setAttribute("message","Username or Password Eorror!!!");
                request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }*/
    } public void destroy(){
        super.destroy();
        //close connection here - when stop tomcat
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}