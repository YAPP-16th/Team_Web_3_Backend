package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_MU_TAG_MAP")
@Entity
public class MusicainTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MU_TAG_MAP_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    @Column(name = "MU_TAG_MAP_RPRSN") // 대표 : 1 , 일반 : 0
    private int represent;

    @Builder
    public MusicainTag(Musician musician, Tag tag, int represent){
        this.musician = musician;
        this.tag = tag;
        this.represent = represent;
    }
}
