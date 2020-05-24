package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_INSTRUMENT")
@Entity
public class Instrument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTRUMENT_ID")
    private Long id;

    @Column(name = "INSTRUMENT_KIND_NM") // 카테고리 이름
    private String instruKindNm;

    @ManyToOne
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musicianId;

    /*
    Request_form_id fk가 와야하는데 정확히 모르겠음.
     */

    @Builder
    public Instrument(Long id,
                 String instruKindNm,
                 Musician musicianId
    ){
        this.id = id;
        this.instruKindNm = instruKindNm;
        this.musicianId = musicianId;

    }
}