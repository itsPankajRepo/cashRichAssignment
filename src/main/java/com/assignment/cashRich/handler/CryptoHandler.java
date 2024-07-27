package com.assignment.cashRich.handler;

import com.assignment.cashRich.constant.MessageConstants;
import com.assignment.cashRich.dto.ApiResponse;
import com.assignment.cashRich.dto.FetchCryptoQuotesRequestDto;
import com.assignment.cashRich.entity.UserFetchingLatestQuotes;
import com.assignment.cashRich.repository.UserFetchingLatestQuotesRep;
import com.assignment.cashRich.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@Slf4j
public class CryptoHandler {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFetchingLatestQuotesRep userFetchingLatestQuotesRep;

    @Autowired
    private UserRepository userRepository;

    @Value("${crypto.latest.quotes.url}")
    private String cryptoLatestQuotesUrl;

    @Value("${crypto.latest.quotes.header.key}")
    private String cryptoLatestQuotesHeaderKey;


    public ApiResponse<Object> fetchCryptoLatestQuotes(FetchCryptoQuotesRequestDto fetchCryptoQuotesRequestDto) {
        log.info("Inside CryptoHandler :: fetchCryptoLatestQuotes");
        cryptoLatestQuotesUrl += fetchCryptoQuotesRequestDto.cryptSymbol();
        var headers = new HttpHeaders();
        headers.set("X-CMC_PRO_API_KEY", cryptoLatestQuotesHeaderKey);

        ResponseEntity<String> response = restTemplate.exchange(cryptoLatestQuotesUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        if (response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            var userName = SecurityContextHolder.getContext().getAuthentication() == null ?
                    null : SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

            var responseBody = new JSONObject(response.getBody());
            var userId = userRepository.findByUsername(userName).get().getId();
            UserFetchingLatestQuotes userFetchingLatestQuotes = UserFetchingLatestQuotes.builder()
                    .userId(userId).request(cryptoLatestQuotesUrl).response(responseBody.toString()).timestamp(LocalDateTime.now()).build();
            userFetchingLatestQuotesRep.save(userFetchingLatestQuotes);


            return new ApiResponse<>(200, MessageConstants.FETCHED_DATA_SUCCESSFULLY, responseBody.toMap());

        }
        return new ApiResponse<>(404, MessageConstants.NO_DATA_FOUND);


    }
}
