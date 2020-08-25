package br.com.petz.apiclientpet.api.dto.form;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.petz.apiclientpet.model.Pet;
import br.com.petz.apiclientpet.model.enums.GenderType;
import br.com.petz.apiclientpet.model.enums.PetType;
import br.com.petz.apiclientpet.model.enums.SizeType;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Valid
@ToString
public class PetForm {
	@NotBlank(message = "Petname Empty")
	@Min(value = 3,message = "Petname must be at least 3 char")
	private String petName;

	@NotNull(message = "Size Not Valid!")
	private SizeType size;
	
	@NotNull(message = "Pet Type Not Valid!")
	private PetType pet;
	
	@NotNull(message = "Gender Not Valid!")
	private GenderType gender;
	
	private String color;
	private Long weight;

	public Pet buildPet() {
		return null;
	}
}
