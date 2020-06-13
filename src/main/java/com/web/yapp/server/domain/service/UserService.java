package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.OAuthAttributesDto;
import com.web.yapp.server.domain.Role;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Long findUserIdByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user.getId();
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    @Transactional
    public void createUser(String email, String userNM, String profileUrl) {
        User user = User.builder()
                .email(email)
                .name(userNM)
                .profile_url(profileUrl)
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }
}
