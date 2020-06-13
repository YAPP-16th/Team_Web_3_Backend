package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
