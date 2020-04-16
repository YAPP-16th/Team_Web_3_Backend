package com.web.yapp.server.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserForm {
    @NotEmpty(message="회원 이름은 필수값 입니다.")
    private String userName;

    private String userEmail;
    private String userProfileUrl;
    private int userAge;
    private String userNickname;
    private String userGender;
    private String userBirth;
    private String userRole;
    private String userPhone;
    private String date;
}
