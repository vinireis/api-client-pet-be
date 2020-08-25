package br.com.petz.apiclientpet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petz.apiclientpet.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
