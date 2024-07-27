package com.assignment.cashRich.config;

import com.assignment.cashRich.filter.RestControllerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class ProjectSecurityConfig {

    @Autowired
    private RestControllerRequestFilter restControllerRequestFilter;


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/cash-rich/api/user/update","/cash-rich/api/latest/crypto-quotes/fetch").authenticated()
                        .requestMatchers("/cash-rich/api/user/sign-up","/cash-rich/api/user/login").permitAll())
                .addFilterAfter(restControllerRequestFilter,UsernamePasswordAuthenticationFilter.class);;

        return http.build();
    }


}
