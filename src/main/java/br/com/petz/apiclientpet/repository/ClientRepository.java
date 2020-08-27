package br.com.petz.apiclientpet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petz.apiclientpet.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Optional<Client> findByCode(String clientCode);
	void deleteByCode(String clientCode);
}
