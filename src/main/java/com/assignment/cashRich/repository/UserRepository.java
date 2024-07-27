package com.assignment.cashRich.repository;

import com.assignment.cashRich.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailOrUsername(String email, String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET first_name = COALESCE(:firstName, users.first_name), last_name = COALESCE(:lastName, users.last_name), mobile_no = COALESCE(:mobileNo , users.mobile_no), password = COALESCE(:password , users.password) WHERE username = :username", nativeQuery = true)
    int updateUser(@Param("firstName") String firstName, @Param("lastName") String lastName,
                   @Param("mobileNo") String mobileNo, @Param("password") String password,
                   @Param("username") String username);


    Optional<User> findByUsernameAndPassword(String username, String password);
}
