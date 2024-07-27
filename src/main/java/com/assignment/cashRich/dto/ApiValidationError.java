package com.assignment.cashRich.dto;

import lombok.Data;

@Data
public class ApiValidationError {

	private String field;

	private String rejectedValue;

	private String errorMessage;

}
