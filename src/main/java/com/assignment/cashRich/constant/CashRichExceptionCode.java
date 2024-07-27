package com.assignment.cashRich.constant;

public enum CashRichExceptionCode {

    USER_ALREADY_EXIST_WITH_GIVEN_MAIL(4001, "user.already.exist.with.mail.or.username"),
    INVALID_USERNAME_OR_PASSWORD(4002,"invalid.username.or.password" ),
    SOMETHING_WENT_WRONG(4002, "something.went.wrong");

    /**
     * The exception code.
     */
    private final Integer exceptionCode;

    /**
     * The exception description.
     */
    private final String exceptionDescription;


    CashRichExceptionCode(int exceptionCode, String exceptionDescription) {
        this.exceptionCode = exceptionCode;
        this.exceptionDescription = exceptionDescription;
    }


    /**
     * Gets the exception code.
     *
     * @return the exception code
     */
    public Integer getExceptionCode() {
        return this.exceptionCode;
    }

    /**
     * Gets the exception description.
     *
     * @return the exception description
     */
    public String getExceptionDescription() {
        return exceptionDescription;
    }
}


