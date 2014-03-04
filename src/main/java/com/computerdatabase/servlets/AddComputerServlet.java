package com.computerdatabase.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerdatabase.classes.Company;
import com.computerdatabase.classes.Computer;
import com.computerdatabase.services.CompanyService;
import com.computerdatabase.services.ComputerService;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CompanyService cs = CompanyService.getInstance();
		request.setAttribute("companies", cs.getAllCompany());
		RequestDispatcher rd = request.getRequestDispatcher("/addComputer.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = (String) request.getParameter("name");
		String introduced = request.getParameter("introducedDate");
		String discontinued = request.getParameter("discontinuedDate");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date introducedSubmit = new Date();
		Date discontinuedSubmit = new Date();
		try {
			//TODO Conversions dates
			introducedSubmit = sdf.parse(introduced);
			discontinuedSubmit = sdf.parse(discontinued);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		long companyId = Long.parseLong(request.getParameter("company"));
		
		Company company = new Company();
		company.setId(companyId);
		Computer computer = new Computer(name, introducedSubmit, discontinuedSubmit, company);
		ComputerService cd = ComputerService.getInstance();
		cd.createComputer(computer);
		
		request.setAttribute("message", "Ajout éffectué");
		RequestDispatcher rd = request.getRequestDispatcher("addComputer.jsp");
		rd.forward(request, response);
	}

}
