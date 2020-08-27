package br.com.petz.apiclientpet.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.petz.apiclientpet.exception.ApiException;
import br.com.petz.apiclientpet.exception.IndexViolationApiException;
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
		log.info("Starting Method findAll in ClientSpringDataJPAService");
		Page<Client> allClients = clientRepository.findAll(pageable);
		log.info("finishing Method findAll in ClientSpringDataJPAService");
		return allClients;
	}

	@Override
	public Client findByCode(String clientCode) throws ApiException {
		log.info("Starting Method findByCode in ClientSpringDataJPAService");
		log.info("Parameter:{}", clientCode);
		log.info("Finding client by code on clientRepository");
		Optional<Client> clientByCode = clientRepository.findByCode(clientCode);
		if (clientByCode.isPresent()) {
			log.info("Finishing Method findByCode in ClientSpringDataJPAService");
			return clientByCode.get();
		} else {
			throw throwNotFoundApiException(clientCode);
		}
	}

	@Override
	public Client save(Client client) throws NoSuchAlgorithmException, ApiException {
		log.info("Starting Method Save in ClientSpringDataJPAService");
		log.info("Encrypting password");
		client.encryptPassword(passwordEncrypter);
		log.info("Building client code");
		client.buildCode();
		log.info("Save in clientRepository");
		saveRepository(client);
		log.info("Finishing Method Save in ClientSpringDataJPAService");
		return client;
	}

	private Client saveRepository(Client client) throws ApiException {
		try {
			return clientRepository.save(client);
		} catch (DataIntegrityViolationException e) {
			throw IndexViolationApiException.build(400L, "ERRO TO CREATE CLIENT", e);
		}
	}

	@Override
	public Client update(Client buildClientByDTO) throws ApiException {
		log.info("Starting Method update in ClientSpringDataJPAService");
		log.info("Parameter:{}", buildClientByDTO.getCode());
		Client clientByCode = this.findByCode(buildClientByDTO.getCode());
		clientByCode.update(buildClientByDTO);
		log.info("Updating client on clientRepository");
		clientRepository.save(clientByCode);
		log.info("Finishing Method update in ClientSpringDataJPAService");
		return clientByCode;
	}

	@Override
	public void deleteByCode(String clientCode) throws ApiException {
		log.info("Starting Method deleteByCode in ClientSpringDataJPAService");
		log.info("Parameter:{}", clientCode);
		log.info("Deleting client by code on clientRepository");
		clientRepository.delete(this.findByCode(clientCode));
		log.info("Finishing Method deleteByCode in ClientSpringDataJPAService");
	}

	private ApiException throwNotFoundApiException(String clientCode) {
		String message = "NOT FOUND CLIENT TO CODE:" + clientCode;
		log.warn(message + " in ClientSpringDataJPAService");
		return new ApiException(404L, message);
	}
}
