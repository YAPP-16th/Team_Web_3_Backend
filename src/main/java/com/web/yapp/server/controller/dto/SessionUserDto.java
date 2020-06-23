package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Role;
import com.web.yapp.server.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
/*
* SessionUserDto 필드 추가 필요
* */
@Getter
@Setter
public class SessionUserDto implements Serializable {
    private String name;
    private String email;
    private String profile_url;
    private Role role;

    /**
     * 세션DTO ROLE 처리를 위해서는 여기 DTO를 조정해야한다. 일반사용자, 뮤지션
     * @param user
     */

    public SessionUserDto(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.profile_url = user.getProfile_url();
        this.role = user.getRole();
    }
}
