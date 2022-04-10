package org.examle.servlets;

import org.examle.Product;
import org.examle.Products;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/ServletGetProductList")
public class ServletGetProductList extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Products products = new Products();
        List<Product> productsList = products.selectAll();
        productsList.forEach(e -> {
            try {
                resp.getWriter().println("<p>" + e.getTitle() + "</p>");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
