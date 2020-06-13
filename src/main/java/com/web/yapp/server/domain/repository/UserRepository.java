package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByEmail(String email);
}
