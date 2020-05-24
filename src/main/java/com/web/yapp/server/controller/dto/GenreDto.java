package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.Atmosphere;
import com.web.yapp.server.domain.Genre;
import com.web.yapp.server.domain.Musician;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class GenreDto {
    private Long id;
    private String genreKindNm;
    private Musician musicianId;


    public GenreDto(Genre Entity){
        this.id = Entity.getId();
        this.genreKindNm= Entity.getGenreKindNm();
        this.musicianId = Entity.getMusicianId();

    }

    @Builder
    public GenreDto(Long id,
                         String genreKindNm,
                         Musician musicianId){
        this.id = id;
        this.genreKindNm = genreKindNm;
        this.musicianId = musicianId;
    }

    public GenreDto toEntity(){
        return GenreDto.builder()
                .id(id)
                .genreKindNm(genreKindNm)
                .musicianId(musicianId)
                .build();
    }
}
