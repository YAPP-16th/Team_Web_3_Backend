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
    private SimpleMusicianResponseDto simpleMusicianResponseDto;
    private Long bookmarkCount; //좋아요 갯수

    @Builder
    public MusicianCardResponseDto(SimpleMusicianResponseDto simpleMusicianResponseDto, Long bookmarkCount){
        this.bookmarkCount = bookmarkCount;
    }

}
