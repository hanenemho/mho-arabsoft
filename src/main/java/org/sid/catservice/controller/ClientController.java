package org.sid.catservice.controller;

import java.util.List;

import org.sid.catservice.dto.MessageResponse;
import org.sid.catservice.entities.Client;
import org.sid.catservice.entities.Contribuable;
import org.sid.catservice.entities.Facture;
import org.sid.catservice.entities.GroupeTaxation;
import org.sid.catservice.entities.ParamPays;
import org.sid.catservice.service.ClientService;
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
@RequestMapping(value = "/Client")
@CrossOrigin
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	
	/* Get a single Client */
	@GetMapping(value = "/getClient/{matriculeFiscaleClient}")
	public Client getClient(@PathVariable("matriculeFiscaleClient") String matriculeFiscaleClient) {
		return this.clientService.getClient(matriculeFiscaleClient);
	}
	

	@CrossOrigin
	@GetMapping(value = "/getAllClientsByCont/{idc}")
	public List<Client> getAllClientsByCont(@PathVariable("idc") Long idc) {
		return this.clientService.getAllMyClientsByCont(idc);
	}
	/* Add Client  */
	@CrossOrigin
	@PostMapping(value = "/addClient")
	public Client addClient(@RequestBody Client client) {
		return this.clientService.addClient(client);
	}
	/* Update Client */
	@CrossOrigin
	@PutMapping(value = "/updateClient/{id}")
	public ResponseEntity<?> updateGroupeTaxation(@PathVariable("id") Long id,@RequestBody Client updateClient){
		try {
			this.clientService.updateClient(id, updateClient);
			return ResponseEntity.ok(new MessageResponse("mise a jour de Client avec succeé!"));
		}catch (Exception e) {			
			return ResponseEntity.badRequest().body(new MessageResponse("Erreur!!!"));
		}
	}
	/* Delete Client*/
	@CrossOrigin
	@DeleteMapping("/deleteClient/{id}")
	public boolean deleteClient(@PathVariable("id") Long id) {
	
		return this.clientService.deleteClient(id);
	}
	
	@CrossOrigin
	@GetMapping(value = "/testgetall")
	public List<Client> testGetAll() {
		return this.clientService.getAll();
	}
	
}
