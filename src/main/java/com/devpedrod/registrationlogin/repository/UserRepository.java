package com.devpedrod.registrationlogin.repository;

import com.devpedrod.registrationlogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE _user x SET x.enabled = TRUE WHERE x.email = ?1")
    void enableUser(String email);
}
