package com.assignment.cashRich.exception;

import com.assignment.cashRich.constant.GenericConstant;
import com.assignment.cashRich.dto.ApiResponse;
import com.assignment.cashRich.dto.ApiValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@ControllerAdvice
public class CashRichExceptionHandler {

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException : {}", ex.getMessage());
        BindingResult br = ex.getBindingResult();
        List<ApiValidationError> apiValidationErrors = new ArrayList<>();
        for (FieldError oe : br.getFieldErrors()) {
            ApiValidationError api = new ApiValidationError();
            api.setErrorMessage(Objects.requireNonNull(oe.getDefaultMessage()));
            api.setField(oe.getField());
            api.setRejectedValue(oe.getRejectedValue() + "");
            apiValidationErrors.add(api);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                GenericConstant.STATUS_FAILURE, HttpStatus.BAD_REQUEST.value(), apiValidationErrors));

    }


    @ExceptionHandler(CashRichException.class)
    public final ResponseEntity<ApiResponse> handleSocketException(CashRichException ex) {
        String message = messageSource.getMessage(ex.getExceptionCodes().getExceptionDescription(),
                null, LocaleContextHolder.getLocale());
        log.error("CashRichException OCCURRED {}", message);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(message, GenericConstant.STATUS_FAILURE, ex.getExceptionCodes()));
    }



    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponse> handelUnknownException(Exception ex) {
        log.info("exception occured {}",ex.getMessage());

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(ex.getMessage(), GenericConstant.STATUS_FAILURE,400));

    }
}