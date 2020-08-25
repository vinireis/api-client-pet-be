package br.com.petz.apiclientpet.exception;

import lombok.Getter;

@Getter
public class ApiException extends Exception {
	private static final long serialVersionUID = 1L;
	private Long code;

	public ApiException(Long code, String message) {
		super(message);
		this.code = code;
	}
}