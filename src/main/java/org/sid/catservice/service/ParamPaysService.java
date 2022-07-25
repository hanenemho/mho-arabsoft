package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.dao.FactureDao;
import org.sid.catservice.dao.ParamPaysDao;
import org.sid.catservice.entities.GroupeTaxation;
import org.sid.catservice.entities.ParamPays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParamPaysService extends GenericService<ParamPays, ParamPaysDao> implements ParamPaysIService {
	


	@Autowired
	private ParamPaysDao paramPaysDao;

	@Override
	public ParamPays addParamPays(ParamPays paramPays) {
		return this.paramPaysDao.save(paramPays);
	}

	@Override
	public List<ParamPays> getAllParamPays() {
		return this.paramPaysDao.findAll();
	}

	@Override
	public boolean deleteParamPays(String codeParam) {
	ParamPays prm = this.paramPaysDao.findByCodePays(codeParam);
				if(paramPaysDao!=null)  {
					 this.paramPaysDao.delete(prm);
					 return true;
				}
		return false ;
	}

}
