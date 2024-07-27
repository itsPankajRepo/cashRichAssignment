package com.assignment.cashRich.repository;

import com.assignment.cashRich.entity.UserFetchingLatestQuotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFetchingLatestQuotesRep extends JpaRepository<UserFetchingLatestQuotes,Long> {
}
