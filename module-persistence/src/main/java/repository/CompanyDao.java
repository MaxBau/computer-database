package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.Company;

@Repository
@Transactional
public class CompanyDao {
	@Autowired
	DataSource dataSource;

	@PersistenceContext
	private EntityManager entityManager;

	public CompanyDao() {
		super();
	}

	public Company find(long id) {

		return entityManager.find(Company.class, id);

	}

	@SuppressWarnings("unchecked")
	public List<Company> findAll() {

		return entityManager.createQuery("SELECT c FROM Company AS c").getResultList();

	}
}
