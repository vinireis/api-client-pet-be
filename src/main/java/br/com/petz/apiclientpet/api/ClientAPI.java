package br.com.petz.apiclientpet.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petz.apiclientpet.api.dto.ClientDTO;
import br.com.petz.apiclientpet.api.dto.form.ClientForm;

@RestController
@RequestMapping("/v1/client")
public interface ClientAPI {
	@PostMapping
	public ClientDTO save(@RequestBody ClientForm form);
}
