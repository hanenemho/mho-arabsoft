package org.sid.catservice.dao;

import java.util.List;

import org.sid.catservice.entities.PointVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PointVenteDao  extends JpaRepository<PointVente, Long> {
	
		
		//Boolean ExistByCodeAgent(String codeAgent);
	
	  Boolean existsByEmail(String email);
	
      @Query( "select  p from PointVente p "
    		+ " where p.contribuable.matriculeFiscale = ?1 " )
	  List<PointVente> getPointVenteByMatFiscale(String matricule_fiscale);
      
  	  List<PointVente> findByCodeAgent(String code_agent);
  	   
  	  
  	   
}
