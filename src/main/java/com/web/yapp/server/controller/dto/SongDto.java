package com.web.yapp.server.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {
    private String title;
    private String coverUrl;
    private String fileUrl;
}
