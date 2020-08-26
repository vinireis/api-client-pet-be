package br.com.petz.apiclientpet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.petz.apiclientpet.exception.ApiException;
import br.com.petz.apiclientpet.model.Pet;

public interface PetService {
	Page<Pet> findByClientCode(String clientCode, Pageable pageable) throws ApiException;

	Pet findByCode(String clientCode, String petCode) throws ApiException;

	Pet save(Pet buildPetByDTO) throws ApiException;

	Pet update(Pet buildPetByDTO) throws ApiException;

	void deleteByCode(String clientCode, String petCode) throws ApiException;
}
