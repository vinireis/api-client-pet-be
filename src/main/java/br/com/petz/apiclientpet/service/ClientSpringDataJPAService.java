package br.com.petz.apiclientpet.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import br.com.petz.apiclientpet.model.Client;
import br.com.petz.apiclientpet.repository.ClientRepository;
import br.com.petz.apiclientpet.service.encrypt.PasswordEncrypter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientSpringDataJPAService implements ClientService {
	private PasswordEncrypter passwordEncrypter;
	private ClientRepository clientRepository;

	public ClientSpringDataJPAService(PasswordEncrypter passwordEncrypter, ClientRepository clientRepository) {
		this.passwordEncrypter = passwordEncrypter;
		this.clientRepository = clientRepository;
	}

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
	public Client save(Client client) throws NoSuchAlgorithmException {
		log.info("Starting Method Save in ClientSpringDataJPAService");
		log.info("Encrypting password");
		client.encryptPassword(passwordEncrypter);
		log.info("Building client code");
		client.buildCode();
		log.info("Save in clientRepository");
		clientRepository.save(client);
		log.info("Finishing Method Save in ClientSpringDataJPAService");
		return client;
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
