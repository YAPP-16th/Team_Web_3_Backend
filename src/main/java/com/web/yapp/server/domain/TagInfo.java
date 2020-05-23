package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "TUNA_TAG_INFO")
@Entity
public class TagInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_INFO_ID")
    private Long id;

    @ManyToOne // 1명의 뮤지션이 태그정보를 여러개 갖는다
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    @Column(name = "TAG_INFO_TAG_NUM")
    private int tagNum;

    @Column(name = "TAG_INFO_RPRSN_YN") //대표 태그인지 아닌지 구분 [ 대표 : 1 / 일반 : 0 ]
    private int represent;

    @Builder
    public TagInfo(Musician musician, int tagNum, int represent){
        this.musician = musician;
        this.tagNum = tagNum;
        this.represent = represent;
    }
}
