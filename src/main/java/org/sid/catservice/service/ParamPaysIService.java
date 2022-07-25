package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.entities.ParamPays;

public interface ParamPaysIService extends GenericIService<ParamPays>{
	
	 public ParamPays addParamPays(ParamPays paramPays);

     public List<ParamPays> getAllParamPays();
     
	 public boolean deleteParamPays(String codeParam);


}
