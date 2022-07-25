package org.sid.catservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class SubTotauxFacture extends ObjetPersistant{

	private static final long serialVersionUID = 1L;
		
	private String codeGrp ;

	@Size(max=25)
	private String totalHt ;

	@Size(max=25)
	private String totalTax ;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="STF_NUM_FACT",nullable=false)
	private Facture facture ;

	public String getCodeGrp() {
		return codeGrp;
	}

	public void setCodeGrp(String codeGrp) {
		this.codeGrp = codeGrp;
	}

	public String getTotalHt() {
		return totalHt;
	}

	public void setTotalHt(String totalHt) {
		this.totalHt = totalHt;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	
}
