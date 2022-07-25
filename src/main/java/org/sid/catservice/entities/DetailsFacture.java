package org.sid.catservice.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DetailsFacture extends ObjetPersistant {
	
	private static final long serialVersionUID = 1L;
	
	private String orderProduit ;

	@Size(max=25)
	private String libProduit ;

	@Size(max=25)
	private String refProduit ;
		
	private String quantite ;
		
	private String prixUnit ;
	
	private String taxSpec ;
	
	private String infoDetFact;
	
	private String infoDetFactHEX;
    
	@ManyToOne
	@JoinColumn(nullable=false)
	private ParamProduit paramProduit ;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="DF_NUM_FACT",nullable=false)
	private Facture facture ;
	
	@ManyToOne
	@JoinColumn(name="DF_CODE_GRP_TAX",nullable=false)
	private GroupeTaxation groupeTaxation ;


	public String getQuantite() {
		return quantite;
	}


	public void setQuantite(String quantite) {
		this.quantite = quantite;
	}


	public String getOrderProduit() {
		return orderProduit;
	}


	public void setOrderProduit(String orderProduit) {
		this.orderProduit = orderProduit;
	}


	public String getLibProduit() {
		return libProduit;
	}


	public void setLibProduit(String libProduit) {
		this.libProduit = libProduit;
	}


	public String getPrixUnit() {
		return prixUnit;
	}


	public void setPrixUnit(String prixUnit) {
		this.prixUnit = prixUnit;
	}


	public String getTaxSpec() {
		return taxSpec;
	}


	public void setTaxSpec(String taxSpec) {
		this.taxSpec = taxSpec;
	}


	public Facture getFacture() {
		return facture;
	}


	public void setFacture(Facture facture) {
		this.facture = facture;
	}


	public GroupeTaxation getGroupeTaxation() {
		return groupeTaxation;
	}


	public void setGroupeTaxation(GroupeTaxation groupeTaxation) {
		this.groupeTaxation = groupeTaxation;
	}


	public String getRefProduit() {
		return refProduit;
	}


	public void setRefProduit(String refProduit) {
		this.refProduit = refProduit;
	}


	public ParamProduit getParamProduit() {
		return paramProduit;
	}


	public void setParamProduit(ParamProduit paramProduit) {
		this.paramProduit = paramProduit;
	}


	public String getInfoDetFact() {
		return infoDetFact;
	}


	public void setInfoDetFact(String infoDetFact) {
		this.infoDetFact = infoDetFact;
	}


	public String getInfoDetFactHEX() {
		return infoDetFactHEX;
	}


	public void setInfoDetFactHEX(String infoDetFactHEX) {
		this.infoDetFactHEX = infoDetFactHEX;
	}

	 
	
	
} 
