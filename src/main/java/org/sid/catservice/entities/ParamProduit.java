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
public class ParamProduit extends ObjetPersistant{
	
	private static final long serialVersionUID = 1L;	
	
	private String codeProduit ;

	@Size(max=25)
	private String libProduit ;
	
	@Size(max=25)
	private String prixUnitaire ;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private GroupeTaxation groupeTaxation  ;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Contribuable contribuable  ;
	
	public String getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getLibProduit() {
		return libProduit;
	}

	public void setLibProduit(String libProduit) {
		this.libProduit = libProduit;
	}

	
	public String getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(String prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public GroupeTaxation getGroupeTaxation() {
		return groupeTaxation;
	}

	public void setGroupeTaxation(GroupeTaxation groupeTaxation) {
		this.groupeTaxation = groupeTaxation;
	}

	public Contribuable getContribuable() {
		return contribuable;
	}

	public void setContribuable(Contribuable contribuable) {
		this.contribuable = contribuable;
	}


}
