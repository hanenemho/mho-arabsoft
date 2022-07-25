package org.sid.catservice.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
public class GroupeTaxation extends ObjetPersistant{
	
	private static final long serialVersionUID = 1L;
	
	private String codeGrpTax ;

	@Size(max=25)
	private String libGrp ;

	@Size(max=25)
	private String taxTva ;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PP_ID",nullable = false)
	private ParamPays paramPays ;


	public String getCodeGrpTax() {
		return codeGrpTax;
	}

	public void setCodeGrpTax(String codeGrpTax) {
		this.codeGrpTax = codeGrpTax;
	}

	public String getLibGrp() {
		return libGrp;
	}

	public void setLibGrp(String libGrp) {
		this.libGrp = libGrp;
	}

	public String getTaxTva() {
		return taxTva;
	}

	public void setTaxTva(String taxTva) {
		this.taxTva = taxTva;
	}

	public ParamPays getParamPays() {
		return paramPays;
	}

	public void setParamPays(ParamPays paramPays) {
		this.paramPays = paramPays;
	}
	
	/*@JsonIgnore
	@OneToMany(mappedBy="groupeTaxation",fetch=FetchType.LAZY)
	private Set<DetailsFacture> detailsFacture ;*/

	

	/*public Set<DetailsFacture> getDetailsFacture() {
		return detailsFacture;
	}

	public void setDetailsFacture(Set<DetailsFacture> detailsFacture) {
		this.detailsFacture = detailsFacture;
	}
	       */
	
	
	


}
