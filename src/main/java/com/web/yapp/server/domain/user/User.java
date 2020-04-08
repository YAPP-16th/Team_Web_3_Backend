package com.web.yapp.server.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
//@Table(name = "TUNA_USER")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false) //이름
    private String name;

    @Column(nullable = false) //이메일
    private String email;

    @Column(nullable = false) //프로필사진
    private String profile_url;

    @Enumerated(EnumType.STRING) //게스트, 사용자, 관리자 등 구분
    @Column(nullable = false)
    private Role role;

    @Column //연령대
    private int age;

    @Column //별명
    private String nickname;

    @Column //성별
    private String gender;

    @Column //생일
    private String birthday;


    @Builder
    public User(String name,String email, String profile_url, Role role){
        this.name = name;
        this.email = email;
        this.profile_url = profile_url;
        this.role = role;
    }

    public User update(String name, String profile_url){
        this.name = name;
        this.profile_url = profile_url;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
