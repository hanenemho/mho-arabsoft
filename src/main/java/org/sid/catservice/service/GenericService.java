package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.entities.ObjetPersistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericService<T extends ObjetPersistant, TDao extends JpaRepository<T, Long>> implements GenericIService<T> {

	@Autowired
	TDao tDao;
	@Override
	public T creation(T t) {
		return tDao.save(t);
	}

	@Override
	public T update(T t) {
		return tDao.save(t);
	}

	@Override
	public void delete(Long id) {
		tDao.deleteById(id);
		
	}

	@Override
	public T getById(Long id) {
		return tDao.findById(id).get();
	}

	@Override
	public List<T> getAll() {
		return tDao.findAll();
	}

}
