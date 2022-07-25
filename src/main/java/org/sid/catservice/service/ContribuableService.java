package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.dao.ContribuableDao;
import org.sid.catservice.dao.DetailsFactureDao;
import org.sid.catservice.dto.MessageResponse;
import org.sid.catservice.entities.Contribuable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContribuableService extends GenericService<Contribuable, ContribuableDao> implements ContribuableIService {
	
	@Autowired
	private ContribuableDao contribuableDao;


	@Override
	public Contribuable getAllContribuables(String matriculeFiscale) {
		return this.contribuableDao.findByMatriculeFiscale(matriculeFiscale);
	}
	

	@Override
	public List<Contribuable> getContribuableList() {
		return this.contribuableDao.findAll();
	}

	
	@Override
	public Contribuable updateContribuable(Long id, Contribuable contribuable) {
		Contribuable con = getById(id);
		con.setMatriculeFiscale(contribuable.getMatriculeFiscale());
		con.setEmail(contribuable.getEmail());
		con.setAdresse(contribuable.getAdresse());
		con.setNumRegistre(contribuable.getNumRegistre());
		con.setNumtel(contribuable.getNumtel());
		con.setRaisonSocial(contribuable.getRaisonSocial());
		con.setParamPays(contribuable.getParamPays());
		if(contribuableDao.existsByEmail(con.getEmail()) && con.getEmail()!= contribuable.getEmail()) {
			return null;
		}
		this.contribuableDao.save(con);
						
		return con;
	}
	
}
