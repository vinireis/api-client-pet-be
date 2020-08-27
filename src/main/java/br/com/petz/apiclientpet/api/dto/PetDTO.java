package br.com.petz.apiclientpet.api.dto;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.petz.apiclientpet.model.Pet;
import br.com.petz.apiclientpet.model.enums.PetType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class PetDTO {
	String petCode;
	String petName;
	String clientCode;
	PetType petType;

	private PetDTO(Pet pet) {
		this.petCode = pet.getCode();
		this.petName = pet.getPetName();
		this.clientCode = pet.getClientCode();
		this.petType = pet.getPetType();
	}

	public static Page<PetDTO> convertToPage(Page<Pet> pets) {
		return pets.map(PetDTO::new);
	}
}
