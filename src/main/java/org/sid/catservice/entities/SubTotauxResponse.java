package org.sid.catservice.entities;

import java.util.ArrayList;

public class SubTotauxResponse {
	 private ArrayList<SubTotauxFacture> SubTotauxFactureList= new ArrayList<SubTotauxFacture>();
	 
	 private String MontantTotal;
	 
	 private String MontantTaxSpec;

	public SubTotauxResponse() {
		// TODO Auto-generated constructor stub
	}
    
	public String getMontantTaxSpec() {
		return MontantTaxSpec;
	}

	public void setMontantTaxSpec(String montantTaxSpec) {
		MontantTaxSpec = montantTaxSpec;
	}

	public ArrayList<SubTotauxFacture> getSubTotauxFactureList() {
		return SubTotauxFactureList;
	}

	public void setSubTotauxFactureList(ArrayList<SubTotauxFacture> subTotauxFactureList) {
		SubTotauxFactureList = subTotauxFactureList;
	}

	public String getMontantTotal() {
		return MontantTotal;
	}

	public void setMontantTotal(String montantTotal) {
		MontantTotal = montantTotal;
	}

	
  
}
