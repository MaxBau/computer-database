package com.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computerdatabase.services.ComputerService;

/**
 * Servlet implementation class ListComputer
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerService cs = ComputerService.getInstance();
		int limitMin = 0;
		int limitMax = 10;
		String search="";
		String order = "id";
		HttpSession session = request.getSession();	
		
		if (session.getAttribute("sens")==null) session.setAttribute("sens", "DESC");
		if (session.getAttribute("sens").toString().equals("ASC")) {
			session.setAttribute("sens", "DESC");
		} else {
			session.setAttribute("sens", "ASC");
		}
		if (request.getParameter("limitmin")!=null) {
			if (!(session.getAttribute("limitmin").equals(request.getParameter("limitmin")))) {
				session.setAttribute("limitmin",request.getParameter("limitmin"));
			}
		}
		else {
			session.setAttribute("limitmin", limitMin);
		}
		
		if (request.getParameter("limitmax")!=null) {
			if (!(session.getAttribute("limitmax").equals(request.getParameter("limitmax")))) {
				session.setAttribute("limitmax",request.getParameter("limitmax"));
			}
		} else {
			session.setAttribute("limitmax", limitMax);
		}
		
		if (request.getParameter("search")!=null) {
			if (!(session.getAttribute("search").equals(request.getParameter("search")))) {
				session.setAttribute("search",request.getParameter("search"));
			}
		}
		
		if (request.getParameter("order")!=null) {
			if (!(session.getAttribute("order").equals(request.getParameter("order")))) {
				session.setAttribute("order",request.getParameter("order"));
			}
		}
		
		if (session.getAttribute("limitmin") !=null) limitMin = Integer.parseInt(session.getAttribute("limitmin").toString());
		if (session.getAttribute("limitmax") != null) limitMax = Integer.parseInt(session.getAttribute("limitmax").toString());
		if (session.getAttribute("search") != null) search = session.getAttribute("search").toString();
		if (session.getAttribute("order") !=null ) order = (String) session.getAttribute("order").toString();
		
		request.setAttribute("computers", cs.getAllComputers(limitMin, limitMax, search, order,session.getAttribute("sens").toString()));		
		request.setAttribute("computerCount", cs.countComputer());
		if (!(search.equals(""))) request.setAttribute("computerCount", cs.getAllComputers(0, 10000, search, order,session.getAttribute("sens").toString()).size());
		
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerService cs = ComputerService.getInstance();
		request.setAttribute("computers", cs.getAllComputers());
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
	}

}
