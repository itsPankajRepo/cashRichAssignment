package com.assignment.cashRich.exception;

import com.assignment.cashRich.constant.CashRichExceptionCode;

public class CashRichException extends RuntimeException{

    private final CashRichExceptionCode exceptionCodes;

    public CashRichException(CashRichExceptionCode exceptionCode) {
        this.exceptionCodes = exceptionCode;
    }



    public CashRichExceptionCode getExceptionCodes() {
        return exceptionCodes;
    }
}
