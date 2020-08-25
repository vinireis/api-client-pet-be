package br.com.petz.apiclientpet.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "credentials")
@Data
@Builder
public class Credential {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 60, nullable = false)
	private String password;
	@Email
	@Column(unique = true)
	private String email;
}
