package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Company;
import domain.Computer;

@Repository
public class ComputerDao {

	public ComputerDao() {
		super();
	}

	static final Logger LOGGER = LoggerFactory.getLogger(ComputerDao.class);

	@Autowired
	DataSource dataSource;

	@PersistenceContext
	private EntityManager entityManager;

	public Computer find(long id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Computer> computerQuery = builder.createQuery(Computer.class);

		Root<Computer> computerRoot = computerQuery.from(Computer.class);
		Expression<String> pathId = computerRoot.get("id");
		computerQuery.select(computerRoot).where(builder.equal(pathId, id));

		return entityManager.createQuery(computerQuery).getSingleResult();

	}

	public void update(Computer obj) {

		LOGGER.debug("Updating computer " + obj);
		entityManager.merge(obj);

	}

	public void delete(long id) {

		entityManager.remove(entityManager.find(Computer.class, id));
		LOGGER.debug("Computer deleted " + id);
	}

	public List<Computer> findAll(String search, String order, String sens, String limitMin, String limitMax) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		if (order.equals(" "))
			order = "name";

		CriteriaQuery<Computer> computerQuery = builder.createQuery(Computer.class);

		Root<Computer> computerRoot = computerQuery.from(Computer.class);
		Expression<String> pathName = computerRoot.get("name");
		Join<Computer, Company> computerCompany = computerRoot.join("company", JoinType.LEFT);
		Expression<String> pathCompanyName = computerCompany.get("name");
		computerQuery.select(computerRoot).where(builder.or(builder.like(pathName, "%" + search + "%"), builder.like(pathCompanyName, "%" + search + "%")))
				.orderBy(builder.asc(computerRoot.get(order)));

		return entityManager.createQuery(computerQuery).setFirstResult(Integer.valueOf(limitMin)).setMaxResults(Integer.valueOf(limitMax)).getResultList();

	}

	public long count(String search) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> computerQuery = builder.createQuery(Long.class);

		Root<Computer> computerRoot = computerQuery.from(Computer.class);
		Expression<String> pathName = computerRoot.get("name");
		Join<Computer, Company> computerCompany = computerRoot.join("company", JoinType.LEFT);
		Expression<String> pathCompanyName = computerCompany.get("name");
		computerQuery.select(builder.count(computerRoot)).where(builder.or(builder.like(pathName, "%" + search + "%"), builder.like(pathCompanyName, "%" + search + "%")));

		return entityManager.createQuery(computerQuery).getSingleResult();
	}

	public void add(Computer c) {

		entityManager.persist(c);
		LOGGER.debug("Computer added : " + c);

	}
}
