package br.com.petz.apiclientpet.api.dto.form;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.petz.apiclientpet.model.Client;
import br.com.petz.apiclientpet.model.Pet;
import br.com.petz.apiclientpet.model.enums.GenderType;
import br.com.petz.apiclientpet.model.enums.PetType;
import br.com.petz.apiclientpet.model.enums.SizeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Valid
@ToString
@Getter
public class PetForm {
	@NotBlank(message = "Petname Empty")
	@Size(min = 3,message = "Petname must be at least 3 char")
	private String petName;

	@NotNull(message = "Size Not Valid!")
	private SizeType size;
	
	@NotNull(message = "Pet Type Not Valid!")
	private PetType petType;
	
	@NotNull(message = "Gender Not Valid!")
	private GenderType gender;
	
	private String color;
	private Long weight;

	public Pet buildPet(String clientCode) {
		return Pet.builder()
				.petName(petName)
				.size(size)
				.petType(petType)
				.gender(gender)
				.color(color)
				.weight(weight)
				.client(buildClient(clientCode))
				.build();
	}
	
	public Pet buildPet(String clientCode, String petCode) {
		Pet pet = this.buildPet(clientCode);
		pet.setCode(petCode);
		return pet;
	}

	private Client buildClient(String clientCode) {
		return Client.builder()
				.code(clientCode)
				.build();
	}
}
