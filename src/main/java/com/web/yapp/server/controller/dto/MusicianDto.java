package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MusicianDto {

    @NotEmpty(message = "뮤지션 경력은 필수입니다.")
    private String career;
    @NotEmpty(message = "뮤지션 이름은 필수입니다.")
    private String name;
    @NotEmpty(message = "뮤지션 소개는 필수입니다.")
    private String introduction;
    private String profile_url;
}
