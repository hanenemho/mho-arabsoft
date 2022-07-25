package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.entities.Client;
import org.sid.catservice.entities.Contribuable;
import org.sid.catservice.entities.Facture;

public interface ClientIService extends GenericIService<Client>{

	public Client getClient(String matriculeFiscaleClient);
	public boolean existingClient(String matriculeFiscaleClient);
	public Client addClient(Client client);
    public List<Client> getAllMyClientsByCont(Long idc);
	public boolean deleteClient(Long id);

}
