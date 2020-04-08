package com.web.yapp.server.domain.song;

import com.web.yapp.server.domain.artist.Artist;

import javax.persistence.*;

public class song {
    @Id
    @GeneratedValue
    @Column(name = "song_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Column //노래제목
    private String title;

    @Column //커버이미지 파일 경로
    private String cover_url;

    @Column //음원파일 경로 
    private String song_url;
}
