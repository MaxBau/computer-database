package springapp.web;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Computer;
import springapp.service.CompanyService;
import springapp.service.ComputerService;

@Controller
@RequestMapping("/computer")
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
	public ModelAndView addComputer(@RequestParam(value="name",required=false) String name,
									@RequestParam(value="introducedDate",required=false) String introducedDate,
									@RequestParam(value = "discontinuedDate",required=false) String discontinuedDate,
									@RequestParam(value="company",required=false) long company) {
		logger.info("Adding computer");
		ModelAndView myModel = new ModelAndView();
		computerService.addComputer(name, introducedDate, discontinuedDate,company);
		
		myModel.setViewName("addComputer");
		myModel.addObject("companyList", companyService.getAllCompany());
		myModel.addObject("message", "Ajout éfféctué");
		return myModel;
	}
	
	@RequestMapping(value="/editComputerForm",method = RequestMethod.GET)
	public ModelAndView editComputerForm(@RequestParam(value="id",required=false) long computerId) {
		ModelAndView myModel = new ModelAndView();
		myModel.setViewName("editComputer");
		myModel.addObject("computer", computerService.getComputerById(computerId));
		myModel.addObject("companyList", companyService.getAllCompany());
		return myModel;
	}
	
	@RequestMapping(value="/editComputer",method = RequestMethod.POST)
	public ModelAndView editComputer(@RequestParam(required=false) long id,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String introducedDate,
			@RequestParam(required=false) String discontinuedDate,
			@RequestParam(required=false) long company) {
		logger.info("Editing computer");
		ModelAndView myModel = new ModelAndView();

		Computer computer = computerService.create(id,name, introducedDate, discontinuedDate, companyService.find(company));
		computerService.update(computer);
		
		myModel.setViewName("editComputer");
		myModel.addObject("computer", computer);
		myModel.addObject("companyList", companyService.getAllCompany());
		myModel.addObject("message", "Modification éfféctuée");
		
		return myModel;
	}
	
	@RequestMapping(value="deleteComputer", method=RequestMethod.GET)
	public ModelAndView deleteComputer(@RequestParam long id) {
		logger.info("deleting computer");
		
		ModelAndView myModel = new ModelAndView();
		
		computerService.deleteComputer(id);
		myModel.setViewName("dashboard");
		myModel.addObject("computerList", computerService.getAllComputers());
		return myModel;
	}
	
	@RequestMapping(value="search",method=RequestMethod.GET)
	public ModelAndView searchComputer(@RequestParam String search) {
		logger.info("Searching for computer");
		
		ModelAndView myModel = new ModelAndView();
		
		myModel.setViewName("dashboard");
		myModel.addObject("computerList", computerService.findAll(search));
		return myModel;
	}
}
