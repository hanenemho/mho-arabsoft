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
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client extends ObjetPersistant{

	private static final long serialVersionUID = 1L;
	
	private String matriculeFiscaleClient ;	

	@Size(max=25)
	private String nom ;

	@Size(min=8,max=25)
	private int numTel ;
	
	private String addresse; 
	
	@Email	
	private String email ;

	@Transient
	private Set<Facture> factures ;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Contribuable contribuable ;
	
	public String getMatriculeFiscaleClient() {
		return matriculeFiscaleClient;
	}

	public void setMatriculeFiscaleClient(String matriculeFiscaleClient) {
		this.matriculeFiscaleClient = matriculeFiscaleClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNumTel() {
		return numTel;
	}

	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public Set<Facture> getFactures() {
		return factures;
	}

	public void setFactures(Set<Facture> factures) {
		this.factures = factures;
	}

	public Contribuable getContribuable() {
		return contribuable;
	}

	public void setContribuable(Contribuable contribuable) {
		this.contribuable = contribuable;
	}

	
	
	
}
