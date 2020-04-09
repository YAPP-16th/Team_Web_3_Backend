package com.web.yapp.server.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String profile_url;

    @Column //연령대
    private int age;

    @Column //별명
    private String nickname;

    @Column //성별
    private String gender;

    @Column //생일
    private String birthday;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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
