package springapp.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ComputerDTO {
	private long id;
	private String name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date introduced;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date discontinued;
	private long companyId;
	private String companyName;
	
	public ComputerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComputerDTO(long id, String name, Date introduced,
			Date discontinued, long companyId, String companyName) {
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
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Date discontinued) {
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
		
		Computer computer = new Computer();
		computer.setId(dto.getId());
		computer.setName(dto.getName());
		computer.setIntroduced(dto.getIntroduced());
		computer.setDiscontinued(dto.getDiscontinued());
		
		Company company = new Company();
		company.setId(dto.getCompanyId());
		company.setName(dto.getCompanyName());
		
		computer.setCompany(company);
		return computer;
	}
	
	public ComputerDTO toDto(Computer computer) {
		ComputerDTO dto = new ComputerDTO();
		dto.setId(computer.getId());
		dto.setName(computer.getName());
		dto.setIntroduced(computer.getIntroduced());
		dto.setDiscontinued(computer.getDiscontinued());
		dto.setCompanyId(computer.getCompany().getId());
		dto.setCompanyName(computer.getCompany().getName());
		
		return dto;
	}
	
	
}
