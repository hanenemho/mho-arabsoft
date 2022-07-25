package org.sid.catservice.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Facture extends ObjetPersistant {
	
	private static final long serialVersionUID = 1L;
	
	private String numFact ;
	
	private LocalDateTime dateFact ;
	
	@Size(max=100)
	private String typFact ;
		
	@Size(max=100)
	private String numFactInit ;
		
	private LocalDateTime datFactInit ;
	
	@Size(max=25)
	private String lieuLivraison ;
		
	@Size(max=100)
	private String codeSecef ;
	
	@Size(max=100)
	private String compteurs ;

	@Size(max=25) 
	private String dateHeureMfc ;
	
	@Size(max=100) 
	private String codeQr ;
	
	private boolean isValid =false ;
	
	private boolean isSaved =false ;

	@Size(max=25) 
	private String montantTotal ;

	@Size(max=25) 
	private String montantTaxSpec ;
	
	@Enumerated(EnumType.STRING)
	private FactureState state ;
	
			
	private String infos ;
	
	private String infoClient;
	
	private String infoClientHEX;
	
	private String infoFacture;
	
	private String infoFactureHEX;
	
	@ManyToOne
	@JoinColumn(name="FCT_MAT_FIS")
	private Contribuable contribuable ;
	
	@OneToOne
	@JoinColumn(name="FCT_ANNULER")
	private Facture factureAvoir;
	
	
	@ManyToOne
	@JoinColumn(name="FCT_MATFIS_CLI")
	private Client client ;
	
	@OneToMany(mappedBy="facture",fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<DetailsFacture> detailsFacture ;

	
	@ManyToOne
	@JoinColumn( name="FCT_CODEPOINT")
	private PointVente pointVente ;
	 
	
	@OneToMany(mappedBy="facture",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	private Set<SubTotauxFacture> subTotauxFacture ;
	
	
	

	public String getCompteurs() {
		return compteurs;
	}

	public void setCompteurs(String compteurs) {
		this.compteurs = compteurs;
	}

	
	public Contribuable getContribuable() {
		return contribuable;
	}

	public void setContribuable(Contribuable contribuable) {
		this.contribuable = contribuable;
	}

	public Set<DetailsFacture> getDetailsFacture() {
		return detailsFacture;
	}

	public void setDetailsFacture(Set<DetailsFacture> detailsFacture) {
		this.detailsFacture = detailsFacture;
	}

	public PointVente getPointVente() {
		return pointVente;
	}

	public void setPointVente(PointVente pointVente) {
		this.pointVente = pointVente;
	}

	public String getNumFact() {
		return numFact;
	}

	public void setNumFact(String numFact) {
		this.numFact = numFact;
	}

	public LocalDateTime getDateFact() {
		return dateFact;
	}

	public void setDateFact(LocalDateTime dateFact) {
		this.dateFact = dateFact;
	}


	public String getTypFact() {
		return typFact;
	}

	public void setTypFact(String typFact) {
		this.typFact = typFact;
	}

	public String getNumFactInit() {
		return numFactInit;
	}

	public void setNumFactInit(String numFactInit) {
		this.numFactInit = numFactInit;
	}

	public LocalDateTime getDatFactInit() {
		return datFactInit;
	}

	public void setDatFactInit(LocalDateTime datFactInit) {
		this.datFactInit = datFactInit;
	}

	public String getLieuLivraison() {
		return lieuLivraison;
	}

	public void setLieuLivraison(String lieuLivraison) {
		this.lieuLivraison = lieuLivraison;
	}

	public String getCodeSecef() {
		return codeSecef;
	}

	public void setCodeSecef(String codeSecef) {
		this.codeSecef = codeSecef;
	}

	public String getDateHeureMfc() {
		return dateHeureMfc;
	}

	public void setDateHeureMfc(String dateHeureMfc) {
		this.dateHeureMfc = dateHeureMfc;
	}

	public String getCodeQr() {
		return codeQr;
	}

	public void setCodeQr(String codeQr) {
		this.codeQr = codeQr;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getInfoClient() {
		return infoClient;
	}

	public void setInfoClient(String infoClient) {
		this.infoClient = infoClient;
	}

	public String getInfoClientHEX() {
		return infoClientHEX;
	}

	public void setInfoClientHEX(String infoClientHEX) {
		this.infoClientHEX = infoClientHEX;
	}

	public String getInfoFacture() {
		return infoFacture;
	}

	public void setInfoFacture(String infoFacture) {
		this.infoFacture = infoFacture;
	}

	public String getInfoFactureHEX() {
		return infoFactureHEX;
	}

	public void setInfoFactureHEX(String infoFactureHEX) {
		this.infoFactureHEX = infoFactureHEX;
	}

	public String getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(String montantTotal) {
		this.montantTotal = montantTotal;
	}

	public String getMontantTaxSpec() {
		return montantTaxSpec;
	}

	public void setMontantTaxSpec(String montantTaxSpec) {
		this.montantTaxSpec = montantTaxSpec;
	}

	public Set<SubTotauxFacture> getSubTotauxFacture() {
		return subTotauxFacture;
	}

	public void setSubTotauxFacture(Set<SubTotauxFacture> subTotauxFacture) {
		this.subTotauxFacture = subTotauxFacture;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	public FactureState getState() {
		return state;
	}

	public void setState(FactureState state) {
		this.state = state;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	
	public Facture getFactureAvoir() {
		return factureAvoir;
	}

	public void setFactureAvoir(Facture factureAvoir) {
		this.factureAvoir = factureAvoir;
	}

	@Override
	public String toString() {
		return "Facture [numFact=" + numFact + ", dateFact=" + dateFact + ", typFact=" + typFact + ", numFactInit="
				+ numFactInit + ", datFactInit=" + datFactInit + ", lieuLivraison=" + lieuLivraison + ", codeSecef="
				+ codeSecef + ", compteurs=" + compteurs + ", dateHeureMfc=" + dateHeureMfc + ", codeQr=" + codeQr
				+ ", isValid=" + isValid + ", isSaved=" + isSaved + ", montantTotal=" + montantTotal
				+ ", montantTaxSpec=" + montantTaxSpec + ", contribuable=" + contribuable + ", client=" + client
				+ ", detailsFacture=" + detailsFacture + ", pointVente=" + pointVente + ", subTotauxFacture="
				+ subTotauxFacture + "]";
	}

	
	
}
