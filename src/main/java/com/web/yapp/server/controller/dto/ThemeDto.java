package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Instrument;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.Theme;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThemeDto {
    private Long id;
    private String themeKindNm;
    private Musician musicianId;


    public ThemeDto(Theme Entity){
        this.id = Entity.getId();
        this.themeKindNm= Entity.getThemeKindNm();
        this.musicianId = Entity.getMusicianId();

    }

    @Builder
    public ThemeDto(Long id,
                         String themeKindNm,
                         Musician musicianId){
        this.id = id;
        this.themeKindNm = themeKindNm;
        this.musicianId = musicianId;
    }

    public ThemeDto toEntity(){
        return ThemeDto.builder()
                .id(id)
                .themeKindNm(themeKindNm)
                .musicianId(musicianId)
                .build();
    }
}
