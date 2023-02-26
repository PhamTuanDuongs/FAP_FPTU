/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import dal.userDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;

/**
 *
 * @author duong
 */
public class loginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String raw_name = req.getParameter("user");
        String raw_pass = req.getParameter("pass");
        userDBContext user = new userDBContext();
        User u = user.get(raw_name, raw_pass);
        if (u != null) {
            req.getSession().setAttribute("user", u);
            resp.getWriter().println("Login successful!");
        } else {
            resp.getWriter().println("Login failed");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
               req.getRequestDispatcher("view/authentication/login.jsp").forward(req, resp);
    }

}
