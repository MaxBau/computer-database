package springapp.domain;

import org.springframework.stereotype.Component;

@Component
public class Company {

	private long id;
	private String name;
	
	public Company(String name,long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	
}
