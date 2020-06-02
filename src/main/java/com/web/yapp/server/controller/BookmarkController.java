package com.web.yapp.server.controller;

import com.web.yapp.server.domain.Bookmark;
import com.web.yapp.server.domain.Musician;
import com.web.yapp.server.domain.User;
import com.web.yapp.server.domain.repository.BookmarkRepository;
import com.web.yapp.server.domain.repository.MusicianRepository;
import com.web.yapp.server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {


    @GetMapping("/{userId}/{musicianId}")
    public void createBookmark(@PathVariable Long userId, @PathVariable Long musicianId){
        
    }

}
