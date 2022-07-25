package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.dao.GroupeTaxationDao;
import org.sid.catservice.entities.GroupeTaxation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupeTaxationService extends GenericService<GroupeTaxation, GroupeTaxationDao> implements GroupeTaxationIService {
	
	@Autowired
	private GroupeTaxationDao groupeTaxationDao;

	@Override
	public List<GroupeTaxation> findAllgroupeTaxation() {
		return groupeTaxationDao.findAll();
	}


	@Override
	public GroupeTaxation addGroupeTaxation(GroupeTaxation groupeTaxation) {
		return this.groupeTaxationDao.save(groupeTaxation);
	}

	@Override
	public GroupeTaxation updateGroupeTax(Long id, GroupeTaxation groupeTaxation) {
		GroupeTaxation grp = getById(id);
		grp.setCodeGrpTax(groupeTaxation.getCodeGrpTax());
		grp.setLibGrp(groupeTaxation.getLibGrp());
		grp.setTaxTva(groupeTaxation.getTaxTva());
		return this.groupeTaxationDao.save(grp);
	}

	
	@Override
	public boolean deleteGroupeTaxation(Long id) {
		GroupeTaxation grp = getById(id);
		if(grp!=null)  {
			grp.setParamPays(null);
			 this.groupeTaxationDao.delete(grp);
			 return true;
		}
		return false ;
	}




}
