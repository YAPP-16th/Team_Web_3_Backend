package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Song;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SongDto {
    private String title;
    private String coverUrl;
    private String songUrl;
    private int represent;

    @Builder
    public SongDto(String title, String coverUrl, String songUrl, int represent){
        this.title = title;
        this.coverUrl = coverUrl;
        this.songUrl = songUrl;
        this.represent = represent;
    }

    public Song toEntity(){
        return Song.builder()
                .title(title)
                .coverUrl(coverUrl)
                .songUrl(songUrl)
                .represent(represent)
                .build();
    }
}
