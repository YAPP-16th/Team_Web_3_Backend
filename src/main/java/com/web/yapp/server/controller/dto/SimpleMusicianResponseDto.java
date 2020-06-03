package com.web.yapp.server.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SimpleMusicianResponseDto {
    private MusicianDto musicianDto;
    private SongDto songDto;
    private List<String> spclNoteTags;
    private List<String> RPtags;

    @Builder
    public SimpleMusicianResponseDto(MusicianDto musicianDto, SongDto songDto,List<String> spclNoteTags, List<String> RPtags){
        this.musicianDto = musicianDto;
        this.songDto = songDto;
        this.spclNoteTags = spclNoteTags;
        this.RPtags = RPtags;
    }
}
