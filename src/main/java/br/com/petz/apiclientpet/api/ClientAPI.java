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
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petz.apiclientpet.api.dto.ClientDTO;
import br.com.petz.apiclientpet.api.dto.ClientDetailDTO;
import br.com.petz.apiclientpet.api.dto.form.ClientForm;

@RestController
@RequestMapping("/v1/client")
public interface ClientAPI {
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	Page<ClientDTO> findAll(Pageable pageable);
	@GetMapping("/{clientCode}")
	@ResponseStatus(value = HttpStatus.OK)
	ClientDetailDTO findByCode(@PathVariable String clientCode) throws NotFound;
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	ResponseEntity<ClientDetailDTO> create(@RequestBody @Valid ClientForm form, UriComponentsBuilder uri) throws NoSuchAlgorithmException;

	@PutMapping("/{clientCode}")
	@ResponseStatus(value = HttpStatus.OK)
	ClientDetailDTO update(@PathVariable String clientCode,@RequestBody @Valid ClientForm form) throws NotFound;

	@DeleteMapping("/{clientCode}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	void deleteByCode(@PathVariable String clientCode) throws NotFound;
}
