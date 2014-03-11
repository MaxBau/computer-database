package springapp.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springapp.domain.Computer;
import springapp.domain.ComputerDTO;
import springapp.service.CompanyService;
import springapp.service.ComputerService;

@Controller
@RequestMapping("/computer")
public class ComputerController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	ComputerService computerService;
	CompanyService companyService;
	FormValidator validator;

	@Autowired
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	@Autowired
	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}
	
	@Autowired
	public void setValidator(FormValidator validator) {
		this.validator = validator;
	}
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	    binder.addValidators(validator);  
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
	public ModelAndView addComputerForm(Model m) {
		ModelAndView myModel = new ModelAndView();
		myModel.setViewName("addComputer");
		myModel.addObject("companyList", companyService.getAllCompany());

		m.addAttribute("computerDto", new ComputerDTO());
		return myModel;
	}
	
	@RequestMapping(value="/addComputer",method = RequestMethod.POST)
	public ModelAndView addComputer(@Valid ComputerDTO computerDto,BindingResult result,Model m,final RedirectAttributes redirectAttributes) {
		ModelAndView myModel = new ModelAndView();
		
		if (result.hasErrors()) {
			myModel.setViewName("addComputer");
			myModel.addObject("companyList", companyService.getAllCompany());
			m.addAttribute("computerDto", new ComputerDTO());
			myModel.addObject("message", result.getAllErrors());

		} else {
			logger.info("Adding computer");
			
			ComputerDTO cDto = new ComputerDTO();
			Computer computer = cDto.fromDto(computerDto);
	
			computerService.addComputer(computer.getName(), computer.getIntroduced().toString(), computer.getDiscontinued().toString(),computer.getCompany().getId());
			
			m.addAttribute("computerDto", new ComputerDTO());
			myModel.setViewName("addComputer");
			myModel.addObject("companyList", companyService.getAllCompany());
			myModel.addObject("message", "Ajout éfféctué");
			
		}
		return myModel;
	}
	
	@RequestMapping(value="/editComputerForm",method = RequestMethod.GET)
	public ModelAndView editComputerForm(@Valid ComputerDTO computerDto,BindingResult result,Model m,final RedirectAttributes redirectAttributes) {
		ModelAndView myModel = new ModelAndView();
		myModel.setViewName("editComputer");
		myModel.addObject("computer", computerService.getComputerById(computerDto.getId()));
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
