package com.web.yapp.server.domain.service;

import com.web.yapp.server.controller.dto.OAuthAttributesDto;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public Long findUserIdEmail(String email){
        User user = userRepository.findByEmail(email);
        return user.getId();
    }

    public User findUserById(Long userId){
        return userRepository.findUserById(userId);
    }
}
