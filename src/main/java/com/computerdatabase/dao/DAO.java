package com.computerdatabase.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.computerdatabase.classes.Computer;

public abstract class DAO<T> {
	public abstract T find(long id);
	public abstract T create(T obj);
	public abstract T update(T obj);
	public abstract void delete(T obj);
	public abstract List<T> findAll(int limitMin,int limitMax,String search,String order,String sens);
	public abstract List<T> findAll();
	public abstract int count();
	public abstract void delete(long id);
	public abstract Computer create(Connection connect, Computer obj) throws SQLException ;

}
