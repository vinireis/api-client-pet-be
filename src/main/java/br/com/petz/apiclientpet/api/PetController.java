package br.com.petz.apiclientpet.api;

import java.net.URI;
import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petz.apiclientpet.api.dto.PetDTO;
import br.com.petz.apiclientpet.api.dto.PetDetailDTO;
import br.com.petz.apiclientpet.api.dto.form.PetForm;
import br.com.petz.apiclientpet.exception.ApiException;
import br.com.petz.apiclientpet.model.Pet;
import br.com.petz.apiclientpet.service.PetService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class PetController implements PetAPI {
	private PetService petService;

	public PetController(PetService petService) {
		this.petService = petService;
	}

	@Override
	public Page<PetDTO> findByClientCode(String clientCode, 
			@PageableDefault(sort = "code", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		log.info("Starting Method FindAll in Pet Controller!");
		Page<Pet> pets = petService.findByClientCode(clientCode,pageable);
		log.info("Convert pets to Page!");
		log.info("Finishing Method Findall in Pet Controller!");
		return PetDTO.convertToPage(pets);
	}

	@Override
	public PetDetailDTO findByCode(String clientCode, String petCode) throws ApiException {
		log.info("Starting Method findByCode in Pet Controller!");
		log.info("Parameter petCode = {}", petCode);
		Pet pet = petService.findByCode(clientCode,petCode);
		log.info("Converting Pet to PetDetailDTO");
		return new PetDetailDTO(pet);
	}

	@Override
	public ResponseEntity<PetDetailDTO> create(String clientCode, PetForm form, UriComponentsBuilder uriBuilder)
			throws NoSuchAlgorithmException, ApiException {
		log.info("Starting Method Create in Pet Controller!");
		log.info("Form: {}", form);
		Pet pet = petService.save(form.buildPet(clientCode));
		log.info("Finishing Method Create in Pet Controller!");
		URI uri = uriBuilder.path("/petz/app/v1/client/{clientCode}/pet/{petCode}").buildAndExpand(pet.getClientCode(),pet.getCode()).toUri();
		return ResponseEntity.created(uri).body(new PetDetailDTO(pet));
	}

	@Override
	public PetDetailDTO update(String clientCode, String petCode, @Valid PetForm form) throws ApiException {
		log.info("Starting Method Update in Pet Controller!");
		log.info("Form: {}", form);
		Pet pet = petService.update(form.buildPet(clientCode,petCode));
		log.info("Finishing Method Update in Pet Controller!");
		return new PetDetailDTO(pet);
	}

	@Override
	public void deleteByCode(String clientCode,String petCode) throws ApiException {
		log.info("Starting Method Delete in Pet Controller!");
		petService.deleteByCode(clientCode,petCode);
		log.info("Finishing Method Delete in Pet Controller!");
	}
}
