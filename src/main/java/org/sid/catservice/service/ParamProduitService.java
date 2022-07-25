package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.dao.ParamProduitDao;
import org.sid.catservice.entities.ParamPays;
import org.sid.catservice.entities.ParamProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamProduitService extends GenericService<ParamProduit, ParamProduitDao> implements ParamProduitIService {
	
	
	 @Autowired
	 private ParamProduitDao paramProduitDao;

	 @Override
	 public List<ParamProduit> getParamProduits() {
		return this.paramProduitDao.findAll();
	 }
	
	 public ParamProduit getParamProduit(String codeProduit) {
		return this.paramProduitDao.findByCodeProduit(codeProduit);
	 }
	 
	 @Override
	 public ParamProduit addParamProduit(ParamProduit paramProduit) {
		return this.paramProduitDao.save(paramProduit);

		}
	 
	 @Override
	 public boolean deleteParamProduit(String codeProduit) {
		 ParamProduit prm = this.paramProduitDao.findByCodeProduit(codeProduit);
				if(paramProduitDao!=null)  {
						this.paramProduitDao.delete(prm);
						return true;
					}
		return false ;
		}

	@Override
	public ParamProduit updateParamProduit(Long id, ParamProduit paramprod) {
		ParamProduit prm = getById(id);
		prm.setCodeProduit(paramprod.getCodeProduit());
		prm.setLibProduit(paramprod.getLibProduit());
		prm.setPrixUnitaire(paramprod.getPrixUnitaire());
		prm.setGroupeTaxation(paramprod.getGroupeTaxation());
		return update(prm);
	}

	@Override
	public List<ParamProduit> getMyProduits(Long id) {
		return this.paramProduitDao.myParamProduit(id);
	}
	 

}
