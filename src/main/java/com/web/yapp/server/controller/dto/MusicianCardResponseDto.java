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
    private Long bookmarkCount; //좋아요 개수
    private Boolean alreadyBookmark; //유저가 좋아요 누른 뮤지션인지

    @Builder
    public MusicianCardResponseDto(SimpleMusicianResponseDto simpleMusicianResponseDto, Long bookmarkCount, Boolean alreadyBookmark){
        this.bookmarkCount = bookmarkCount;
        this.alreadyBookmark = alreadyBookmark;
    }

}
