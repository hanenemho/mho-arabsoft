package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.entities.ParamProduit;
import org.springframework.data.jpa.repository.Query;

public interface ParamProduitIService extends GenericIService<ParamProduit>{
	
	   public ParamProduit addParamProduit(ParamProduit paramProduit);

	   public List<ParamProduit> getParamProduits();
	   
	   public boolean deleteParamProduit(String codeProduit);
	   
	   public ParamProduit updateParamProduit( Long id, ParamProduit paramprod);
	
	   public List<ParamProduit> getMyProduits(Long id);
}
