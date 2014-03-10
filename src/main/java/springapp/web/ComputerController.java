package springapp.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.service.CompanyService;
import springapp.service.ComputerService;

@Controller
@RequestMapping("/")
public class ComputerController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	ComputerService computerService;
	CompanyService companyService;

	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	@Autowired
	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

	@RequestMapping("/dashboard")
	public ModelAndView listComputers() {
		
		logger.info("Returning dashboard view");
		ModelAndView myModel = new ModelAndView();
	    myModel.setViewName("dashboard");
	    myModel.addObject("computerList", computerService.getAllComputers());
	    return myModel;
	}
	
	@RequestMapping("/addComputerForm")
	public ModelAndView addComputerForm() {
		ModelAndView myModel = new ModelAndView();
		myModel.setViewName("addComputer");
		myModel.addObject("companyList", companyService.getAllCompany());
		return myModel;
	}

	@RequestMapping(value="/addComputer",method = RequestMethod.POST)
	public ModelAndView addComputer(@RequestParam("name") String name,@RequestParam("introducedDate") Date introducedDate,
			@RequestParam("discontinuedDate") Date discontinuedDate,@RequestParam("company") long companyId) {
		logger.info("Adding computer");
		ModelAndView myModel = new ModelAndView();
		computerService.addComputer(name, introducedDate, discontinuedDate, companyId);
		
		myModel.setViewName("addComputer");
		myModel.addObject("companyList", companyService.getAllCompany());
		myModel.addObject("message", "Ajout éfféctué");
		return myModel;
	}
}
