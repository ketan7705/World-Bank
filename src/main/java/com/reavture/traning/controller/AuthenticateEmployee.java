package com.reavture.traning.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pms.service.CustomerService;
import com.revature.pms.service.CustomerServiceImpl;
import com.revature.pms.service.EmployeeService;
import com.revature.pms.service.EmployeeServiceImpl;


/**
 * Servlet implementation class AuthenticateUser
 */
public class AuthenticateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticateEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String employeePassword = request.getParameter("employeePassword");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		out.println("<html><bod>");
		EmployeeService employeeService = new EmployeeServiceImpl();
		if (employeeService.validateUser(employeeId, employeePassword)) {

			HttpSession session = request.getSession();
			session.setAttribute("EmployeeId", employeeId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeHome.jsp");

			dispatcher.forward(request, response);
			//

		} else {
			out.println("<h2>You have logged in at : " + new java.util.Date());
			out.println("Sorry customerid or password incorrect " + employeeId);
			out.println("<br/><br/><a href=Home.html>Try Again</a>");

		}
		out.println("</body></html>");	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
}
