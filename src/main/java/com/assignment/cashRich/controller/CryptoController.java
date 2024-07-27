package com.assignment.cashRich.controller;

import com.assignment.cashRich.constant.RequestUriConstant;
import com.assignment.cashRich.dto.ApiResponse;
import com.assignment.cashRich.dto.FetchCryptoQuotesRequestDto;
import com.assignment.cashRich.handler.CryptoHandler;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RequestUriConstant.CASH_RICH_ROOT)
@Slf4j
@AllArgsConstructor
public class CryptoController {

    private final CryptoHandler cryptoHandler;

    @PostMapping(value = RequestUriConstant.FETCH_LATEST_CRYPTO_QUOTES)
    public ApiResponse<Object> fetchCryptoLatestQuotes(@RequestBody @Valid FetchCryptoQuotesRequestDto fetchCryptoQuotesRequestDto) {
        log.info("Inside CryptoController :: fetchCryptoLatestQuotes  with request :: {}", fetchCryptoQuotesRequestDto);
        return cryptoHandler.fetchCryptoLatestQuotes(fetchCryptoQuotesRequestDto);
    }
}
