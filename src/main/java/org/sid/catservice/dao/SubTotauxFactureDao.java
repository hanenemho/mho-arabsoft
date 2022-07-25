package org.sid.catservice.dao;

import org.sid.catservice.entities.SubTotauxFacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SubTotauxFactureDao extends JpaRepository <SubTotauxFacture,Long>{
	  
	  @Modifying
	  @Query(value="Delete from sub_totaux_facture s "
	         +" where stf_num_fact = ?1 ",nativeQuery=true)
	  @Transactional
      public void deleteAllFactures(Long id);          
}
