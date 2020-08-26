package br.com.petz.apiclientpet.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.petz.apiclientpet.exception.ApiException;
import br.com.petz.apiclientpet.model.Client;

public interface ClientService {
	Page<Client> findAll(Pageable pageable);
	Client findByCode(String clientCode) throws ApiException;
	Client save(Client client) throws ApiException,NoSuchAlgorithmException;
	Client update(Client buildClient) throws ApiException;
	void deleteByCode(String clientCode) throws ApiException;
}
