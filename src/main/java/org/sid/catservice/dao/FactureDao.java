package org.sid.catservice.dao;

import java.util.List;

import org.sid.catservice.dto.RechercheFactDto;
import org.sid.catservice.entities.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureDao  extends JpaRepository<Facture, Long> {
	
	 Boolean existsByNumFact(String numfact); 
	
	 List<Facture> findByNumFact(String objetFact);
	 
	 List<Facture> findByNumFactOrTypFact(String numFact, String typFact);
	
	@Query( "select  f from Facture f "
    		+ " where f.pointVente.codeAgent = ?1 and ROWNUM <= 10 ORDER BY f.datFactInit DESC " )
	 List<Facture> getFactureByCodeAgent (String codeAgent);
	
	@Query( "select  f from Facture f "
    		+ " where f.pointVente.codeAgent = ?1 " )
	 List<Facture> getAllFactureByCodeAgent (String codeAgent);
	
	@Query("from Facture f "
			+"where (f.numFact = :#{#rechercheFact.numFact} or :#{#rechercheFact.numFact} is null)"
			+" and (f.typFact = :#{#rechercheFact.typFact} or :#{#rechercheFact.typFact} is null)"
			+"and (f.state = :#{#rechercheFact.etat} or :#{#rechercheFact.etat} is null)"
			+ "and (f.datFactInit between  :#{#rechercheFact.minDate} and :#{#rechercheFact.maxDate} or :#{#rechercheFact.minDate} is null or (:#{#rechercheFact.maxDate} is null))")
	 List<Facture> rechercheFacture (@Param("rechercheFact") RechercheFactDto rechercheFact);	
	
	@Query( " select Count(f) from Facture f where MONTH(f.datFactInit) = ?1 ")
	 Long getNbFactByMonth(int month); 
}
