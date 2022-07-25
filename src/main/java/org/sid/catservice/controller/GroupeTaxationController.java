package org.sid.catservice.controller;

import java.util.List;

import org.sid.catservice.dto.MessageResponse;
import org.sid.catservice.entities.GroupeTaxation;
import org.sid.catservice.entities.ParamProduit;
import org.sid.catservice.service.GroupeTaxationService;
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

@RestController
@RequestMapping(value = "/GrpTax")
public class GroupeTaxationController {
	
	
	@Autowired
	private GroupeTaxationService groupeTaxationService ;
	
	/* Get All groupe taxations */
	@CrossOrigin
	@GetMapping(value = "/getGrpTaxation")
	public List<GroupeTaxation> getAllGroupeTaxations() {
		return this.groupeTaxationService.findAllgroupeTaxation();
	}
    
	/* Add groupe taxations */
	@CrossOrigin
	@PostMapping(value = "/addGrpTaxation")
	public GroupeTaxation addGrpTaxation(@RequestBody GroupeTaxation groupeTaxation) {
		return this.groupeTaxationService.addGroupeTaxation(groupeTaxation);
	}
	
	/* Update Param produit */
	@CrossOrigin
	@PutMapping(value = "/updateGrpTaxation/{id}")
	public ResponseEntity<?> updateGroupeTaxation(@PathVariable("id") Long id,@RequestBody GroupeTaxation groupeTaxation){
		try {
			this.groupeTaxationService.updateGroupeTax(id, groupeTaxation);
			return ResponseEntity.ok(new MessageResponse("mise a jour de Groupe taxation avec succeé!"));
		}catch (Exception e) {			
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur!!!"));
		}
	}
	
	
	/* Delete groupe taxations */
	@CrossOrigin
	@DeleteMapping("/deleteGrp/{id}")
	public boolean deleteGrpTax(@PathVariable("id") Long id) {
	
		return this.groupeTaxationService.deleteGroupeTaxation(id);
	}
}
