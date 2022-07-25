package org.sid.catservice.service;

import org.sid.catservice.dao.SubTotauxFactureDao;
import org.sid.catservice.entities.Facture;
import org.sid.catservice.entities.SubTotauxFacture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubTotauxService extends GenericService<SubTotauxFacture, SubTotauxFactureDao> implements SubTotauxFactureIservice {
	 
	@Autowired
	 private SubTotauxFactureDao  subTotauxFactureDao;

	@Override
	public SubTotauxFacture addSubTotauxFacture(SubTotauxFacture subTotaux) {
		// TODO Auto-generated method stub
		return this.subTotauxFactureDao.save(subTotaux);
	}
	
	
	@Override
	public boolean deleteAllFactures(Long id) {
		this.subTotauxFactureDao.deleteAllFactures(id);		
		return true;
	}

}
