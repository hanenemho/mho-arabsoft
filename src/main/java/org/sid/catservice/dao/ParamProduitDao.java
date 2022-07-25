package org.sid.catservice.dao;

import java.util.List;

import org.sid.catservice.entities.ParamPays;
import org.sid.catservice.entities.ParamProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamProduitDao extends JpaRepository<ParamProduit , Long>  {

	
	   @Query("SELECT p from ParamProduit p where p.codeProduit = ?1 ")
	   ParamProduit findByCodeProduit(String codeProduit);
	   
	   @Query("select p from ParamProduit p where p.contribuable.id = ?1")
	   List<ParamProduit> myParamProduit(Long id);
}
