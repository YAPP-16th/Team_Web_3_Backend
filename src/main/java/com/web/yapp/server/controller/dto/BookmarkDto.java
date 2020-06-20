package com.web.yapp.server.controller.dto;

import com.web.yapp.server.domain.BaseTimeEntity;
import com.web.yapp.server.domain.Bookmark;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class BookmarkDto extends BaseTimeEntity {
    private Long id;
    private Musician musician;
    private User user;
    public BookmarkDto(Bookmark Entity){
        this.id = Entity.getId();
        this.musician = Entity.getMusician();
        this.user = Entity.getUser();
    }

    public Bookmark toEntity(){
        return Bookmark.builder()
                .id(id)
                .musician(musician)
                .user(user)
                .build();
    }
}