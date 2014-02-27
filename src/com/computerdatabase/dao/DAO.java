package com.computerdatabase.dao;

import java.sql.Connection;
import java.util.List;

import com.computerdatabase.jdbc.ConnectionMySql;

public abstract class DAO<T> {
	public Connection connect = ConnectionMySql.getInstance();
	public abstract T find(long id);
	public abstract T create(T obj);
	public abstract T update(T obj);
	public abstract void delete(T obj);
	public abstract List<T> findAll(int limitMin,int limitMax,String search,String order);
	public abstract List<T> findAll();
	public abstract int count();
	public abstract void delete(long id);

}
