package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import domain.Computer;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
	List<Computer> findByNameContainingOrCompanyNameContaining(String Name, String companyName, Pageable page);
	Long countByNameContainingOrCompanyNameContaining(String companyName, String name);
}
