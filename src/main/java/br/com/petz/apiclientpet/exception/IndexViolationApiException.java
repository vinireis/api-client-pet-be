package br.com.petz.apiclientpet.exception;

import org.springframework.dao.DataIntegrityViolationException;

import lombok.Getter;

@Getter
public class IndexViolationApiException extends ApiException {
	private static final String REGEX = ".*\\_INDEX\\_(\\d)( ON PUBLIC.)(.*)\\((.*)\\)(.*)";

	public IndexViolationApiException(Long code, String message) {
		super(code, message);
	}

	public static IndexViolationApiException build(Long code, String message, DataIntegrityViolationException e) {
		String fieldViolated = extractFieldViolated(e);
		return new IndexViolationApiException(400L,
				message.concat(" - THERE IS A ENTITY WITH THIS " + fieldViolated));
	}

	private static String extractFieldViolated(DataIntegrityViolationException e) {
		return e.getMessage().split("\\n")[0].replaceAll(REGEX, "$4");
	}

	private static final long serialVersionUID = 1L;
}