package com.web.yapp.server.config.auth.dto;

import com.web.yapp.server.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String profile_url;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.profile_url = user.getProfile_url();
    }
}
