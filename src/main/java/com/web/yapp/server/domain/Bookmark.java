package com.web.yapp.server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_BOOKMARK")
@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKMARK_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "MUSICIAN_ID")
    private Musician musician;

    @ManyToOne // 북마크(n) -> 유저(1)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Bookmark(User user, Musician musician){
        this.user = user;
        this.musician = musician;
    }

}