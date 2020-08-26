package br.com.petz.apiclientpet.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.petz.apiclientpet.model.enums.GenderType;
import br.com.petz.apiclientpet.model.enums.PetType;
import br.com.petz.apiclientpet.model.enums.SizeType;
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

	private String code;

	@NotBlank(message = "Petname Empty")
	@Min(value = 3, message = "Petname must be at least 3 char")
	private String petName;

	@NotNull(message = "Size Not Valid!")
	private SizeType size;

	@NotNull(message = "Pet Type Not Valid!")
	private PetType petType;

	@NotNull(message = "Gender Not Valid!")
	private GenderType gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", referencedColumnName = "id")
	private Client client;

	private String color;
	private Long weight;
	
	public void buildCode() {
		String firstClientName = this.client.getFullName().split(" ")[0];
		this.code = this.petName.concat(firstClientName.concat(this.client.getCpf().substring(0, 3))).toLowerCase();
	}
}
