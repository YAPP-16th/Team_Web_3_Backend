package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUserDto implements Serializable {
    private String name;
    private String email;
    private String profile_url;

    public SessionUserDto(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.profile_url = user.getProfile_url();
    }
}
