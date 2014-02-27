package com.computerdatabase.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerdatabase.services.ComputerService;
import com.computerdatabase.taglibs.Paginator;

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
		Paginator.setItemsPerPage(100);	
		String action = "initialize";
		if (request.getParameter("action") != null ) action = request.getParameter("action");
		
		if (action.equals("previousPage")) {
			request.setAttribute("computers", Paginator.previousPage());
		} else  {
			request.setAttribute("computers", Paginator.nextPage());
		}
		
		request.setAttribute("computerCount", cs.countComputer());
		
		
		/*ComputerService cs = ComputerService.getInstance();
		request.setAttribute("computers", cs.getAllComputers());*/
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ComputerService cs = ComputerService.getInstance();
		request.setAttribute("computers", cs.getAllComputers());
		RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
		rd.forward(request, response);
	}

}
