package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.entities.PointVente;

public interface PointVenteIService extends GenericIService<PointVente>{
     
	 public PointVente addPointVente(PointVente pointVente);
	 
	 public List<PointVente> getPointVentesByMf(String maricule_fiscale);
	 
	 public PointVente getPointVenteByCodeAgent(String codeAgent);

	 public PointVente updatePointvente (Long id, PointVente pointvente);
}
