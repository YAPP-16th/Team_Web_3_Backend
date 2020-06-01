package com.web.yapp.server.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MusicianCardResponseDto {
    private MusicianDto musicianDto;
    private SongDto songDto;
    private List<String> spclNoteTags;
    private List<String> RPtags;
    private int bookmarkCount; //좋아요 갯수

    @Builder
    public MusicianCardResponseDto(MusicianDto musicianDto, SongDto songDto,
                                   List<String> spclNoteTags, List<String> RPtags, int bookmarkCount){
        this.musicianDto = musicianDto;
        this.songDto = songDto;
        this.spclNoteTags = spclNoteTags;
        this.RPtags = RPtags;
        this.bookmarkCount = bookmarkCount;
    }

}
