package com.assignment.cashRich.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFetchingLatestQuotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String request;

    @Column(columnDefinition = "TEXT")
    private String response;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;
}
