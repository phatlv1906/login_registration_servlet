package com.regis.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.regis.model.User;
import com.regis.utils.userDAO;

@WebServlet("/regisServlet")
public class regisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public regisServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		try {
			String name = request.getParameter("name");
			String username = request.getParameter("uname");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			String repass = request.getParameter("re_pass");
			String contact = request.getParameter("contact");
			
			
			if(!pass.equals(repass)) {
				response.sendError(1);
			}else {
				User user = new User();
				user.setName(name);
				user.setUname(username);
				user.setEmail(email);
				user.setPass(pass);
				user.setPhone(contact);
				dispatcher = request.getRequestDispatcher("registration.jsp");
				userDAO dao = new userDAO();
				dao.saveUser(user, request);
				
				dispatcher.forward(request, response);
				PrintWriter out = response.getWriter();
				out.print("<h1>Insert user" + username + "sucessfully</h1>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
