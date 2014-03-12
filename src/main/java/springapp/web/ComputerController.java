package springapp.web;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import springapp.domain.Computer;
import springapp.domain.ComputerDTO;
import springapp.service.CompanyService;
import springapp.service.ComputerService;

@Controller
@RequestMapping("/computer")
public class ComputerController {
	private static String sens="ASC";
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	@Autowired
	FormValidator validator;

//	@InitBinder
//	private void initBinder(WebDataBinder binder) {  
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	    sdf.setLenient(true);
//	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//	    binder.addValidators(validator);  
//	} 
	
	@RequestMapping(value="/dashboard",method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listComputers(@RequestParam(value="search",defaultValue="") String search,
										@RequestParam(value="order",defaultValue="computer.name") String order) {
		
		if (sens.equals("ASC")) {
			sens="DESC";
		} else {
			sens="ASC";
		}
		logger.info("Returning dashboard view");
		ModelAndView myModel = new ModelAndView();
	    myModel.setViewName("dashboard");
	    myModel.addObject("computerList", computerService.findAll(search,order,sens));
	    return myModel;
	}
	
	@RequestMapping(value="/addComputer*",method=RequestMethod.GET)
	public ModelAndView addComputerForm(Model m) {
		ModelAndView myModel = new ModelAndView();
		myModel.setViewName("addComputer");
		myModel.addObject("companyList", companyService.getAllCompany());

		m.addAttribute("computerDto", new ComputerDTO());
		return myModel;
	}
	
	@RequestMapping(value="/addComputer",method = RequestMethod.POST)
	public ModelAndView addComputer(@Valid ComputerDTO computerDto,BindingResult result,Model m) {
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
			LocalDate introduced = new LocalDate(computer.getIntroduced());
			LocalDate discontinued = new LocalDate(computer.getDiscontinued());
			computerService.addComputer(computer.getName(), introduced.toString(), discontinued.toString(),computer.getCompany().getId());
			
			m.addAttribute("computerDto", new ComputerDTO());
			myModel.setViewName("addComputer");
			myModel.addObject("companyList", companyService.getAllCompany());
			myModel.addObject("message", "Ajout éfféctué");
			
		}
		return myModel;
	}
	
	@RequestMapping(value="/editComputerForm",method = RequestMethod.GET)
	public ModelAndView editComputerForm(@RequestParam(required=false,defaultValue="0") long id,Model m) {
		logger.info("Displaying edit Form");
		ModelAndView myModel = new ModelAndView();
		ComputerDTO cDto = new ComputerDTO();
		myModel.setViewName("editComputer");		
		myModel.addObject("computer", cDto.toDto(computerService.getComputerById(id)));
		m.addAttribute("computerDto", cDto);
		myModel.addObject("companyList", companyService.getAllCompany());
		
		return myModel;
	}
	
	@RequestMapping(value="/editComputer",method = RequestMethod.POST)
	public ModelAndView editComputer(@Valid ComputerDTO computerDto,BindingResult result,Model m) {
		ModelAndView myModel = new ModelAndView();
		
		if (result.hasErrors()) {
			myModel.setViewName("editComputer");
			myModel.addObject("companyList", companyService.getAllCompany());
			m.addAttribute("computerDto", new ComputerDTO());
			myModel.addObject("message", result.getAllErrors());
		} else {
			logger.info("Editing computer");
			
			ComputerDTO cDto = new ComputerDTO();
			Computer computer = cDto.fromDto(computerDto);
			
			computerService.update(computer);
			m.addAttribute("computerDto", cDto);
			myModel.setViewName("editComputer");
			myModel.addObject("computer", computerDto);
			myModel.addObject("companyList", companyService.getAllCompany());
			myModel.addObject("message", "Modification éfféctuée");
		}
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
