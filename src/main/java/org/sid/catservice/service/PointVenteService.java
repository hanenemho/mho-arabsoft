package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.dao.PointVenteDao;
import org.sid.catservice.entities.PointVente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointVenteService extends GenericService<PointVente, PointVenteDao>implements PointVenteIService {
	
    @Autowired
	private PointVenteDao pointVenteDao;

	@Override
	public PointVente addPointVente(PointVente pointVente) {
		return pointVenteDao.save(pointVente);
	}

	@Override
	public List<PointVente> getPointVentesByMf(String matricule_fiscale) {
		return this.pointVenteDao.getPointVenteByMatFiscale(matricule_fiscale);
	}

	@Override
	public PointVente getPointVenteByCodeAgent(String codeAgent) {
		return this.pointVenteDao.findByCodeAgent(codeAgent).get(0);
	}

	@Override
	public PointVente updatePointvente(Long id, PointVente pointvente) {
		PointVente pv = getById(id);
		pv.setCodeAgent(pointvente.getCodeAgent());
		pv.setNomAgent(pointvente.getNomAgent());
		pv.setLibellePoint(pointvente.getLibellePoint());
		pv.setEmail(pointvente.getEmail());
		//pv.setMotPasse(pointvente.getMotPasse());
		if(pointVenteDao.existsByEmail(pv.getEmail()) && pv.getEmail()!= pointvente.getEmail()) {
			return null;
		}
		this.pointVenteDao.save(pv);

		return pv;
	}

}
