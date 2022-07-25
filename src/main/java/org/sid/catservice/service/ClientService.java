package org.sid.catservice.service;

import java.util.List;

import org.sid.catservice.dao.ClientDao;
import org.sid.catservice.dao.ContribuableDao;
import org.sid.catservice.entities.Client;
import org.sid.catservice.entities.GroupeTaxation;
import org.sid.catservice.entities.ParamPays;
import org.sid.catservice.entities.ParamProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends GenericService<Client, ClientDao> implements ClientIService{
	
	@Autowired
	private ClientDao clientDao;

	@Override
	public Client getClient(String matriculeFiscaleClient) {	
		return this.clientDao.findByMatriculeFiscaleClient(matriculeFiscaleClient);
	}

	@Override
	public boolean existingClient(String matriculeFiscaleClient) {
		return this.clientDao.existsByMatriculeFiscaleClient(matriculeFiscaleClient);
	}
	
	 @Override
	 public Client addClient(Client client) {
		return this.clientDao.save(client);

		}

	@Override
	public List<Client> getAllMyClientsByCont(Long idc) {
		return this.clientDao.getMyClients(idc);
	}
	

	public Client updateClient(Long id, Client client) {
		Client cl = getById(id);
		cl.setMatriculeFiscaleClient(client.getMatriculeFiscaleClient());
		cl.setNom(client.getNom());
		cl.setAddresse(client.getAddresse());
		cl.setEmail(client.getEmail());
		cl.setNumTel(client.getNumTel());
		return this.clientDao.save(cl);
	}
	@Override
	public boolean deleteClient(Long id) {
		Client cl = getById(id);
		if(cl!=null)  {
			 this.clientDao.delete(cl);
			 return true;
		}
		return false ;
	}
	


}
