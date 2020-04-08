package com.web.yapp.server.domain.bookmark;

import com.web.yapp.server.domain.artist.Artist;
import com.web.yapp.server.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "TUNA_BOOKMARK")
@Entity
public class bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne // 북마크(n) -> 유저(1)
    @JoinColumn(name = "user_id")
    private User user;

}
