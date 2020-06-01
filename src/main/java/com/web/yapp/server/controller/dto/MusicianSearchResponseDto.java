package com.web.yapp.server.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MusicianSearchResponseDto {
    private MusicianDto musicianDto;
    private SongDto songDto;
    private List<String> spclNoteTags;
    private List<String> RPtags;

    @Builder
    public MusicianSearchResponseDto(MusicianDto musicianDto, SongDto songDto,List<String> spclNoteTags, List<String> RPtags){
        this.musicianDto = musicianDto;
        this.songDto = songDto;
        this.spclNoteTags = spclNoteTags;
        this.RPtags = RPtags;
    }
}
