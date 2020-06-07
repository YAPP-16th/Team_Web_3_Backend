package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_SONG")
@Entity
public class Song extends BaseTimeEntity{
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
/* feature/api
    private String cover_url;

    @Column(name = "SONG_ESNTL_URL") //필수 음원파일 경로
    private String songEsntlUrl;

    @Column(name = "SONG_CHC01_URL") //선택 음원파일 경로1
    private String songChc01Url;

    @Column(name = "SONG_CHC02_URL") //선택 음원파일 경로2
    private String songChc02Url;

    @Column(name = "SONG_CHC03_URL") //선택 음원파일 경로3
    private String songChc03Url;



}
*/

    private String coverUrl;

    @Column(name = "SONG_URL") //음원파일 경로
    private String songUrl;

    @Column(name = "SONG_RPRSN") //대표곡인지 아닌지 구분 0 : 일반 or 1 : 대표
    private int represent;

    @Builder
    public Song(String title, String coverUrl, String songUrl, int represent, Musician musician) {
        this.title = title;
        this.coverUrl = coverUrl;
        this.songUrl = songUrl;
        this.represent = represent;
        this.musician = musician;
    }

//    public static Song createSong(Musician musician){
//        Song song = new Song();
//        song.musician = musician;
//        return song;
//    }
}

