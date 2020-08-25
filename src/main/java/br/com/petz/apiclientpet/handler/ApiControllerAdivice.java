package br.com.petz.apiclientpet.handler;

import java.io.IOException;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.servlet.resource.HttpResource;

import br.com.petz.apiclientpet.exception.ApiException;
import br.com.petz.apiclientpet.handler.model.ApiErrorResponse;
import br.com.petz.apiclientpet.handler.model.FormErro;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class ApiControllerAdivice {
    private ApiErrorResponseExceptionExtractor apiErrorResponseExceptionExtractor;	
	private MessageSource messageSource;

    public ApiControllerAdivice(ApiErrorResponseExceptionExtractor apiErrorExtractor, MessageSource messageSource) {
		this.apiErrorResponseExceptionExtractor = apiErrorExtractor;
		this.messageSource = messageSource;
	}
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ApiErrorResponse handle(HttpMessageNotReadableException exception) throws IOException {
		log.error("Invalid Format Errors: {}", exception.getMostSpecificCause().getLocalizedMessage());
		return this.apiErrorResponseExceptionExtractor.getApiResponse(exception, HttpStatus.BAD_REQUEST.value());
	}
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiErrorResponse handle(MethodArgumentNotValidException exception) {
		log.error("Validation Errors: {}", exception.getMessage());
		List<FormErro> listFormErros = FormErro.convert(exception.getBindingResult().getFieldErrors(),messageSource);
		return this.apiErrorResponseExceptionExtractor.getApiResponse(listFormErros);
	}

	@ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(ApiException ex) {
    	log.error("Error:",ex);
        ApiErrorResponse apiErrorResponse = this.apiErrorResponseExceptionExtractor.getApiResponse(ex, ex.getCode());
        return ResponseEntity.status(apiErrorResponse.getCode().intValue()).body(apiErrorResponse);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFound ex) {
    	log.error("Error:",ex);
        ApiErrorResponse apiErrorResponse = this.apiErrorResponseExceptionExtractor.getApiResponse(ex, HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiErrorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleException(Exception ex) {
    	log.error("Internal Server Error:",ex);
        return ApiErrorResponse.builder()
        		.code((long) HttpStatus.INTERNAL_SERVER_ERROR.value())
        		.description("Internal Server Error!").build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handleInternalError(HttpRequest req, HttpResource res, Exception ex) {
    	log.error("Internal Server Error:",ex);
    	ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
    	.code((long) HttpStatus.INTERNAL_SERVER_ERROR.value())
    	.description("Internal Server Error!").build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleBadRequest(HttpRequest req, HttpResource res, BadRequest ex) {
    	log.error("Error:",ex);
    	ApiErrorResponse apiErrorResponse = this.apiErrorResponseExceptionExtractor.getApiResponse(ex, HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }
}