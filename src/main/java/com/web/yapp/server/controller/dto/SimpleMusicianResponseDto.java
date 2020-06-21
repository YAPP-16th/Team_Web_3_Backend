package com.web.yapp.server.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SimpleMusicianResponseDto {
    private MusicianMainResponseDto musicianMainResponseDto;
    private SongDto songDto;
    private List<String> spclNoteTags;
    private List<String> RPtags;

    @Builder
    public SimpleMusicianResponseDto(MusicianMainResponseDto musicianMainResponseDto, SongDto songDto,List<String> spclNoteTags, List<String> RPtags){
        this.musicianMainResponseDto = musicianMainResponseDto;
        this.songDto = songDto;
        this.spclNoteTags = spclNoteTags;
        this.RPtags = RPtags;
    }
}
