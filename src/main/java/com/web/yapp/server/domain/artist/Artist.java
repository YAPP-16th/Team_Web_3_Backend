package com.web.yapp.server.domain.artist;

import com.web.yapp.server.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_ARTIST")
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTIST_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "ARTIST_CAREER") //경력
    private String career;

    @Column(name = "ARTIST_NAME", nullable = false) //아티스트 이름
    private String name;

    @Column(name = "ARTIST_INTRODUCTION", nullable = false) //프로필 문구
    private String introduction;

    @Column(name = "ARTIST_PROFILE_URL") //프로필 사진 url
    private String profile_url;


}
