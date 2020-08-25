package br.com.petz.apiclientpet.api;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petz.apiclientpet.api.dto.ClientDTO;
import br.com.petz.apiclientpet.api.dto.ClientDetailDTO;
import br.com.petz.apiclientpet.api.dto.form.ClientForm;
import br.com.petz.apiclientpet.model.Client;
import br.com.petz.apiclientpet.service.ClientService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ClientController implements ClientAPI {
	private ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	public Page<ClientDTO> findAll(
			@PageableDefault(sort = "code", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		log.info("Starting Method FindAll in Client Controller!");
		Page<Client> clients = clientService.findAll(pageable);
		log.info("Convert clients to Page!");
		log.info("Finishing Method Findall in Client Controller!");
		return ClientDTO.convertToPage(clients);
	}

	@Override
	public ClientDetailDTO findByCode(@PathVariable @NotNull String clientCode) throws NotFound {
		log.info("Starting Method findByCode in Client Controller!");
		log.info("Parameter clientCode = {}", clientCode);
		Client client = clientService.findByCode(clientCode);
		log.info("Converting Client to ClientDetailDTO");
		return new ClientDetailDTO(client);
	}

	@Override
	public ResponseEntity<ClientDetailDTO> create(ClientForm form, UriComponentsBuilder uriBuilder) {
		log.info("Starting Method Create in Client Controller!");
		log.info("Form: {}", form);
		Client client = clientService.save(form.buildClient());
		log.info("Finishing Method Create in Client Controller!");
		URI uri = uriBuilder.path("/petz/app/v1/client/{clientCode}").buildAndExpand(client.getCode()).toUri();
		return ResponseEntity.created(uri).body(new ClientDetailDTO(client));
	}

	@Override
	public ClientDetailDTO update(String clientCode, @Valid ClientForm form) throws NotFound {
		log.info("Starting Method Update in Client Controller!");
		log.info("Form: {}", form);
		Client client = clientService.update(form.buildClient(clientCode));
		log.info("Finishing Method Update in Client Controller!");
		return new ClientDetailDTO(client);
	}

	@Override
	public void deleteByCode(String clientCode) throws NotFound {
		log.info("Starting Method Delete in Client Controller!");
		clientService.deleteByCode(clientCode);
		log.info("Finishing Method Delete in Client Controller!");
	}
}
