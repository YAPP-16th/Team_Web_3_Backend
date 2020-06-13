package com.web.yapp.server.domain.service;

import com.web.yapp.server.domain.Role;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.UserClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserClassRepository userClassRepository;

    public Long findUserIdByEmail(String email) {
        User user = userClassRepository.findUserByEmail(email);
        return user.getId();
    }

    public User findUserByEmail(String email) {
        return userClassRepository.findUserByEmail(email);
    }

    public User findUserById(Long userId) {
        return userClassRepository.findUserById(userId);
    }

    @Transactional
    public void createUser(String email, String userNM, String profileUrl) {
        User user = User.builder()
                .email(email)
                .name(userNM)
                .profile_url(profileUrl)
                .role(Role.USER)
                .build();
        userClassRepository.save(user);
    }
}
