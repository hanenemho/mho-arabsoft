package org.sid.catservice.service;

import java.util.ArrayList;
import java.util.List;

import org.sid.catservice.dto.RechercheFactDto;
import org.sid.catservice.entities.DetailsFacture;
import org.sid.catservice.entities.Facture;
import org.sid.catservice.entities.SubTotauxFacture;
import org.sid.catservice.entities.SubTotauxResponse;

public interface FactureIService extends GenericIService<Facture>{
	
	
	 public SubTotauxResponse addFacture(Facture facture);
	 
	 public Facture getFactureByNumFact(String numFact);
	 
     public List<Facture> getAllFacture();
     
     public List<Facture> getFactureByCodeAgent(String codeAgent);
          
     public List<Facture> getAllFactureByCodeAgent(String codeAgent);
     
	 public Boolean validateFacture(String numFacture);

	 public Boolean deleteFacture(Long id);

	 public List<Facture> rechercheFacture(RechercheFactDto rechercheFact);
	 
	 public List<Long> countFactByMonth();
	 
	 public String clientInfo(Facture fact);
	 
	 public String factInfo(Facture fact);
	 
	 public String detailfactInfo(DetailsFacture df);
}
