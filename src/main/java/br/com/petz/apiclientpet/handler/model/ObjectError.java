package br.com.petz.apiclientpet.handler.model;

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
@ApiModel(description = "Error Enitity")
public class ObjectError   {
	@ApiModelProperty(value = "Error ID")
	private Long code;
	@ApiModelProperty(value = "Error Description")
	private String message;
	@ApiModelProperty(value = "Native Error description")
	private String nativeMessage;
}

