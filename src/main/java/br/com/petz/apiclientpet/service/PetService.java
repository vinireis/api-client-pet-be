package br.com.petz.apiclientpet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.petz.apiclientpet.model.Pet;

public interface PetService {
	Page<Pet> findByClientCode(String clientCode, Pageable pageable);
	Pet findByCode(String clientCode, String petCode);
	Pet save(Pet buildPetByDTO);
	Pet update(Pet buildPetByDTO);
	void deleteByCode(String clientCode, String petCode);
}
