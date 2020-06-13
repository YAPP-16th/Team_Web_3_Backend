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
@ApiModel
public class SimpleMusicianResponseDto {
    @ApiModelProperty(value = "musicianDto")
    private MusicianDto musicianDto;

    @ApiModelProperty(value = "songDto")
    private SongDto songDto;

    @ApiModelProperty(value = "작업태그")
    private List<String> spclNoteTags;

    @ApiModelProperty(value = "대표태그")
    private List<String> RPtags;

    @Builder
    public SimpleMusicianResponseDto(MusicianDto musicianDto, SongDto songDto,List<String> spclNoteTags, List<String> RPtags){
        this.musicianDto = musicianDto;
        this.songDto = songDto;
        this.spclNoteTags = spclNoteTags;
        this.RPtags = RPtags;
    }
}
