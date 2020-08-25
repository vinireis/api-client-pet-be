package br.com.petz.apiclientpet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import br.com.petz.apiclientpet.model.Client;

public interface ClientService {
	Page<Client> findAll(Pageable pageable);
	Client findByCode(String clientCode) throws NotFound;
	Client save(Client client);
	Client update(Client buildClient) throws NotFound;
	void deleteByCode(String clientCode) throws NotFound;
}
