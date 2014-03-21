package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class JdbcCompanyDao {
	@Autowired
	DataSource dataSource;

	@PersistenceContext
	private EntityManager entityManager;

	public JdbcCompanyDao() {
		super();
	}

	public Company find(long id) {

		return entityManager.find(Company.class, id);

	}

	public List<Company> findAll() {

		return entityManager.createQuery("SELECT c FROM Company AS c").getResultList();

	}

	public Company create(Connection connect, Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public Company update(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	public List<Company> findAll(int limitMin, int limitMax, String search, String order, String sens) {
		// TODO Auto-generated method stub
		return null;
	}

	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Company create() {

		return new Company();
	}

	public Company create(long id) {

		return new Company(id);
	}
}
