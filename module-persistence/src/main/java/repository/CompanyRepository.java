package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
