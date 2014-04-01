package dto;

import java.util.Locale;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import domain.Company;
import domain.Computer;

@Component
public class ComputerDTO implements MessageSourceAware {

	private long id;
	@NotEmpty
	private String name;
	@DateFormat
	private String introduced;
	@DateFormat
	private String discontinued;
	private long companyId;
	private String companyName;

	private static MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		ComputerDTO.messageSource = messageSource;
	}

	public ComputerDTO() {
		super();
	}

	public ComputerDTO(long id, String name, String introduced, String discontinued, long companyId, String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Computer fromDto(ComputerDTO dto) {
		Locale locale = LocaleContextHolder.getLocale();
		DateTimeFormatter fmt = DateTimeFormat.forPattern(messageSource.getMessage("date.format", null, locale));

		Computer computer = new Computer();
		computer.setId(dto.getId());
		computer.setName(dto.getName());
		computer.setIntroduced(LocalDate.parse(dto.getIntroduced(),fmt));
		computer.setDiscontinued(LocalDate.parse(dto.getDiscontinued(),fmt));

		Company company = new Company();
		company.setId(dto.getCompanyId());
		company.setName(dto.getCompanyName());

		computer.setCompany(company);
		return computer;
	}

	public ComputerDTO toDto(Computer computer) {
		Locale locale = LocaleContextHolder.getLocale();
		String dateFormat = messageSource.getMessage("date.format", null, locale);

		ComputerDTO dto = new ComputerDTO();
		dto.setId(computer.getId());
		dto.setName(computer.getName());
		if (computer.getIntroduced() != null)
			dto.setIntroduced(computer.getIntroduced().toString(dateFormat));
		if (computer.getDiscontinued() != null)
			dto.setDiscontinued(computer.getDiscontinued().toString(dateFormat));

		if (computer.getCompany() != null) {
			dto.setCompanyId(computer.getCompany().getId());
			dto.setCompanyName(computer.getCompany().getName());
		} else {
			dto.setCompanyId(0);
			dto.setCompanyName(" ");
		}

		return dto;
	}
}
