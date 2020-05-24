package com.web.yapp.server.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_THEME")
@Entity
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "THEME_ID")
    private Long id;

    @Column(name = "THEME_KIND_NM") // 카테고리 이름
    private String themeKindNm;

    @ManyToOne
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musicianId;

    /*
    Request_form_id fk가 와야하는데 정확히 모르겠음.
     */
    @Builder
    public Theme(Long id,
                      String themeKindNm,
                      Musician musicianId
    ){
        this.id = id;
        this.themeKindNm = themeKindNm;
        this.musicianId = musicianId;

    }
}