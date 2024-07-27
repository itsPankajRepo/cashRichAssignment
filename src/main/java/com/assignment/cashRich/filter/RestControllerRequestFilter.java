package com.assignment.cashRich.filter;

import com.assignment.cashRich.constant.GenericConstant;
import com.assignment.cashRich.constant.MessageConstants;
import com.assignment.cashRich.dto.ApiResponse;
import com.assignment.cashRich.service.TokenService;
import com.assignment.cashRich.util.ValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class RestControllerRequestFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Inside once per request filter");
       try {
           ValidationUtil.validateMandatoryHeader(request);

           final String authorizationHeader = request.getHeader("Authorization");
           String jwtToken = null;

           if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
               jwtToken = authorizationHeader.substring(7);

           }

           if (jwtToken != null) {
               if (tokenService.isTokenPresent(jwtToken)) {
                   var userToken = tokenService.getUserByToken(jwtToken);
                   UsernamePasswordAuthenticationToken authenticationToken =
                           new UsernamePasswordAuthenticationToken(userToken.getUsername(), null, null);
                   authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);
               } else {
                   throw new RuntimeException("Invalid Token");
               }
           }

           filterChain.doFilter(request, response);

       } catch (RuntimeException e) {
           response.setContentType("application/json");
           ObjectMapper objectMapper = new ObjectMapper();
           String jsonResponse = objectMapper.writeValueAsString(new ApiResponse<>(e.getMessage(),
                   GenericConstant.STATUS_FAILURE, HttpStatus.BAD_REQUEST.value()));
           response.getWriter().write(jsonResponse);
           response.getWriter().flush();

       } catch (Exception e) {
           response.setContentType("application/json");
           ObjectMapper objectMapper = new ObjectMapper();
           String jsonResponse = objectMapper.writeValueAsString(new ApiResponse<>(MessageConstants.SOMETHING_WENT_WRONG,
                   GenericConstant.STATUS_FAILURE, HttpStatus.BAD_REQUEST.value()));
           response.getWriter().write(jsonResponse);
           response.getWriter().flush();

        }

    }
}
