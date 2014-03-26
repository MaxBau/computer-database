package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Log;

public interface LogRepository extends JpaRepository<Log, Integer> {

}
