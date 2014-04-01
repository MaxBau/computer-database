package dto;

import domain.Company;

public class CompanyDTO {
	private long id;
	private String name;
	
	public CompanyDTO(String name,long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public CompanyDTO() {
		super();
	}

	public CompanyDTO(long id) {
		super();
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	};
	
	public Company fromDto(CompanyDTO dto) {
		Company company = new Company();
		company.setId(dto.getId());
		company.setName(dto.getName());
		
		return company;
	}
	
	public CompanyDTO toDto(Company company) {
		CompanyDTO dto = new CompanyDTO();
		dto.setId(company.getId());
		dto.setName(company.getName());
		
		return dto;
	}
}
