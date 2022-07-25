package org.sid.catservice.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class PointVente extends ObjetPersistant {
	
	private static final long serialVersionUID = 1L;

	@Size(max=25)
	private String codeAgent ;

	@Size(max=100)
	private String email ;

	@Size(max=25)
	private String nomAgent ;
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@Size(min=8,max=25)
	private String motPasse ;
	
	@Size(max=8)
	private String verificationCode ;

	@Size(max=25)
	private String libellePoint ;
	
	private boolean actif =false ;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PV_MATRICULE_FISCALE",nullable =false)
	@NotFound(action=NotFoundAction.IGNORE)
	private Contribuable contribuable ;

	@JsonIgnore
	@OneToMany(mappedBy="pointVente",fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<Facture> facture ;
	
	
	
	public String getCodeAgent() {
		return codeAgent;
	}

	public void setCodeAgent(String codeAgent) {
		this.codeAgent = codeAgent;
	}

	public String getNomAgent() {
		return nomAgent;
	}

	public void setNomAgent(String nomAgent) {
		this.nomAgent = nomAgent;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public String getLibellePoint() {
		return libellePoint;
	}

	public void setLibellePoint(String libellePoint) {
		this.libellePoint = libellePoint;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Contribuable getContribuable() {
		return contribuable;
	}

	public void setContribuable(Contribuable contribuable) {
		this.contribuable = contribuable;
	}

	public Set<Facture> getFacture() {
		return facture;
	}

	public void setFacture(Set<Facture> facture) {
		this.facture = facture;
	}


	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	
	 
	
	
}
