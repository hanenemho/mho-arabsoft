package org.sid.catservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.sid.catservice.dao.ParamProduitDao;
import org.sid.catservice.dto.MessageResponse;
import org.sid.catservice.entities.ParamPays;
import org.sid.catservice.entities.ParamProduit;
import org.sid.catservice.service.ParamProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping(value = "/ParamProduits")
public class ParamProduitController {
     
	@Autowired
	private ParamProduitService paramProduitService ;
	 @Autowired
	 private ParamProduitDao paramProduitDao;

	
	/* Get All param */
	@GetMapping(value = "/getParamProduit")
	public List<ParamProduit> getAllParamProduit() {
		return  this.paramProduitService.getParamProduits();
	}
	
	/* Get By Codeproduit */
	@GetMapping(value = "/getParamProduit/{codeProduit}")
	public ParamProduit getParamProduit(@PathVariable(name="codeProduit") String cp) {
	  return this.paramProduitService.getParamProduit(cp);
	}
	
	/* Get By Codeproduit */
	@GetMapping(value = "/getmyproduits/{id}")
	public List<ParamProduit> getParamProduit(@PathVariable(name="id") Long id) {
	  return this.paramProduitService.getMyProduits(id);
	}
	
	/* Add Param produit */
	@CrossOrigin
	@PostMapping(value = "/addParamProduit")
	public ResponseEntity<?> addParamProduit(@RequestBody ParamProduit paramProduit) {
		try {
			 this.paramProduitService.addParamProduit(paramProduit);
			 return ResponseEntity.ok(new MessageResponse("Param Produit Ajouter avec succée!"));
		}catch(Exception e){
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur!!!"));
		}
		
	}
	/* Update Param produit */
	@CrossOrigin
	@PutMapping(value = "/updateParamProduit/{id}")
	public ResponseEntity<?> updateProduit(@PathVariable("id") Long id,@RequestBody ParamProduit paramProduit){
		try {
			this.paramProduitService.updateParamProduit(id, paramProduit);
			return ResponseEntity.ok(new MessageResponse("mise a jour de produit avec succeé!"));
		}catch (Exception e) {			
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur!!!"));
		}
	}
	
	/* Delete Param produit */
	@CrossOrigin
	@DeleteMapping("/deleteParamProduit/{codeProduit}")
	public ResponseEntity<?> deleteParamProduit(@PathVariable("codeProduit") String codeProduit) {
	try {
		this.paramProduitService.deleteParamProduit(codeProduit);
		return ResponseEntity.ok(new MessageResponse("Produit supprimeé!"));
	}catch(Exception e){
		System.out.println(e.getMessage());
		return ResponseEntity.badRequest().body(new MessageResponse("Erreur!"));
	}
		
	}
	
	
}
