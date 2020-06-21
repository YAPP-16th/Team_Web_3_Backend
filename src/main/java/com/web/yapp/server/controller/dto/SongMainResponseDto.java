package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SongMainResponseDto {
    private String title;
    private String coverUrl;
    private String songUrl;
}
