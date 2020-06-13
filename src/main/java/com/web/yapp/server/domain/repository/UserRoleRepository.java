package com.web.yapp.server.domain.repository;

import com.web.yapp.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
