package br.com.petz.apiclientpet.api.dto;

import br.com.petz.apiclientpet.model.Client;
import br.com.petz.apiclientpet.model.enums.GenderType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class ClientDetailDTO {
	private String fullName;
	private String email;
	private String prefixCellPhoneNumber;
	private String cellPhoneNumber;
	private String prefixPhoneNumber;
	private String phoneNumber;
	private GenderType gender;

	public ClientDetailDTO(Client client) {
		this.fullName = client.getFullName();
		this.email = client.getEmail();
		this.prefixCellPhoneNumber = client.getPrefixCellPhoneNumber();
		this.cellPhoneNumber = client.getCellPhoneNumber();
		this.prefixPhoneNumber = client.getPrefixPhoneNumber();
		this.phoneNumber = client.getPhoneNumber();
		this.gender = client.getGender();
	}
}
