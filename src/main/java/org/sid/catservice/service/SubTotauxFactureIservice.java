package org.sid.catservice.service;

import org.sid.catservice.entities.SubTotauxFacture;

public interface SubTotauxFactureIservice extends GenericIService<SubTotauxFacture>{
	
	 SubTotauxFacture addSubTotauxFacture(SubTotauxFacture subTotaux);

	 boolean deleteAllFactures(Long id);

}
