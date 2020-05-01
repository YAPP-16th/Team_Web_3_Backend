package com.web.yapp.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_SPCL_NOTE")
@Entity
public class SpecialNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPCL_NOTE_ID")
    private Long id;

    @Column(name = "SPCL_NOTE_KIND_NM") // 카테고리 이름
    private String spclNoteKindNm;

    @ManyToOne
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    /*
    Request_form_id fk가 와야하는데 정확히 모르겠음.
     */

}