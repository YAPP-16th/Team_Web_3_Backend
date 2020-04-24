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
    private int represent;

    @Builder
    public SongDto(String title, String coverUrl, String fileUrl, int represent){
        this.title = title;
        this.coverUrl = coverUrl;
        this.fileUrl = fileUrl;
        this.represent = represent;
    }

    public Song toEntity(){
        return Song.builder()
                .title(title)
                .cover_url(coverUrl)
                .file_url(fileUrl)
                .represent(represent)
                .build();
    }
}
