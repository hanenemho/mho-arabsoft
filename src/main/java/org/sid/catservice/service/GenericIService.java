package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.entities.ObjetPersistant;

public interface GenericIService<T extends ObjetPersistant> {

	T creation(T t);
	T update(T t);
	void delete(Long id);
	T getById(Long id);
	List<T> getAll();
	
}
