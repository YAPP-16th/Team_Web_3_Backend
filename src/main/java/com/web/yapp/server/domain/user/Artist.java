package com.web.yapp.server.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String career;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String introduction;

    @Column
    private String profile_url;

}
