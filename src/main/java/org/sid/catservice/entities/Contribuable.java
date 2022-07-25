package org.sid.catservice.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Contribuable extends ObjetPersistant  {
   
	private static final long serialVersionUID = 1L;

		private String code;
			
		private String matriculeFiscale ;
		
		@Size(max=25)
		private String numRegistre ;
			
		@Size(min=8,max=25)
		private String raisonSocial ;
			
		@Email
		private String email ;
			
		private String numtel ;
		
		@Size(max=100)
		private String adresse ;
		
		@Size(max=8)
		private String verificationCode ;
		
		private boolean actif =false ;
				
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(nullable = false)
		@NotFound(action=NotFoundAction.IGNORE)
		private ParamPays paramPays ;   
		
		@JsonProperty(access=Access.WRITE_ONLY)
		@OneToMany(mappedBy="contribuable",fetch=FetchType.LAZY)
		@NotFound(action=NotFoundAction.IGNORE)
		private List<PointVente> pointVente ;
		
		@JsonIgnore
		@OneToMany(mappedBy="contribuable",fetch=FetchType.LAZY)
		private Set<Facture> factures ;
		
		
		

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAdresse() {
			return adresse;
		}

		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

		public boolean getActif() {
			return actif;
		}

		public void setActif(boolean actif) {
			this.actif = actif;
		}

		 

		public ParamPays getParamPays() {
			return paramPays;
		}

		public void setParamPays(ParamPays paramPays) {
			this.paramPays = paramPays;
		}     
		public List<PointVente> getPointVente() {
			return pointVente;
		}

		public void setPointVente(List<PointVente> pointVente) {
			this.pointVente = pointVente;
		}

		public Set<Facture> getFactures() {
			return factures;
		}

		public void setFactures(Set<Facture> factures) {
			this.factures = factures;
		}

		public String getMatriculeFiscale() {
			return matriculeFiscale;
		}

		public void setMatriculeFiscale(String matriculeFiscale) {
			this.matriculeFiscale = matriculeFiscale;
		}

		public String getNumRegistre() {
			return numRegistre;
		}

		public void setNumRegistre(String numRegistre) {
			this.numRegistre = numRegistre;
		}

		public String getRaisonSocial() {
			return raisonSocial;
		}

		public void setRaisonSocial(String raisonSocial) {
			this.raisonSocial = raisonSocial;
		}

		public String getNumtel() {
			return numtel;
		}

		public void setNumtel(String numtel) {
			this.numtel = numtel;
		}

		 

		

		public String getVerificationCode() {
			return verificationCode;
		}

		public void setVerificationCode(String verificationCode) {
			this.verificationCode = verificationCode;
		}

		@Override
		public String toString() {
			return "Contribuable [matriculeFiscale=" + matriculeFiscale + ", numRegistre=" + numRegistre
					+ ", raisonSocial=" + raisonSocial + ", email=" + email + ", numtel=" + numtel + ", adresse="
					+ adresse + ", verificationCode=" + verificationCode + ", actif=" + actif + ", paramPays="
					+ paramPays + ", pointVente=" + pointVente + ", factures=" + factures + "]";
		}
		
		
		



}
