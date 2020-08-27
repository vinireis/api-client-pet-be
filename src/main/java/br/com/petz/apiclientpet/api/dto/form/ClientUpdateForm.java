package br.com.petz.apiclientpet.api.dto.form;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.petz.apiclientpet.model.Client;
import br.com.petz.apiclientpet.model.Credential;
import br.com.petz.apiclientpet.model.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Valid
@ToString
@Getter
public class ClientUpdateForm {
	@NotBlank(message = "Name Empty")
	@Pattern(regexp = "^[A-Z][a-z]*\\s.*[A-Z][a-z]*$",message = "Fullname Not Valid")
	private String fullName;
	
	@NotBlank(message = "Email Empty")
	@Email(message = "Email Not Valid")
	private String email;
	
	@NotBlank(message = "Prefix Cellphone Number Empty")
	@Pattern(regexp = "[1-9]{2}",message = "Prefix Cellphone Number Not Valid")
	private String prefixCellPhoneNumber;
	
	@NotBlank(message = "Cellphone Number Empty")
	@Pattern(regexp = "^9[1-9][0-9]{3}\\-[0-9]{4}$",message = "Cellphone Number Not Valid")
	private String cellPhoneNumber;
	
	@Pattern(regexp = "[1-9]{2}",message = "Prefix Telephone Number Not Valid")
	private String prefixPhoneNumber;
	
	@Pattern(regexp = "^9[1-9][0-9]{3}\\-[0-9]{4}$",message = "Telephone Number Not Valid")
	private String phoneNumber;
	
	@NotNull(message = "Gender Not Valid!")
	private GenderType gender;
	
	
	public Client buildClient() {
		return Client.builder()
				.fullName(fullName)
				.credential(buildCredential())
				.prefixCellPhoneNumber(prefixCellPhoneNumber)
				.cellPhoneNumber(cellPhoneNumber)
				.prefixPhoneNumber(prefixPhoneNumber)
				.phoneNumber(phoneNumber)
				.gender(gender)
				.build();
	}

	private Credential buildCredential() {
		return Credential.builder()
				.email(email)
				.build();
	}

	public Client buildClient(String clientCode) {
		Client client = this.buildClient();
		client.setCode(clientCode);
		return client;
	}
}
