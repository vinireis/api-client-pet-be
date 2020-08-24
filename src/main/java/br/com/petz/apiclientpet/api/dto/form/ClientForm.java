package br.com.petz.apiclientpet.api.dto.form;

public class ClientForm {
	private String name;
	private String email;
	private String code;

	public ClientForm(String name, String email, String code) {
		this.name = name;
		this.email = email;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "ClientForm [name=" + name + ", email=" + email + ", code=" + code + "]";
	}
}
