package br.com.petz.apiclientpet.handler;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.petz.apiclientpet.handler.model.ApiErrorResponse;
import br.com.petz.apiclientpet.handler.model.FormErro;

@Component
public class ApiErrorResponseExceptionExtractor {
	public ApiErrorResponse getApiResponse(Exception e, long defaultCode) {
		return ApiErrorResponse.builder()
				.description(e.getMessage())
				.code(defaultCode)
				.message(e.getLocalizedMessage())
				.build()
		.createErros(e);
	}
	public ApiErrorResponse getApiResponse(List<FormErro> listFormErros) {
		return ApiErrorResponse.builder()
				.description("FORM ERROS")
				.code((long) 400)
				.message("ERRO TO VALID FORM")
				.validationsErrors(listFormErros)
				.build();
	}
}
