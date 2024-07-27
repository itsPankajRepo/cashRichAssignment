package com.assignment.cashRich.dto;

import com.assignment.cashRich.constant.CashRichExceptionCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;


@Data
@ToString
@JsonInclude(value = Include.NON_NULL)
public class ApiResponse<T extends Object> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3689694387554313790L;

	/**
	 * message : localized message for the various scenario.
	 */
	private String message;

	/**
	 * status : Success/Failure
	 */
	private String status;

	/**
	 * statusCode : status code for various senarios
	 */
	private Integer statusCode;

	/**
	 * result : object if required otherwise it will be null.
	 */
	private T result;

	private List<String> warning;

	public ApiResponse() {

	}

	public ApiResponse(String message, String status, Integer statusCode) {
		super();
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
	}

	public ApiResponse(String message, String status, Integer statusCode, T result) {
		super();
		this.message = message;
		this.status = status;
		this.statusCode = statusCode;
		this.result = result;
	}


	public ApiResponse(Integer statusCode, String message, T result) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.result = result;

	}

	public ApiResponse(String message, Integer statusCode, T result) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.result = result;
	}

	public ApiResponse(String message,String status, CashRichExceptionCode exceptionCodes) {
		super();
		this.message = message;
		this.status = status;
		this.statusCode = exceptionCodes.getExceptionCode();
	}

	public ApiResponse(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;

	}
}

