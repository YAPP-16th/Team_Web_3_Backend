package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Song;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDto {
    private String title;
    private String coverUrl;
    private String fileUrl;

    @Builder
    public SongDto(String title, String coverUrl, String fileUrl){
        this.title = title;
        this.coverUrl = coverUrl;
        this.fileUrl = fileUrl;
    }

    public Song toEntity(){
        return Song.
    }
}
