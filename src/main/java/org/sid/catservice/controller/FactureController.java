package org.sid.catservice.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sid.catservice.dto.MessageResponse;
import org.sid.catservice.dto.RechercheFactDto;
import org.sid.catservice.entities.Facture;
import org.sid.catservice.entities.FactureState;
import org.sid.catservice.entities.SubTotauxFacture;
import org.sid.catservice.entities.SubTotauxResponse;
import org.sid.catservice.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Facture")
public class FactureController {
	
	@Autowired
	private FactureService factureService ;
    
	Date date = new Date();
	@CrossOrigin
	@PostMapping(value = "/addFacture",produces=MediaType.APPLICATION_JSON_VALUE)
	 public SubTotauxResponse addFacture(@RequestBody Facture facture) {
		     SubTotauxResponse sbr = new SubTotauxResponse();
		     facture.setState(FactureState.ENREGISTRER);
		     facture.setInfos(facture.toString());
		     LocalDateTime localDateTime = LocalDateTime.now();
		     facture.setDateFact(localDateTime);
		     facture.setDatFactInit(facture.getDatFactInit().plusDays(1));
		     sbr= this.factureService.addFacture(facture);
		 return sbr;
	}
	
	@CrossOrigin
	@GetMapping(value = "/getAllFacture")
	public List<Facture> getAllFacture() {
		return this.factureService.getAllFacture();
	}
	
	@CrossOrigin
	@GetMapping(value = "/countbymonth")
	public List<Long> countByMonth() {
		return this.factureService.countFactByMonth();
	}
	
	@CrossOrigin
	@GetMapping(value = "/getFactureByNumFact/{numFact}")
	public Facture getFactureByNumFact(@PathVariable("numFact") String numFact) {
		return this.factureService.getFactureByNumFact(numFact);
	}
	
	@CrossOrigin
	@GetMapping(value = "/getFactureByCodeAgent/{codeAgent}")
	public List<Facture> getFactureByCodeAgent(@PathVariable("codeAgent") String codeAgent) {
		return this.factureService.getFactureByCodeAgent(codeAgent);
	}
	
	@CrossOrigin
	@GetMapping(value = "/getAllFactureByCodeAgent/{codeAgent}")
	public List<Facture> getAllFactureByCodeAgent(@PathVariable("codeAgent") String codeAgent) {
		return this.factureService.getAllFactureByCodeAgent(codeAgent);
	}
	
	@CrossOrigin
	@PostMapping(value = "/validateFacture/{numFact}")
	 public String validateFacture(@PathVariable("numFact") String numFact) {
		if( this.factureService.validateFacture(numFact)) {
			return "valider";
		}else {
		 return "non valider";
		}
	}
	
	@CrossOrigin
	@DeleteMapping("/deleteFacture/{id}")
	public ResponseEntity<?> deleteFacture(@PathVariable("id") Long id) {
	try {
		this.factureService.deleteFacture(id);
		return ResponseEntity.ok(new MessageResponse("Facture supprimeé!"));
	}catch(Exception e){
		System.out.println(e.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse("Erreur!"));
	}
		
	}
	
	@CrossOrigin
	@PostMapping(value = "/rechercheFacture")
	public List<Facture> rechercheFact(@RequestBody RechercheFactDto rechercheFact) {
		return this.factureService.rechercheFacture(rechercheFact);
	}
	
	

}
