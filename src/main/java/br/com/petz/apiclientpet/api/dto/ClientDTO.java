package br.com.petz.apiclientpet.api.dto;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.petz.apiclientpet.model.Client;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(Include.NON_NULL)
public class ClientDTO {
	private String code;
	private String fullName;

	private ClientDTO(Client client) {
		this.code = client.getCode();
		this.fullName = client.getFullName();
	}

	public static Page<ClientDTO> convertToPage(Page<Client> clients) {
		return clients.map(ClientDTO::new);
	}
}
