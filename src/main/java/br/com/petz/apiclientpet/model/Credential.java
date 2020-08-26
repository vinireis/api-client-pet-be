package br.com.petz.apiclientpet.model;

import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import br.com.petz.apiclientpet.service.encrypt.PasswordEncrypter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "credentials")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
