package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_GENRE")
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GENRE_ID")
    private Long id;

    @Column(name = "GENRE_KIND_NM") // 카테고리 이름
    private String genreKindNm;

    @ManyToOne
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musicianId;

    /*
    Request_form_id fk가 와야하는데 정확히 모르겠음.
     */

    @Builder
    public Genre(Long id,
                      String genreKindNm,
                      Musician musicianId
    ){
        this.id = id;
        this.genreKindNm = genreKindNm;
        this.musicianId = musicianId;

    }

}