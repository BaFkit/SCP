package org.example.servlets;

import org.example.connection.ConnectionDB;

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
    private PreparedStatement ps;

    @Override
    public void init() throws ServletException {
        ConnectionDB connectionDB = new ConnectionDB();
        connection = (Connection) connectionDB.getConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ps = connection.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                resp.getOutputStream().println("<p>"+ rs.getString(2) +"</p>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
