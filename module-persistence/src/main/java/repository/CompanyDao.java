package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
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

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Company> companyQuery = builder.createQuery(Company.class);

		Root<Company> companyRoot = companyQuery.from(Company.class);
		Expression<String> pathId = companyRoot.get("id");
		companyQuery.select(companyRoot).where(builder.equal(pathId, id));

		return entityManager.createQuery(companyQuery).getSingleResult();
	}

	public List<Company> findAll() {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Company> companyQuery = builder.createQuery(Company.class);

		Root<Company> companyRoot = companyQuery.from(Company.class);

		companyQuery.select(companyRoot);

		return entityManager.createQuery(companyQuery).getResultList();

	}
}
