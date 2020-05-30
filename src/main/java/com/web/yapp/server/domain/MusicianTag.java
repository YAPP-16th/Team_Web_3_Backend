package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_MU_TAG_MAP")
@Entity
public class MusicianTag {
    //비식별 관계 받아온 식별자는 외래키로만 사용하고 새로운 식별자를 추가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MU_TAG_MAP_ID")
    private Long id;

    @ManyToOne // 뮤지션 (1) : 태그 (N)
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    @ManyToOne // 태그 (1) : 뮤지션(N)
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    @Column(name = "MU_TAG_MAP_RPRSN") // 대표 : 1 , 일반 : 0
    private int represent;

    @Builder
    public MusicianTag(Musician musician, Tag tag, int represent){
        this.musician = musician;
        this.tag = tag;
        this.represent = represent;
    }
}
