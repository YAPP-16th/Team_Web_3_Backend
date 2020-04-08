package com.web.yapp.server.domain.artist;

import com.web.yapp.server.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    @Column(name = "artist_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column //경력
    private String career;

    @Column(nullable = false) //아티스트 이름
    private String name;

    @Column(nullable = false) //프로필 문구
    private String introduction;

    @Column //프로필 사진 url
    private String profile_url;

}
