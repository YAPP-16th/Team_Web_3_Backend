package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MusicianMainResponseDto {
    private Long musicianId;
    private String nickNm;
    private String introduction;
    private String profileUrl;
}
