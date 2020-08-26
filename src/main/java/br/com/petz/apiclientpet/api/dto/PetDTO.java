package br.com.petz.apiclientpet.api.dto;

import org.springframework.data.domain.Page;

import br.com.petz.apiclientpet.model.Pet;

public class PetDTO {
	private PetDTO(Pet pet) {

	}

	public static Page<PetDTO> convertToPage(Page<Pet> pets) {
		return pets.map(PetDTO::new);
	}
}
