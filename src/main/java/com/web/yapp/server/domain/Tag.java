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

    @Column(name = "TAG_CATEGORY")
    private String categoryNM;

    @Builder
    public Tag(String tagNM, String categoryNM){
        this.tagNM = tagNM;
        this.categoryNM = categoryNM;
    }

}
