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
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request,response);
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

        String sql = "INSERT INTO Users(username,password,email,gender,birthdate) VALUES(?,?,?,?,?)";
        try {
            //这里不用插id是因为在数据库的表中设置为了标识增量（自增1，第一行默认插入1）
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,email);
            ps.setString(4,gender);
            ps.setDate(5, Date.valueOf(birthdate));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*out.println("       </table>");
        out.println("   </body>");
        out.println("</html>");*/
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
