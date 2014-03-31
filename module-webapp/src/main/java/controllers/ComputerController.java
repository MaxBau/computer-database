package controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import services.ComputerService;
import domain.WrapperListInt;
import dto.ComputerDTO;

@Controller
@RequestMapping("/computer")
public class ComputerController {
	private static String sens = "ASC";

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;

	@RequestMapping(value = "/dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listComputers(@RequestParam(value = "search", defaultValue = "") String search, 
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "limitmin", defaultValue = "0") String limitMin, 
			@RequestParam(value = "limitmax", defaultValue = "10") String limitMax) {

		if (!order.equals(" ")) {
			if (sens.equals("ASC")) {
				sens = "DESC";
			} else {
				sens = "ASC";
			}
		}

		logger.info("Returning dashboard view");
		ModelAndView myModel = new ModelAndView();
		myModel.setViewName("dashboard");
		WrapperListInt wrapper = computerService.findAll(search, order, sens, limitMin, limitMax);
		myModel.addObject("computerList", wrapper.getList());
		myModel.addObject("computerCount", wrapper.getInteger());
		myModel.addObject("search", search);
		return myModel;
	}

	@RequestMapping(value = "/addComputer*", method = RequestMethod.GET)
	public ModelAndView addComputerForm(Model m) {
		ModelAndView myModel = new ModelAndView();
		myModel.setViewName("addComputer");
		myModel.addObject("companyList", companyService.getAllCompany());

		m.addAttribute("computerDto", new ComputerDTO());
		return myModel;
	}

	@RequestMapping(value = "/addComputer", method = RequestMethod.POST)
	public ModelAndView addComputer(@ModelAttribute("computerDto") @Valid ComputerDTO computerDto, BindingResult result) {
		ModelAndView myModel = new ModelAndView();
		myModel.setViewName("addComputer");
		myModel.addObject("companyList", companyService.getAllCompany());

		if (!result.hasErrors()) {
			logger.info("Adding computer");

			computerService.addComputer(computerDto.getName(), computerDto.getIntroduced(), computerDto.getDiscontinued(), computerDto.getCompanyId());

			myModel.addObject("message", "Ajout éfféctué");

		}
		return myModel;
	}

	@RequestMapping(value = "/editComputerForm", method = RequestMethod.GET)
	public ModelAndView editComputerForm(@RequestParam(required = false, defaultValue = "0") long id, Model m) {
		logger.info("Displaying edit Form");
		ModelAndView myModel = new ModelAndView();

		myModel.setViewName("editComputer");
		myModel.addObject("companyList", companyService.getAllCompany());

		m.addAttribute("computerDto", computerService.getComputerById(id));

		return myModel;
	}

	@RequestMapping(value = "/editComputer", method = RequestMethod.POST)
	public ModelAndView editComputer(@ModelAttribute("computerDto") @Valid ComputerDTO computerDto, BindingResult result) {
		ModelAndView myModel = new ModelAndView();

		if (result.hasErrors()) {
			myModel.setViewName("editComputer");
			myModel.addObject("computer", computerDto);
			myModel.addObject("companyList", companyService.getAllCompany());
		} else {
			logger.info("Editing computer");

			computerService.update(computerDto);
			myModel.setViewName("editComputer");
			myModel.addObject("computer", computerDto);
			myModel.addObject("companyList", companyService.getAllCompany());
			myModel.addObject("message", "Modification éfféctuée");
		}
		return myModel;
	}

	@RequestMapping(value = "deleteComputer", method = RequestMethod.GET)
	public ModelAndView deleteComputer(@RequestParam long id) {
		logger.info("deleting computer");

		computerService.deleteComputer(id);

		return new ModelAndView("redirect:/computer/dashboard");
	}
	
	@RequestMapping(value = "/loggedOut")
	public ModelAndView loggedOut() {
		logger.info("Logging out ");
		ModelAndView myModel = new ModelAndView();
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler();
//		ctxLogOut.logout(null, null, auth);
		myModel.setViewName("loggedOut");

		return myModel;
	}
}
