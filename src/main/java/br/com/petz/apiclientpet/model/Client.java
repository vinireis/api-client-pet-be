package br.com.petz.apiclientpet.model;

import java.security.NoSuchAlgorithmException;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petz.apiclientpet.model.enums.GenderType;
import br.com.petz.apiclientpet.service.encrypt.PasswordEncrypter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client")
public class Client {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String code;
	
    @JsonIgnore 
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Credential credential;
	
	@NotBlank(message = "Name Empty")
	@Pattern(regexp = "^[A-Z][a-z]*\\s.*[A-Z][a-z]*$", message = "Fullname Not Valid")
	private String fullName;

	@NotBlank(message = "Prefix Cellphone Number Empty")
	@Pattern(regexp = "[1-9]{2}", message = "Prefix Cellphone Number Not Valid")
	private String prefixCellPhoneNumber;

	@NotBlank(message = "Cellphone Number Empty")
	@Pattern(regexp = "^9[1-9][0-9]{3}\\-[0-9]{4}$", message = "Cellphone Number Not Valid")
	private String cellPhoneNumber;

	@Pattern(regexp = "[1-9]{2}", message = "Prefix Telephone Number Not Valid")
	private String prefixPhoneNumber;

	@Pattern(regexp = "^9[1-9][0-9]{3}\\-[0-9]{4}$", message = "Telephone Number Not Valid")
	private String phoneNumber;

	@NotNull(message = "Gender Not Valid!")
	private GenderType gender;

	@CPF(message = "CPF Not Valid")
	@NotBlank(message = "CPF Empty")
	@ToString.Exclude
	private String cpf;

	public void encryptPassword(PasswordEncrypter passwordEncrypter) throws NoSuchAlgorithmException {
		this.credential.encryptPassword(passwordEncrypter);
	}

	public String getEmail() {
		return this.credential.getEmail();
	}

	public void buildCode() {
		String[] nameParts = this.fullName.split(" ");
		String firstName = nameParts[0];
		String lastName = nameParts[nameParts.length - 1];
		this.code = firstName.concat(lastName).concat(this.cpf.substring(0, 3)).toLowerCase();
	}

	public void update(Client buildClientByDTO) {
		this.fullName = buildClientByDTO.getFullName();
		this.prefixCellPhoneNumber = buildClientByDTO.getPrefixCellPhoneNumber();
		this.cellPhoneNumber = buildClientByDTO.getCellPhoneNumber();
		this.prefixPhoneNumber = buildClientByDTO.getPrefixPhoneNumber();
		this.phoneNumber = buildClientByDTO.getPhoneNumber();
		this.gender = buildClientByDTO.getGender();
	}
}
