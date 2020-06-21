package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Musician;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class MusicianMainResponseDto {
    private Long musicianId;
    private String nickNm;
    private String introduction;
    private String profileUrl;

    public MusicianMainResponseDto(Musician Entity){
        this.musicianId = Entity.getId();
        this.nickNm = Entity.getNickNm();
        this.introduction = Entity.getIntroduction();
        this.profileUrl = Entity.getProfileUrl();
    }
}
