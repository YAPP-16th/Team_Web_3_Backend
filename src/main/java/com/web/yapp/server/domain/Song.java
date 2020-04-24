package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_SONG")
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_ID")
    private Long id;

    @ManyToOne // 노래(n) : 아티스트(1) 한명의 아티스트가 여러 곡 가질 수 있음
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    @Column(name = "SONG_TITLE") //노래제목
    private String title;

    @Column(name = "SONG_COVER_URL") //커버이미지 파일 경로
    private String coverUrl;

    @Column(name = "SONG_URL") //음원파일 경로
    private String fileUrl;

    @Column(name = "SONG_RPRSN_YN") //대표곡인지 아닌지 구분 0 : 일반 or 1 : 대표
    private int represent;

    @Builder
    public Song(String title, String cover_url, String file_url, int represent){
        this.title = title;
        this.coverUrl = cover_url;
        this.fileUrl = file_url;
        this.represent = represent;
    }
}