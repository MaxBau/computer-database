package services;

import java.util.List;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.ComputerRepository;
import repository.LogRepository;
import domain.Computer;
import domain.Log;
import domain.WrapperListInt;
import dto.ComputerDTO;


@Service

public class ComputerService implements MessageSourceAware{

	@Autowired
	private ComputerRepository computerRepository;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private LogRepository logRepository;
	
	
	private static MessageSource messageSource;
		
	public ComputerService()
	{
		super();
	}
	
	public void setMessageSource(MessageSource messageSource) {
		ComputerService.messageSource = messageSource;
	}
	
	public List<Computer> findAll() {
		return computerRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public WrapperListInt findAll(String search,String order,String sens,String limitMin, String limitMax) {
		

		Direction direction;
		if (sens=="ASC") {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}
	
		PageRequest page = new PageRequest(Integer.valueOf(limitMin), Integer.valueOf(limitMax),direction,order);
		WrapperListInt wrapper = new WrapperListInt(computerRepository.findByNameContainingOrCompanyNameContaining(search,search, page),
						computerRepository.countByNameContainingOrCompanyNameContaining(search, search));
		
		return wrapper ;

	}
	
	@Transactional
	public void addComputer(String name, String introduced, String discontinued, long companyId) {
		Locale locale = LocaleContextHolder.getLocale();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(messageSource.getMessage("date.format", null, locale));

		Computer c = new Computer(name,LocalDate.parse(introduced,fmt),LocalDate.parse(discontinued,fmt), companyService.find(companyId));
		computerRepository.save(c);
		
		Log l = new Log("Computer added "+c.toString(), 0);
		logRepository.save(l);
		
	}
	
	public ComputerDTO getComputerById(long id) {
		return new ComputerDTO().toDto(computerRepository.findOne(id));
	}
	
	@Transactional
	public void update(ComputerDTO computerDto) {
		Computer c = new ComputerDTO().fromDto(computerDto);
		c.setCompany(companyService.find(computerDto.getCompanyId()));
		computerRepository.save(c);
		
		Log l = new Log("Computer updated "+c , 0);
		logRepository.save(l);
	}
	
	@Transactional
	public void deleteComputer(long id) {
		Log l = new Log("Computer deleted "+id , 0);
		logRepository.save(l);
		computerRepository.delete(id);
	}
}
