package br.com.petz.apiclientpet.model;

import java.security.NoSuchAlgorithmException;

import javax.persistence.*;
import javax.validation.constraints.Email;

import br.com.petz.apiclientpet.service.encrypt.PasswordEncrypter;
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

	public void encryptPassword(PasswordEncrypter passwordEncrypter) throws NoSuchAlgorithmException {
		this.password = passwordEncrypter.encrypt(password);
	}
}
