package com.alindus.iss.service;

import java.util.List;

public interface AbstractService<T, PK> {

	public void add(T t);

	public void update(T t);

	public void remove(PK obj);

	public T findOne(PK obj);

	public List<T> findAll();
}
