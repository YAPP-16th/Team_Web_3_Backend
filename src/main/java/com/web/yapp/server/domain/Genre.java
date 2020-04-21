package com.web.yapp.server.domain;

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

    @Column(name = "GENRE_KIND_NM") //경력
    private String kind_nm;

    @ManyToOne
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    /*
    Request_form_id fk가 와야하는데 정확히 모르겠음.
     */

}