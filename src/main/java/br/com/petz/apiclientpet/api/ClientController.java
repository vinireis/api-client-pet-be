package br.com.petz.apiclientpet.api;

import org.springframework.web.bind.annotation.RestController;

import br.com.petz.apiclientpet.api.dto.ClientDTO;
import br.com.petz.apiclientpet.api.dto.form.ClientForm;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ClientController implements ClientAPI {
	public ClientDTO save(ClientForm form) {
		log.info("Method Save in Client Controller Start!");
		log.info("Form: {}",form);
		return null;
	}
}
