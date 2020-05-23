package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Atmosphere;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AtmosphereDto {
    private Long id;
    private String atmoKindNm;
    private Musician musicianId;


    public AtmosphereDto(Atmosphere Entity){
        this.id = Entity.getId();
        this.atmoKindNm = Entity.getAtmoKindNm();
        this.musicianId = Entity.getMusicianId();

    }

    @Builder
    public AtmosphereDto(Long id,
                         String atmoKindNm,
                         Musician musicianId){
        this.id = id;
        this.atmoKindNm = atmoKindNm;
        this.musicianId = musicianId;
    }

    public AtmosphereDto toEntity(){
        return AtmosphereDto.builder()
                .id(id)
                .atmoKindNm(atmoKindNm)
                .musicianId(musicianId)
                .build();
    }

}
