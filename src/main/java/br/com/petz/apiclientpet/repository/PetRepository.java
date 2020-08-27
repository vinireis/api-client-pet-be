package br.com.petz.apiclientpet.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petz.apiclientpet.model.Client;
import br.com.petz.apiclientpet.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	Page<Pet> findByClient(Client client, Pageable pageable);
	Optional<Pet> findByClient_CodeAndCode(String clientCode, String code);
	Page<Pet> findByClient_Code(String clientCode, Pageable pageable);
}
