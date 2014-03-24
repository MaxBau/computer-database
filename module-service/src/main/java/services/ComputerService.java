package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.CompanyDao;
import repository.ComputerDao;
import repository.LogDAO;
import domain.Computer;
import domain.Log;
import domain.WrapperListInt;
import dto.ComputerDTO;


@Service

public class ComputerService implements MessageSourceAware{
	@Autowired
	private ComputerDao computerDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private LogDAO logDao;

	private static MessageSource messageSource;
		
	public ComputerService()
	{
		super();
	}
	
	public void setMessageSource(MessageSource messageSource) {
		// TODO Auto-generated method stub
		ComputerService.messageSource = messageSource;
	}
	
	@Transactional(readOnly=true)
	public WrapperListInt findAll(String search,String order,String sens,String limitMin, String limitMax) {
		
		WrapperListInt wrapper = new WrapperListInt(computerDao.findAll(search,order,sens,limitMin, limitMax),computerDao.count(search));
		
		ComputerDTO cDto = new ComputerDTO();
		List<ComputerDTO> computerList = new ArrayList<ComputerDTO>();
		for (Object computer : wrapper.getList()) {
			computerList.add(cDto.toDto((Computer) computer));
		}
		
		wrapper.setList(computerList);
		return wrapper;
	}
	
	@Transactional
	public void addComputer(String name, String introduced, String discontinued, long companyId) {
		Locale locale = LocaleContextHolder.getLocale();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(messageSource.getMessage("date.format", null, locale));

		Computer c = new Computer(name,LocalDate.parse(introduced,fmt),LocalDate.parse(discontinued,fmt), companyDao.find(companyId));
		computerDao.add(c);
		
		Log l = new Log("Computer added "+c.toString(), 0);
		logDao.addLog(l);
		
	}
	
	public ComputerDTO getComputerById(long id) {
		return new ComputerDTO().toDto(computerDao.find(id));
	}
	
	@Transactional
	public void update(ComputerDTO computerDto) {
		Computer c = new ComputerDTO().fromDto(computerDto);
		c.setCompany(companyDao.find(computerDto.getCompanyId()));
		computerDao.update(c);
		
		Log l = new Log("Computer updated "+c , 0);
		logDao.addLog(l);
	}
	
	@Transactional
	public void deleteComputer(long id) {
		Log l = new Log("Computer deleted "+id , 0);
		logDao.addLog(l);
		computerDao.delete(id);
	}
}
