package br.com.petz.apiclientpet.handler.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FormErro {
	private String field;
	private String message;

	public FormErro(FieldError fieldError, MessageSource messageSource) {
		this.field = fieldError.getField();
		this.message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}

	public static List<FormErro> convert(List<FieldError> fieldErrors, MessageSource messageSource) {
		return fieldErrors.stream().map(e -> new FormErro(e, messageSource)).collect(Collectors.toList());
	}
}
