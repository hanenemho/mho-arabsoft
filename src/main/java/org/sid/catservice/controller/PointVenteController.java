package org.sid.catservice.controller;

import java.util.List;

import org.sid.catservice.entities.Contribuable;
import org.sid.catservice.entities.PointVente;
import org.sid.catservice.service.PointVenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/PointVentes")
public class PointVenteController {
	
	@Autowired
	private PointVenteService pointVenteService ;
    
	@CrossOrigin
	@PostMapping(value = "/addPointVente")
	 public PointVente addPointVente(@RequestBody PointVente pointVente) {
		return this.pointVenteService.addPointVente(pointVente);
	}
	
	/* Get All Point vente */
	@CrossOrigin
	@GetMapping(value = "/getPointVente/{matricule_fiscale}")
	public List<PointVente> getAllPointVente(@PathVariable("matricule_fiscale") String matricule_fiscale) {
		return  this.pointVenteService.getPointVentesByMf(matricule_fiscale);
	}
	
	/* Update Point */
	@CrossOrigin
	@PutMapping(value = "/updatePointvente/{id}")
	public PointVente updatePoint(@PathVariable("id") Long id,@RequestBody PointVente pointvente) {
		pointVenteService.updatePointvente(id, pointvente);
		return pointvente;
	}
	
	
}
