package br.com.petz.apiclientpet.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.petz.apiclientpet.model.Pet;
import br.com.petz.apiclientpet.model.enums.GenderType;
import br.com.petz.apiclientpet.model.enums.PetType;
import br.com.petz.apiclientpet.model.enums.SizeType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class PetDetailDTO {
	private String code;
	private String petName;
	private SizeType size;
	private PetType petType;
	private GenderType gender;
	private String clientCode;
	private String color;
	private Long weight;

	public PetDetailDTO(Pet pet) {
		this.code = pet.getCode();
		this.petName = pet.getPetName();
		this.size = pet.getSize();
		this.petType = pet.getPetType();
		this.gender = pet.getGender();
		this.clientCode = pet.getClientCode();
		this.color = pet.getColor();
		this.weight = pet.getWeight();
	}

}
