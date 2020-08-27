package br.com.petz.apiclientpet.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.petz.apiclientpet.exception.ApiException;
import br.com.petz.apiclientpet.exception.IndexViolationApiException;
import br.com.petz.apiclientpet.model.Pet;
import br.com.petz.apiclientpet.repository.PetRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PetSpringDataJPAService implements PetService {
	private ClientService clientService;
	private PetRepository petRepository;

	public PetSpringDataJPAService(ClientService clientService, PetRepository petRepository) {
		this.clientService = clientService;
		this.petRepository = petRepository;
	}

	@Override
	public Page<Pet> findByClientCode(String clientCode, Pageable pageable) throws ApiException {
		log.info("Starting Method findByClientCode in PetSpringDataJPAService");
		log.info("Parameter:{}", clientCode);
		log.info("Finding pets by client on petRepository");
		Page<Pet> petsByClient = petRepository.findByClient_Code(clientCode,pageable);
		log.info("Finishing Method findByClientCode in PetSpringDataJPAService");
		return petsByClient;
	}

	@Override
	public Pet findByCode(String clientCode, String petCode) throws ApiException {
		log.info("Starting Method findByClientCode in PetSpringDataJPAService");
		log.info("Parameters:clientCode = {}, petCode = {}", clientCode,petCode);
		log.info("Finding pets by clientCode and petCode on petRepository");
		Optional<Pet> pet = petRepository.findByClient_CodeAndCode(clientCode, petCode);
		if(pet.isPresent()) {
			log.info("Finishing Method findByClientCode in PetSpringDataJPAService");
			return pet.get();
		} else {
			throw throwNotFoundApiException(clientCode, petCode);
		}
	}

	@Override
	public Pet save(Pet petBuildedByForm) throws ApiException {
		log.info("Starting Method save in PetSpringDataJPAService");
		log.info("Parameters:clientCode = {}", petBuildedByForm.getClientCode());
		log.info("Finding client by code on clientService");
		petBuildedByForm.flushClient(clientService);
		log.info("Building pet code");
		petBuildedByForm.buildCode();
		log.info("Save in petRepository");
		saveRepository(petBuildedByForm);
		log.info("Finishing Method save in PetSpringDataJPAService");
		return petBuildedByForm;
	}

	private Pet saveRepository(Pet petBuildedByForm) throws ApiException {
		try {
			return petRepository.save(petBuildedByForm);
		} catch (DataIntegrityViolationException e) {
			throw IndexViolationApiException.build(400L, "ERRO TO CREATE PET", e);
		}
	}

	@Override
	public Pet update(Pet petBuildedByForm) throws ApiException {
		log.info("Starting Method update in PetSpringDataJPAService");
		log.info("Parameter:Client Code = {}, Pet Code = {}", petBuildedByForm.getClientCode(), petBuildedByForm.getCode());
		Pet petFound = this.findByCode(petBuildedByForm.getClientCode(), petBuildedByForm.getCode());
		log.info("Updating pet with form data");
		petFound.update(petBuildedByForm);
		log.info("Save in petRepository");
		saveRepository(petFound);
		log.info("Finishing Method update in PetSpringDataJPAService");
		return petFound;
	}

	@Override
	public void deleteByCode(String clientCode, String petCode) throws ApiException {
		log.info("Starting Method deleteByCode in PetSpringDataJPAService");
		log.info("Parameter:Client Code = {}, Pet Code = {}", clientCode, petCode);
		log.info("Deleting pet by code on clientRepository");
		petRepository.delete(this.findByCode(clientCode, petCode));
		log.info("Finishing Method deleteByCode in ClientSpringDataJPAService");
	}

	private ApiException throwNotFoundApiException(String clientCode,String petCode) {
		String message = "NOT FOUND PET TO CLIENT CODE: " + clientCode + " PET CODE: " + petCode;
		log.warn(message + " in PetSpringDataJPAService");
		return new ApiException(404L, message);
	}
}
