package br.com.petz.apiclientpet.api;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petz.apiclientpet.api.dto.PetDTO;
import br.com.petz.apiclientpet.api.dto.PetDetailDTO;
import br.com.petz.apiclientpet.api.dto.form.PetForm;
import br.com.petz.apiclientpet.exception.ApiException;

@RestController
@RequestMapping("/v1/client/{clientCode}/pet")
public interface PetAPI {
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	Page<PetDTO> findByClientCode(@PathVariable String clientCode, Pageable pageable) throws ApiException;

	@GetMapping("/{petCode}")
	@ResponseStatus(value = HttpStatus.OK)
	PetDetailDTO findByCode(@PathVariable String clientCode, @PathVariable String petCode) throws ApiException;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	ResponseEntity<PetDetailDTO> create(@PathVariable String clientCode, @RequestBody @Valid PetForm form, UriComponentsBuilder uri)
			throws NoSuchAlgorithmException, ApiException;

	@PutMapping("/{petCode}")
	@ResponseStatus(value = HttpStatus.OK)
	PetDetailDTO update(@PathVariable String clientCode, @PathVariable String petCode, @RequestBody @Valid PetForm form) throws ApiException;

	@DeleteMapping("/{petCode}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void deleteByCode(@PathVariable String clientCode, @PathVariable String petCode) throws ApiException;
}
