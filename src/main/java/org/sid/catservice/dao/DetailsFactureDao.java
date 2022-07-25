package org.sid.catservice.dao;

import org.sid.catservice.entities.DetailsFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DetailsFactureDao  extends JpaRepository<DetailsFacture, Long> {

	@Modifying
	@Query(value="Delete from details_facture d "
	         +" where df_num_fact = ?1 ",nativeQuery=true)
	@Transactional
    void deleteDetailsFactures(Long id);  
}
