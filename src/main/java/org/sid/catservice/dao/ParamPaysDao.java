package org.sid.catservice.dao;

import org.sid.catservice.entities.ParamPays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamPaysDao  extends JpaRepository<ParamPays, Long> {
	
	   public ParamPays findByCodePays(String codePays);
	   


}
