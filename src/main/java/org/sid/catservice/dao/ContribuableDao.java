package org.sid.catservice.dao;

import org.sid.catservice.entities.Contribuable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.lang.String;
import java.util.List;

@Repository
public interface ContribuableDao extends JpaRepository<Contribuable , Long> {
	
	Boolean existsByEmail(String email);
  //public  List<Contribuable> findByMatriculeFiscale(String matriculefiscale);
  
    Contribuable findByMatriculeFiscale(String matriculefiscale);
  

}
