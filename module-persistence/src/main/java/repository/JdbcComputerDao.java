package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Company;
import domain.Computer;

@Repository
public class JdbcComputerDao {

	public JdbcComputerDao() {
		super();
	}

	static final Logger LOGGER = LoggerFactory.getLogger(JdbcComputerDao.class);

	@Autowired
	DataSource dataSource;

	@PersistenceContext
	private EntityManager entityManager;

	public Computer find(long id) {
		LOGGER.debug("Retrieving computer");
		return entityManager.find(Computer.class, id);
	}

	public void update(Computer obj) {

		LOGGER.debug("Updating computer " + obj);
		entityManager.merge(obj);

	}

	public void delete(long id) {

		entityManager.remove(entityManager.find(Computer.class, id));
		LOGGER.debug("Computer deleted " + id);
	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll(String search, String order, String sens, String limitMin, String limitMax) {
		if (order.equals(" ")) order = "computer.name";
		
		String query = "SELECT computer FROM Computer as computer LEFT JOIN computer.company company WITH company.name LIKE :search WHERE computer.name LIKE :search OR company.name LIKE :search ORDER BY "+order+" ASC ";

		return entityManager.createQuery(query).setParameter("search", "%" + search + "%")
				.setFirstResult(Integer.valueOf(limitMin))
				.setMaxResults(Integer.valueOf(limitMax))
				.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll() {

		return entityManager.createQuery("SELECT c FROM Computer AS c").getResultList();

	}

	public long count(String search) {

		String query = "SELECT COUNT(computer) FROM Computer as computer LEFT JOIN computer.company company WITH company.name LIKE :search WHERE computer.name LIKE :search OR company.name LIKE :search";
		return (Long) entityManager.createQuery(query).setParameter("search", "%" + search + "%").getSingleResult();
	}

	public void add(Computer c) {

		entityManager.persist(c);
		LOGGER.debug("Computer added : " + c);

	}

	public Computer create(String name, LocalDate introducedDate, LocalDate discontinuedDate, Company company) {

		return new Computer(name, introducedDate, discontinuedDate, company);
	}

	public Computer create(long id, String name, LocalDate introducedDate, LocalDate discontinuedDate, Company company) {
		return new Computer(id, name, introducedDate, discontinuedDate, company);
	}

	@SuppressWarnings("unchecked")
	public List<Computer> findAll(String search) {
		String query = "SELECT computer FROM Computer as computer LEFT JOIN computer.company company WITH company.name LIKE :search WHERE computer.name LIKE :search OR company.name LIKE :search";

		return entityManager.createQuery(query).setParameter("search", "%" + search + "%").getResultList();
	}

}
