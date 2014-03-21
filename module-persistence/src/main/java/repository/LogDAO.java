package repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import domain.Log;

@Repository
public class LogDAO {
	static final Logger LOGGER = LoggerFactory.getLogger(LogDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public LogDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addLog(Log l) 
	{
		
		entityManager.persist(l);
		LOGGER.debug("Log added : " + l);
		
	}
	
	

}
