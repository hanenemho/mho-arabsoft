package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.entities.Contribuable;

public interface ContribuableIService extends GenericIService<Contribuable>{
	
	public Contribuable getAllContribuables(String matriculeFiscale);
	public List<Contribuable> getContribuableList();
	public Contribuable updateContribuable(Long id, Contribuable contribuable);
}
