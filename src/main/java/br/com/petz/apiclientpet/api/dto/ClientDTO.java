package br.com.petz.apiclientpet.api.dto;

import org.springframework.data.domain.Page;

import br.com.petz.apiclientpet.model.Client;

public class ClientDTO {
	private ClientDTO(Client client) {

	}

	public static Page<ClientDTO> convertToPage(Page<Client> clients) {
		return clients.map(ClientDTO::new);
	}
}
