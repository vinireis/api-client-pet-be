package br.com.petz.apiclientpet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petz.apiclientpet.exception.ApiException;
import br.com.petz.apiclientpet.model.enums.GenderType;
import br.com.petz.apiclientpet.model.enums.PetType;
import br.com.petz.apiclientpet.model.enums.SizeType;
import br.com.petz.apiclientpet.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pet")
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String code;

	@NotBlank(message = "Petname Empty")
	@Size(min = 3, message = "Petname must be at least 3 char")
	private String petName;

	@NotNull(message = "Size Not Valid!")
	private SizeType size;

	@NotNull(message = "Pet Type Not Valid!")
	private PetType petType;

	@NotNull(message = "Gender Not Valid!")
	private GenderType gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", referencedColumnName = "id", updatable = false)
	private Client client;

	private String color;
	private Long weight;

	public void buildCode() {
		this.code = getFirstPetName().concat(getFirtClientName().concat(this.client.getCpf().substring(0, 3))).toLowerCase();
	}

	private String getFirstPetName() {
		return this.petName.split(" ")[0];
	}
	
	private String getFirtClientName() {
		return this.client.getFullName().split(" ")[0];
	}

	@JsonIgnore
	@Transient
	public String getClientCode() {
		return this.client.getCode();
	}

	public String getClientName() {
		return this.client.getFullName();
	}

	public void flushClient(ClientService clientService) throws ApiException {
		this.client = clientService.findByCode(this.getClientCode());
	}

	public void update(Pet petByForm) {
		this.petName = petByForm.getPetName();
		this.size = petByForm.getSize();
		this.petType = petByForm.getPetType();
		this.gender = petByForm.getGender();
		this.color = petByForm.getColor();
		this.weight = petByForm.getWeight();
	}
}
