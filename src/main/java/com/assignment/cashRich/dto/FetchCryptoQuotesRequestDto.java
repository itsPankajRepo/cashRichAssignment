package com.assignment.cashRich.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FetchCryptoQuotesRequestDto(@NotNull @NotEmpty String cryptSymbol) {
}
