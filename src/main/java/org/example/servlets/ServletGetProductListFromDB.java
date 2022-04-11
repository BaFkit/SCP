package org.example.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/ServletGetProductListFromDB")
public class ServletGetProductListFromDB extends HttpServlet {

    private Connection connection;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        connection = (Connection) context.getAttribute("jdbcConnection");
        if (connection == null) {
            throw new ServletException("No JDBC connection in Servlet Context");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                String tableName = rs.getString(2);
                resp.getWriter().println("<p> " + tableName + "</p>");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

