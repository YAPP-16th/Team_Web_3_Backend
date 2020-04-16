package com.web.yapp.server.domain.musician;

import com.web.yapp.server.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_MUSICIAN")
@Entity
public class Musician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MUSICIAN_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "MUSICIAN_CAREER") //경력
    private String career;

    @Column(name = "MUSICIAN_NAME", nullable = false) //아티스트 이름
    private String name;

    @Column(name = "MUSICIAN_INTRODUCTION", nullable = false) //프로필 문구
    private String introduction;

    @Column(name = "MUSICIAN_PROFILE_URL") //프로필 사진 url
    private String profile_url;


}
