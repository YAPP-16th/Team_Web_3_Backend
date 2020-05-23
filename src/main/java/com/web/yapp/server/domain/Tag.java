package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "TUNA_TAG")
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private long id;

    @Column(name = "TAG_NM")
    private String tagNM;

    /*
    * 0 : 특이사항(작업)
    * 1 : 테마
    * 2 : 장르
    * 3 : 분위기
    * 4 : 악기
    * */
    @Column(name = "TAG_CATEGORY")
    private int category;

    @Builder
    public Tag(String tagNM, Integer category){
        this.tagNM = tagNM;
        this.category = category;
    }

}
