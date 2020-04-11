package com.web.yapp.server.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Table(name = "TUNA_USER")
@Entity
public class User {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NM", nullable = false)
    private String name;

    @Column(name = "USER_EMAIL", nullable = false)
    private String email;

    @Column(name = "USER_PROFILE_URL")
    private String profile_url;

    @Column(name = "USER_AGE")//연령대
    private int age;

    @Column(name = "USER_NICKNM") //별명
    private String nickname;

    @Column(name = "USER_GENDER") //성별
    private String gender;

    @Column(name = "USER_BIRTH") //생일
    private String birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

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
