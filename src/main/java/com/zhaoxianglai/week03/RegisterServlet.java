package com.zhaoxianglai.week03;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        urlPatterns = "/register",
        loadOnStartup = 1
)
public class RegisterServlet extends HttpServlet {
    Connection conn = null;

    @Override
    public void init() throws ServletException {
        super.init();
        conn = (Connection) getServletContext().getAttribute("conn");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String sql1 = "INSERT INTO Users VALUES(?,?,?,?,?,?)";
        String sql2 = "SELECT * FROM Users";
        int id;
        try {
            PreparedStatement ps = conn.prepareStatement(sql1);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(sql2);
            rs.last();
            int count = rs.getRow();
            //System.out.println(count);
            id = ++count;
            //System.out.println(id);
            rs.beforeFirst();

            ps.setInt(1,id);
            ps.setString(2,username);
            ps.setString(3,password);
            ps.setString(4,email);
            ps.setString(5,gender);
            ps.setDate(6, Date.valueOf(birthdate));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.close();


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
