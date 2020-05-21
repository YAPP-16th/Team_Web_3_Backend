package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Genre;
import com.web.yapp.server.domain.Instrument;
import com.web.yapp.server.domain.Musician;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstrumentDto {
    private Long id;
    private String instruKindNm;
    private Musician musicianId;


    public InstrumentDto(Instrument Entity){
        this.id = Entity.getId();
        this.instruKindNm= Entity.getInstruKindNm();
        this.musicianId = Entity.getMusicianId();

    }

    @Builder
    public InstrumentDto(Long id,
                    String instruKindNm,
                    Musician musicianId){
        this.id = id;
        this.instruKindNm = instruKindNm;
        this.musicianId = musicianId;
    }

    public InstrumentDto toEntity(){
        return InstrumentDto.builder()
                .id(id)
                .instruKindNm(instruKindNm)
                .musicianId(musicianId)
                .build();
    }
}
