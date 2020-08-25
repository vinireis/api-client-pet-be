package br.com.petz.apiclientpet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import br.com.petz.apiclientpet.model.Client;

@Service
public class ClientSpringDataJPAService implements ClientService{
	@Override
	public Page<Client> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client findByCode(String clientCode) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client save(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client update(Client buildClient) throws NotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByCode(String clientCode) throws NotFound {
		// TODO Auto-generated method stub
		
	}
}
