package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.entities.GroupeTaxation;

public interface GroupeTaxationIService extends GenericIService<GroupeTaxation>{
	
	
	 public List<GroupeTaxation> findAllgroupeTaxation();
	 
	 public GroupeTaxation addGroupeTaxation(GroupeTaxation groupeTaxation);
	 
	 public GroupeTaxation updateGroupeTax(Long id, GroupeTaxation groupeTaxation);
	 
	 public boolean deleteGroupeTaxation(Long id);
	 


}
