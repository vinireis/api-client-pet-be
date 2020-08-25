package br.com.petz.apiclientpet.handler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Builder
@Getter
@ApiModel(description = "Error API responses")
@JsonInclude(Include.NON_NULL)
public class ApiErrorResponse {
	@ApiModelProperty(value = "Error ID")
	private Long code;
	@ApiModelProperty(value = "Error Description")
	private String message;
	@ApiModelProperty(value = "Error Details")
	private String description;
	@JsonProperty("errors")
	private List<ObjectError> objectErrors;
	@JsonProperty("validations errors")
	private List<FormErro> validationsErrors;

	public ApiErrorResponse addErrorsItem(ObjectError errorsItem) {
		this.objectErrors = toInstaceIfNull();
		this.objectErrors.add(errorsItem);
		return this;
	}

	private List<ObjectError> toInstaceIfNull() {
		return (this.objectErrors == null) ? new ArrayList<ObjectError>():this.objectErrors;
	}

	public ApiErrorResponse createErros(Exception e) {
		return addErrorsItem(buildObjectError(e));
	}

	private ObjectError buildObjectError(Exception e) {
		return ObjectError.builder()
		.message(e.getMessage())
		.nativeMessage(getNativeMessage(e))
		.code(this.getCode())
		.build();
	}

	private String getNativeMessage(Exception e) {
		return Optional.ofNullable(e.getCause())
				.map(Throwable::getMessage)
				.orElse(e.getLocalizedMessage());
	}
}
