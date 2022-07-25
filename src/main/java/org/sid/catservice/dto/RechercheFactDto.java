package org.sid.catservice.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.sid.catservice.entities.FactureState;

public class RechercheFactDto {
	private String numFact;
	private String typFact;
	private FactureState etat;
	private LocalDateTime minDate;
	private LocalDateTime maxDate;
	public String getNumFact() {
		return numFact;
	}
	public void setNumFact(String numFact) {
		this.numFact = numFact;
	}
	public String getTypFact() {
		return typFact;
	}
	public void setTypFact(String typFact) {
		this.typFact = typFact;
	}
	public FactureState getEtat() {
		return etat;
	}
	public void setEtat(FactureState etat) {
		this.etat = etat;
	}
	public LocalDateTime getMinDate() {
		return minDate;
	}
	public void setMinDate(LocalDateTime minDate) {
		this.minDate = minDate;
	}
	public LocalDateTime getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(LocalDateTime maxDate) {
		this.maxDate = maxDate;
	}
	
	
}
