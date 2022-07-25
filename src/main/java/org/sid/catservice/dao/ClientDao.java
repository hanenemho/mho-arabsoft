package org.sid.catservice.dao;

import java.util.List;

import org.sid.catservice.entities.Client;
import org.sid.catservice.entities.Contribuable;
import org.sid.catservice.entities.GroupeTaxation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDao extends JpaRepository<Client , Long> {

	boolean existsByMatriculeFiscaleClient(String matriculeFiscaleClient);
	
	Client findByMatriculeFiscaleClient(String matriculeFiscaleClient);
	
	@Query( "select  c from Client c "
    		+ " where c.contribuable.id = ?1 ")
	List<Client> getMyClients(Long idc);
	
	/*
	 * @Query(
	 * value="select distinct email,matricule_fiscale_client,nom,num_tel from client c ,facture f,point_vente p where f.fct_codepoint= ?1 and c.id=f.fct_matfis_cli\r\n"
	 * + "",nativeQuery=true) 
	 * public List<Client> getMyClients(String codeAgent);
	 */
}
