package org.sid.catservice.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ParamPays extends ObjetPersistant{
	
	private static final long serialVersionUID = 1L;
		
	private String codePays ;

	@Size(max=25)
	private String libPays ;

	@Size(max=2)
	private String langue ;

	@Size(max=3)
	private String codeDevise ;

	@Size(max=25)
	private String formatMontant ;

	@Size(max=100)
	private String procVerifMat ;

	@Size(max=100)
	private String swPaiement ;
	

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getLibPays() {
		return libPays;
	}

	public void setLibPays(String libPays) {
		this.libPays = libPays;
	}

	public String getCodeDevise() {
		return codeDevise;
	}

	public void setCodeDevise(String codeDevise) {
		this.codeDevise = codeDevise;
	}

	public String getFormatMontant() {
		return formatMontant;
	}

	public void setFormatMontant(String formatMontant) {
		this.formatMontant = formatMontant;
	}

	public String getProcVerifMat() {
		return procVerifMat;
	}

	public void setProcVerifMat(String procVerifMat) {
		this.procVerifMat = procVerifMat;
	}

	public String getSwPaiement() {
		return swPaiement;
	}

	public void setSwPaiement(String swPaiement) {
		this.swPaiement = swPaiement;
	}

	@Override
	public String toString() {
		return "ParamPays [codePays=" + codePays + ", libPays=" + libPays + ", langue=" + langue + ", codeDevise="
				+ codeDevise + ", formatMontant=" + formatMontant + ", procVerifMat=" + procVerifMat + ", swPaiement="
				+ swPaiement + "]";
	}

	
	

	
    
	
}
