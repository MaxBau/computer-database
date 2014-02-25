package com.computerdatabase.dao;

import java.sql.Connection;

import com.computerdatabase.jdbc.ConnectionMySql;

public abstract class DAO<T> {
	public Connection connect = ConnectionMySql.getInstance();
	public abstract T find(long id);
	public abstract T create(T obj);
	public abstract T update(T obj);
	public abstract void delete(T obj);

}
